package com.hmkurth.persistence;

import com.hmkurth.entity.FoodResource;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class FoodResourceTest {



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


        genericDao = new GenericDao(FoodResource.class);
    }

    /**
     * verifies Gets all FoodResources success.
     */
    @Test
    void getAllFoodResourcesSuccess() {
        List<FoodResource> foodResources = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, foodResources.size());
        log.info("get all FoodResources test: all FoodResources;" + genericDao.getAll());
    }
    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<FoodResource> foodResources = genericDao.getByPropertyEqual("deliveryOffered", "1");
        assertEquals(1, foodResources.size());
        assertEquals(5, foodResources.get(0).getId());
    }


    /**
     * Verifies Gets FoodResources by an attribute
     */
    @Test
    void getFoodResourcesByLastNameLikeSuccess() {
        List<FoodResource> foodResources = genericDao.getEntityByName("city","M");
        assertEquals(6, foodResources.size());
        List<FoodResource> foodResources2 = genericDao.getEntityByName("zip", "58");
        assertEquals(1, foodResources2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {

        String newName = "Church Pantry";
        FoodResource foodResourcesToUpdate = (FoodResource) genericDao.getById(3);//mike Voit
        foodResourcesToUpdate.setName(newName);
        genericDao.saveOrUpdate(foodResourcesToUpdate);
        FoodResource retrievedUser = (FoodResource) genericDao.getById(3);
        assertEquals(foodResourcesToUpdate, retrievedUser);
        log.info("in save or update test");

    }

    @Test
    void deleteSuccess() {
        //how many do we have to start
        List allFoodResources = genericDao.getAll();
        assertEquals(6, allFoodResources.size());
        FoodResource toDelete = (FoodResource) genericDao.getById(3);
        genericDao.delete(toDelete);
        assertNull(genericDao.getById(3));
        List FoodResources = genericDao.getAll();
        assertEquals(5, FoodResources.size());

    }

    @Test
    void getByIdSuccess() {
        FoodResource retrievedResource = (FoodResource) genericDao.getById(1);
        assertEquals("free pantry on johnson ", retrievedResource.getName());
        assertEquals("free pantry", retrievedResource.getDescription());
        assertEquals("social security number and id", retrievedResource.getDocumentation());
        assertNotNull(retrievedResource);
    }

    /**
     * Verify successful insert of a FoodResource
     *
    @Test
    void insertSuccess() {

        FoodResource newFoodResource = new FoodResource("Bpnn Meal", "2", 4, 6, "west side", "ssn", 0, "gluten free");
        int id = genericDao.insert(newFoodResource);
        assertNotEquals(0,id);
        FoodResource insertedResource = (FoodResource) genericDao.getById(id);
        assertEquals(newFoodResource, insertedResource);
    }
     */

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<FoodResource> FoodResources = genericDao.getPropertyByName("streetAddressOrIntersection", "bert");
        assertEquals(1, FoodResources.size());
    }
}