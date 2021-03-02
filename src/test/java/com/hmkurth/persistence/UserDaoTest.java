package com.hmkurth.persistence;
import com.hmkurth.entity.UserRoles;
import com.hmkurth.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hmkurth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
class UserDaoTest {
    /**
     * The Dao.
     */
    UserDao dao;
    GenericDao genericDao;
    private final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanDb.sql");

        dao = new UserDao();
        genericDao = new GenericDao(User.class);
    }

    /**
     * verifies Gets all users success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, users.size());
        logger.info("get all users test: all users;" + genericDao.getAll());
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
    logger.info("in save or update test");

    }

    /** TODO
     * Verifies Delete.
     * Think about testing the delete scenarios in one-to-many relationships more fully.
     * For example, if a user is deleted, what should happen to that user's roles? What if a role is deleted?
     * Write tests to make sure whatever should happen, does happen.
     */
    @Test
    void deleteWithRolesSuccess() {
      /* for UserRoles roles : genericDao.getById(3)){
           // genericDao.getById(3).removeUserRoles(roles);
        }
        genericDao.getById(3).clear();
/*

       */
        User toDelete = (User) genericDao.getById(3);
        UserRoles roleToDelete = (UserRoles) toDelete.getRoles();

        genericDao.delete(genericDao.getById(3));
        genericDao.delete(roleToDelete);
        assertNull(genericDao.getById(3));
        //user's roles should be null
        assertNull(toDelete.getRoles());

    }

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
        assertEquals(newUser, insertedUser);
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
        UserRoles role = new UserRoles(roleName, newUser);
        newUser.addRole(role);
        int id = genericDao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = (User) genericDao.getById(id);
        //verify the role was added
        assertEquals(1,insertedUser.getRoles().size());
        assertEquals(newUser, insertedUser);

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
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = genericDao.getPropertyByName("lastName", "c");
        assertEquals(3, users.size());
    }
}