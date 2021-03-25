package com.hmkurth.persistence;

import com.hmkurth.entity.Contact;
import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
/**
 * The type Contact dao test.
 */
class ContactDaoTest {
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


        genericDao = new GenericDao(Contact.class);
    }

    /**
     * verifies Gets all contacts success.
     */
    @Test
    void getAllContactsSuccess() {
        List<Contact> contacts = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, contacts.size());
        log.info("get all users test: all users;" + genericDao.getAll());
    }
    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Contact> contacts = genericDao.getByPropertyEqual("lastName", "Planer");
        assertEquals(1, contacts.size());
        assertEquals(6, contacts.get(0).getId());
    }


    /**
     * Verifies Gets contacts by last name.
     */
    @Test
    void getContactsByLastNameLikeSuccess() {
        List<Contact> contacts = genericDao.getEntityByName("lastName","P");
        assertEquals(1, contacts.size());
        List<Contact> contact2 = genericDao.getEntityByName("lastName", "V");
        assertEquals(1, contact2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {

    String newLastName = "Smurf";
    Contact contactToUpdate = (Contact) genericDao.getById(3);//mike Voit
        contactToUpdate.setLastName(newLastName);
    genericDao.saveOrUpdate(contactToUpdate);
    Contact retrievedUser = (Contact) genericDao.getById(3);
    assertEquals(contactToUpdate, retrievedUser);
    log.info("in save or update test");

    }

    @Test
    void deleteSuccess() {
        //how many do we have to start
        List allContacts = genericDao.getAll();
        assertEquals(6, allContacts.size());
        Contact toDelete = (Contact) genericDao.getById(3);
        genericDao.delete(toDelete);
        assertNull(genericDao.getById(3));
        List contacts = genericDao.getAll();
        assertEquals(5, contacts.size());

    }

    @Test
    void getByIdSuccess() {
        Contact retrievedUser = (Contact) genericDao.getById(1);
        assertEquals("tim", retrievedUser.getFirstName());
        assertEquals("shmidt", retrievedUser.getLastName());
        assertEquals("tsm@gone.com", retrievedUser.getEmail());
        assertNotNull(retrievedUser);
    }

    /**
     * Verify successful insert of a contact
     * */
    @Test
    void insertSuccess() {

        Contact newUser = new Contact("Fred", "Flintstone", "meaty@sharks", "6089479889");
        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        Contact insertedUser = (Contact) genericDao.getById(id);
        assertEquals(newUser, insertedUser);
    }


    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Contact> users = genericDao.getPropertyByName("lastName", "er");
        assertEquals(2, users.size());
    }
}