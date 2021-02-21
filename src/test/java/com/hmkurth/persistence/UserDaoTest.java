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
    }

    /**
     * verifies Gets all users success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, users.size());
        logger.info("get all users test: all users;" + dao.getAllUsers());
    }

    /**
     * Verifies Gets users by last name.
     */
    @Test
    void getUsersByLastNameLikeSuccess() {
        List<User> users = dao.getUsersByLastName("K");
        assertEquals(2, users.size());
        List<User> users2 = dao.getUsersByLastName("Z");
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
    User userToUpdate = dao.getById(3);
    userToUpdate.setLastName(newLastName);
    dao.saveOrUpdate(userToUpdate);
    User retrievedUser = dao.getById(3);
    assertEquals(userToUpdate, retrievedUser);
    logger.info("");

    }

    /**
     * Verifies Delete.
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));

    }

    /**
     * Verifies a user is returned correctly based on id
     * compare different fields
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(1);
        assertEquals("Joe", retrievedUser.getFirstName());
        assertEquals("Coyne", retrievedUser.getLastName());
        assertEquals("jcoyne'", retrievedUser.getUserName());
        assertEquals("beast@mail", retrievedUser.getEmail());
        assertNotNull(retrievedUser);
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("Fred", "Flintstone", "fflintstone", "meaty", "meaty@sharks");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
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
        UserRoles role = new UserRoles(usersName, roleName, newUser);
        newUser.addRole(role);
        int id = dao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        //verify the role was added
        assertEquals(1,insertedUser.getRoles().size());
        assertEquals(newUser, insertedUser);

    }


    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyEqual("lastName", "Curry");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "c");
        assertEquals(3, users.size());
    }
}