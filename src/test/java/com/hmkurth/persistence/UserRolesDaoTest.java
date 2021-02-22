package com.hmkurth.persistence;

import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
import com.hmkurth.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
class UserRolesDaoTest {
    /**
     * The Dao.
     */
    UserRolesDao dao;
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

        dao = new UserRolesDao();
    }

    /**
     * verifies Gets all users success.
     */
    @Test
    void getAllUserRolesSuccess() {
        List<UserRoles> userRoles = dao.getAllUserRoles();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, userRoles.size());//
        logger.info("get all userRoles test: all userRoles;" + dao.getAllUserRoles());
    }

    /**
     * Verifies Gets users by last name.
     */
    @Test
    void getUserRolesByNameLikeSuccess() {
        List<UserRoles> userRoles = dao.getUserRolesByName("admin");
        assertEquals(1, userRoles.size());
        List<UserRoles> users2 = dao.getUserRolesByName("all");
        assertEquals(2, users2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {
    String newRoleName = "readOnly";
    UserRoles toUpdate = dao.getById(3);
    toUpdate.setRoleName(newRoleName);
    dao.saveOrUpdate(toUpdate);
    UserRoles retrievedRole = dao.getById(3);
    assertEquals(newRoleName, retrievedRole.getRoleName());
    logger.info("update role success test");

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
     * Verifies a userRole is returned correctly based on id
     * compare different fields
     */
    @Test
    void getByIdSuccess() {
        UserRoles retrievedUserRoles = dao.getById(1);
        assertEquals("regular", retrievedUserRoles.getRoleName());
        assertNotNull(retrievedUserRoles);
        //TODO compare remaining values

    }
    /**
     * Verifies a role is returned correctly based on id search
     */
    @Test
    void getByIdVerifyUserSuccess() {
        UserRoles retrievedRoles = dao.getById(1);
        assertNotNull(retrievedRoles);
        assertEquals("regular", retrievedRoles.getRoleName());
        assertEquals("jcoyne", retrievedRoles.getUser().getUserName());//not sure about this one
    }

    /**
     * Verify successful insert of a user role
     */
    @Test
    void insertSuccess() {

        UserDao userDao = new UserDao();
        User user = userDao.getById(1);
       // String roleName = "write";
        UserRoles newUserRoles= new UserRoles("write", user);
        user.addRole(newUserRoles);
        int id = dao.insert(newUserRoles);
        assertNotEquals(0, id);
        assertEquals("write", newUserRoles.getRoleName());
        UserRoles insertedUserRoles= dao.getById(id);
        assertNotNull(insertedUserRoles);
        assertEquals("write", insertedUserRoles.getRoleName());
        assertNotNull(insertedUserRoles.getUser());


    }


    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<UserRoles> usersRoles = dao.getByPropertyEqual("roleName", "admin");
        assertEquals(1, usersRoles.size());
        assertEquals(3, usersRoles.get(0).getId());//not sure about this one
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<UserRoles> usersRoles = dao.getByPropertyLike("roleName", "regular");
        assertEquals(3, usersRoles.size());
    }
}