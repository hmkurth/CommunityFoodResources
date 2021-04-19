package com.hmkurth.persistence;
import com.hmkurth.entity.Contact;
import com.hmkurth.entity.ResourceOwner;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hmkurth.entity.ResourceOwner;
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
 * The type ResourceOwner dao test.
 */
class ResourceOwnerDaoTest {
    /**
     * The Dao.
     */

    GenericDao resourceOwnerDao;
    GenericDao contactDao;
    GenericDao foodResourceDao;



    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanDb.sql");


        resourceOwnerDao = new GenericDao(ResourceOwner.class);

    }

    /**
     * verifies Gets all ResourceOwners success.
     */
    @Test
    void getAllResourceOwnersSuccess() {
        List<ResourceOwner> ResourceOwners = resourceOwnerDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, ResourceOwners.size());
        log.info("get all ResourceOwners test: all ResourceOwners;" + resourceOwnerDao.getAll());
    }
    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<ResourceOwner> ResourceOwners = resourceOwnerDao.getByPropertyEqual("website", "www.SNAP.com");
        assertEquals(1, ResourceOwners.size());
        assertEquals(5, ResourceOwners.get(0).getId());
    }


    /**
     * Verifies Gets ResourceOwners by name.
     */
    @Test
    void getResourceOwnersByNameLikeSuccess() {
        List<ResourceOwner> ResourceOwners = resourceOwnerDao.getEntityByName("name","Salvation Army");
        assertEquals(1, ResourceOwners.size());
        List<ResourceOwner> ResourceOwners2 = resourceOwnerDao.getEntityByName("name", "zither");
        assertEquals(0, ResourceOwners2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {
        //assertions; what will be changed? number of ResourceOwners? nametest?
        // ResourceOwner updatedResourceOwner = dao.saveOrUpdate(dao);
        String newName = "Porch Light";
        ResourceOwner ResourceOwnerToUpdate = (ResourceOwner) resourceOwnerDao.getById(3);
        ResourceOwnerToUpdate.setName(newName);
        resourceOwnerDao.saveOrUpdate(ResourceOwnerToUpdate);
        ResourceOwner retrievedResourceOwner = (ResourceOwner) resourceOwnerDao.getById(3);
        assertEquals(ResourceOwnerToUpdate, retrievedResourceOwner);
        log.info("in save or update test");

    }

    /**
     * verifies the successful deletion of a resource owner
     */
    @Test
    void deleteSuccess() {
        resourceOwnerDao.delete(resourceOwnerDao.getById(3));
        assertNull(resourceOwnerDao.getById(3));
    }

    /**
     * Verifies a ResourceOwner is returned correctly based on id
     * compare different fields

    @Test
    void getByIdSuccess() {
        ResourceOwner retrievedResourceOwner = (ResourceOwner) resourceOwnerDao.getById(4);
        assertEquals("Bethel Lutheran Church", retrievedResourceOwner.getName());
        assertEquals(6, retrievedResourceOwner.getContacts(0).get());
        assertEquals("www.christalmighty!.com", retrievedResourceOwner.getWebsite());
        assertNotNull(retrievedResourceOwner);
    }
     */
    /**
     * Verify successful insert of a ResourceOwner
     */
    @Test
    void insertSuccess() {

        ResourceOwner newResourceOwner = new ResourceOwner("Schmidts","schmiddy.coms");
        newResourceOwner.setName("Schmidts");
        newResourceOwner.setWebsite("Schmidts@food.com");

        int id = resourceOwnerDao.insert(newResourceOwner);
        assertNotEquals(0,id);
        ResourceOwner insertedResourceOwner = (ResourceOwner) resourceOwnerDao.getById(id);
        assertEquals(newResourceOwner, insertedResourceOwner);
    }
    /**
     * Verify successful insert of a ResourceOwner with contact
     */
    @Test
    void insertWithContactSuccess() {
        GenericDao cdao=new GenericDao<>(Contact.class);
        Contact contactToAdd = new Contact("Bill", "Larson", "bill@pantries.com", "6085134568");

        ResourceOwner newResourceOwner = new ResourceOwner("WIC", contactToAdd, "wic@gov" );

        //need to access both objects, bidirectionality

        int id = resourceOwnerDao.insert(newResourceOwner);
        newResourceOwner.addContact(contactToAdd);
        assertNotEquals(0,id);
        ResourceOwner insertedResourceOwner = (ResourceOwner) resourceOwnerDao.getById(id);
        //verify the contact was added
        assertEquals(cdao.getById(2), insertedResourceOwner.getContacts(0));
        assertEquals(newResourceOwner, insertedResourceOwner);

    }



    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<ResourceOwner> ResourceOwners = resourceOwnerDao.getPropertyByName("name", "church");
        assertEquals(1, ResourceOwners.size());
    }
}