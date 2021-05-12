package com.hmkurth.persistence;

import com.hmkurth.entity.Type;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Type dao test.
 */
@Log4j2
/**
 * The type Type dao test.
 */
class TypeDaoTest {
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



        genericDao = new GenericDao(Type.class);

    }

    /**
     * verifies Gets all Types success.
     */
    @Test
    void getAllTypesSuccess() {
        List<Type> Types = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, Types.size());
        log.info("get all Types test: all Types;" + genericDao.getAll());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Type> Types = genericDao.getByPropertyEqual("name", "other");
        assertEquals(1, Types.size());
        assertEquals(6, Types.get(0).getId());
    }


    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {
        //assertions; what will be changed? number of Types? nametest?
        // Type updatedType = dao.saveOrUpdate(dao);
        String newLastName = "Community Gardens";
        Type TypeToUpdate = (Type) genericDao.getById(3);
        TypeToUpdate.setName(newLastName);
        genericDao.saveOrUpdate(TypeToUpdate);
        Type retrievedType = (Type) genericDao.getById(3);
        assertEquals(TypeToUpdate, retrievedType);
        log.info("in save or update test");

    }

    /**
     * Delete success.
     */
    @Test
    void deleteSuccess() {
        //how many do we have to start
        List allTypes = genericDao.getAll();
        GenericDao<Type> roleDao = new GenericDao(Type.class);
        assertEquals(6, allTypes.size());
        //the one to delete
        Type toDelete = (Type) genericDao.getById(4);

        genericDao.delete(toDelete);
        //Assert the Type was deleted (getting the Type results in null
        assertNull(genericDao.getById(4));

    }

    /**
     * Verifies a Type is returned correctly based on id
     * compare different fields
     */
    @Test
    void getByIdSuccess() {
        Type retrievedType = (Type) genericDao.getById(1);
        assertEquals("food pantry", retrievedType.getName());
        assertNotNull(retrievedType);
    }

    /**
     * Verify successful insert of a Type
     */
    @Test
    void insertSuccess() {

        Type newType = new Type("school meal drop off");
        int id = genericDao.insert(newType);
        assertNotEquals(0,id);
        Type insertedType = (Type) genericDao.getById(id);
        assertEquals(newType, insertedType);
    }


    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Type> Types = genericDao.getPropertyByName("name", "pantry");
        assertEquals(2, Types.size());
    }
}