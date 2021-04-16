package com.hmkurth.persistence;
import com.hmkurth.entity.UserRoles;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hmkurth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.relation.Role;
import javax.persistence.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2
/**
 * The type User dao test.
 */
class UserDaoTest {
    /**
     * The Dao.
     */

    GenericDao genericDao;



    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanDb.sql");


        genericDao = new GenericDao(User.class);
        /*
        databaseUtility = new DatabaseUtility();
        databaseUtility.runSQL("cleandb.sql");
        databaseUtility.runSQL("createTestData.sql");

        dao = new GenericDao(TrailReport.class);
        userDao = new GenericDao(User.class);
        trailDao = new GenericDao(Trail.class);

        Trail trail = (Trail)trailDao.getAll().get(0);
        Set<TrailReport> reports = trail.getReports();


        trailReport = new TrailReport();
        trailReport.setComments("test comments");
        trailReport.setTrail(trail);

        trailReport.setUser(((User)userDao.getAll().get(0)));

        int id = dao.insert(trailReport);
        trailReport = (TrailReport)dao.getById(id);
*/
    }

    /**
     * verifies Gets all users success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, users.size());
        log.info("get all users test: all users;" + genericDao.getAll());
    }
    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = genericDao.getByPropertyEqual("lastName", "Curry");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getId());
    }


    /**
     * Verifies Gets users by last name.
     */
    @Test
    void getUsersByLastNameLikeSuccess() {
        List<User> users = genericDao.getEntityByName("lastName","K");
        assertEquals(2, users.size());
        List<User> users2 = genericDao.getEntityByName("lastName", "Z");
        assertEquals(0, users2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {
        //assertions; what will be changed? number of users? nametest?
       // User updatedUser = dao.saveOrUpdate(dao);
    String newLastName = "Smurf";
    User userToUpdate = (User) genericDao.getById(3);
    userToUpdate.setLastName(newLastName);
    genericDao.saveOrUpdate(userToUpdate);
    User retrievedUser = (User) genericDao.getById(3);
    assertEquals(userToUpdate, retrievedUser);
    log.info("in save or update test");

    }
   /** To test that deleting a user results in deleted roles:
    Create a user that has at least one role (this might already be part of your cleandb.sql).
    Get the role id/s for that user's roles (hang on to these as you'll need them below).
    Delete the user.
    Assert the user was deleted (getting the user results in null - this part is like your typical delete test)
    Attempt to get the role/s by id.
    Assert that the role/s is/are null.
            2. To test that deleting a role does not delete the user:
    Create a user that has at least one role (this might already be part of your cleandb.sql).
    Get the role/s for that user.
    Delete the role/s.
            Verify (assert) the role/s was/were deleted.
    Get the user.
            Verify (assert) the the user still exists. (edited)*/
    @Test
    void deleteSuccess() {
        //how many do we have to start
        List allUsers = genericDao.getAll();
        GenericDao<UserRoles> roleDao = new GenericDao(UserRoles.class);
        assertEquals(6, allUsers.size());
        //the one to delete
        User toDelete = (User) genericDao.getById(3);
        Set<UserRoles> rolesToDelete = toDelete.getRoles();
        int idOfRole = 0;
        for (UserRoles roles: rolesToDelete) {
            idOfRole = roles.getId();
        }
        //delete the user
        genericDao.delete(toDelete);
        //Assert the user was deleted (getting the user results in null
        assertNull(genericDao.getById(3));
        // Attempt to get the role/s by id.
        //assert that the user's roles are gone, THIS DOES NOT WORK
        assertEquals(null, roleDao.getById(idOfRole));


    }

    /** TODO
     * Verifies Delete.
     * Think about testing the delete scenarios in one-to-many relationships more fully.
     * For example, if a user is deleted, what should happen to that user's roles? What if a role is deleted?
     * Write tests to make sure whatever should happen, does happen.
   NOt sure i need this test.....???
    @Test
    void deleteWithRolesSuccess() {
        //delete a user who has multiple roles
        User toDelete = (User) genericDao.getById(3);
        //what if I only want to delete one role if a user has multiple roles??
        Set<UserRoles> rolesToDelete = toDelete.getRoles();
        logger.debug("roles to delete: "+ rolesToDelete);

        genericDao.deleteMultiple(UserRoles);
        genericDao.delete(toDelete);
        //user's roles should be null
        assertNull(toDelete.getRoles());

    }
     */
    /**
     * Verifies a user is returned correctly based on id
     * compare different fields
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = (User) genericDao.getById(1);
        assertEquals("Joe", retrievedUser.getFirstName());
        assertEquals("Coyne", retrievedUser.getLastName());
        assertEquals("jcoyne", retrievedUser.getUserName());
        assertEquals("beast@mail", retrievedUser.getEmail());
        assertNotNull(retrievedUser);
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("Fred", "Flintstone", "fflintstone", "meaty", "meaty@sharks");
        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = (User) genericDao.getById(id);
        assertEquals(newUser, insertedUser);`   
    }
    /**
     * Verify successful insert of a user with user role
     */
    @Test
    void insertWithRolesSuccess() {

        User newUser = new User("Fred", "Flintstone", "fflintstone", "meaty", "meaty@sharks");
        String roleName = "admin";
        String usersName= "Sally";
        //need to access both objects, bidirectionality
        UserRoles role = new UserRoles(roleName, newUser, usersName);
        newUser.addRole(role);
        int id = genericDao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = (User) genericDao.getById(id);
        //verify the role was added
        assertEquals(1,insertedUser.getRoles().size());
        assertEquals(newUser, insertedUser);

    }



    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = genericDao.getPropertyByName("lastName", "cur");
        assertEquals(1, users.size());
    }
}