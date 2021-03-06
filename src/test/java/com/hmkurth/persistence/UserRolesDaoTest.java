package com.hmkurth.persistence;

import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
@Log4j2
class UserRolesDaoTest {
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


        genericDao = new GenericDao(UserRoles.class);
    }

    /**
     * verifies Gets all users success.
     */
    @Test
    void getAllUserRolesSuccess() {
        List<UserRoles> userRoles = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, userRoles.size());//
        log.info("get all userRoles test: all userRoles;" + genericDao.getAll());
    }

    /**
     * Verifies Gets users by last name.
     */
    @Test
    void getUserRolesByNameLikeSuccess() {
        List<UserRoles> userRoles = genericDao.getEntityByName("roleName", "all");
        assertEquals(2, userRoles.size());
        List<UserRoles> users2 = genericDao.getEntityByName("roleName", "user");
        assertEquals(2, users2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {
    String newRoleName = "readOnly";
    UserRoles toUpdate = (UserRoles) genericDao.getById(3);
    toUpdate.setRoleName(newRoleName);
    genericDao.saveOrUpdate(toUpdate);
    UserRoles retrievedRole = (UserRoles) genericDao.getById(3);
    assertEquals(newRoleName, retrievedRole.getRoleName());
    log.info("update role success test");

    }

    /**
     * Verifies Delete.
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));

    }

    /**
     * Verifies a userRole is returned correctly based on id
     * compare different fields
     */
    @Test
    void getByIdSuccess() {
        UserRoles retrievedUserRole = (UserRoles) genericDao.getById(2);
        assertEquals("all", retrievedUserRole.getRoleName());
        assertEquals("fhensen", retrievedUserRole.getUserName());

        assertNotNull(retrievedUserRole);
    }

    /**
     * test the successful insertion of a user role
     */
    @Test
    void insertSuccess() {
       GenericDao userDao = new GenericDao(User.class);
        //get a user to insert a new role
        User toInsert = (User) userDao.getEntityByName("lastName", "Curry").get(0);

        UserRoles roles = new UserRoles("admin", toInsert, toInsert.getUserName() );
        int id = genericDao.insert(roles);
        assertNotEquals(0, id);
       int insertedRoles = roles.getId();
        //assertEquals(toInsert.getId(), insertedRoles);
    }


    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<UserRoles> usersRoles = genericDao.getByPropertyEqual("roleName", "admin");
        assertEquals(1, usersRoles.size());
        assertEquals(3, usersRoles.get(0).getId());//not sure about this one
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<UserRoles> usersRoles = genericDao.getPropertyByName("roleName", "user");
        assertEquals(2, usersRoles.size());
    }
}