package com.hmkurth.persistence;
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
        List<User> usersz = dao.getUsersByLastName("Z");
        assertEquals(0, usersz.size());
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
    assertEquals(newLastName, retrievedUser.getLastName());
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
        assertNotNull(retrievedUser);
        //TODO compare remaining values

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
        assertEquals("Fred", insertedUser.getFirstName());
        assertEquals("Flintstone", insertedUser.getLastName());
        assertEquals("fflintstone", insertedUser.getUserName());
        assertEquals("meaty", insertedUser.getPassword());
        assertEquals("meaty@sharks", insertedUser.getEmail());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        //  review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "Curry");
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