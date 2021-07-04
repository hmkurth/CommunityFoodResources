package com.hmkurth.persistence;

import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.Location;
import com.hmkurth.entity.ResourceOwner;
import com.hmkurth.entity.Type;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Food resource test.
 */
@Log4j2
/**
 * The type food resource  dao test.
 */
public class FoodResourceTest {
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


        genericDao = new GenericDao(FoodResource.class);
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
        String s1=Boolean.toString(false);
        List<FoodResource> foodResources = genericDao.getByPropertyEqual("name","SNAP Program");
        List<FoodResource> verifiedResources = genericDao.getByPropertyEqual("verificationStatus", s1);
        assertEquals(1, foodResources.size());
        assertEquals(4, foodResources.get(0).getId());
        assertEquals(0, verifiedResources.size());
        assertEquals(4, verifiedResources.get(0).getId());
    }

    /**
     * Verify successful get by propertywith a boolean
     */
    @Test
    void getByPropertyEqualToBoolean() {
        List<FoodResource> verifiedResources = genericDao.getByPropertyEqualToBoolean("verificationStatus", true);
        assertEquals(0, verifiedResources.size());
       // assertEquals(4, verifiedResources.get(0).getId());
    }


    /**
     * Verifies Gets FoodResources by an attribute
     */
    @Test
    void getFoodResourcesByNameLikeSuccess() {
        List<FoodResource> foodResources = genericDao.getEntityByName("documentation","ssn");
        assertEquals(1, foodResources.size());
        List<FoodResource> foodResources2 = genericDao.getEntityByName("name", "church");
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
        FoodResource retrievedResource = (FoodResource) genericDao.getById(3);
        assertEquals(foodResourcesToUpdate, retrievedResource);
        log.info("in save or update test");

    }

    /**
     * Delete success.
     */
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

    /**
     * Gets by id success.
     */
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
     * TEST ALL FIELDS TO BE THOROUGH
     * going to need to bring in all the other entities to insert with their daos
     */
    @Test
    void insertSuccess() {

    //new location dao to get the location
    GenericDao locationDao = new GenericDao(Location.class);
    GenericDao tDao = new GenericDao(Type.class);
    GenericDao oDao = new GenericDao(ResourceOwner.class);
    //get a location to test
    Location location = (Location) locationDao.getById(2);
    Type type = (Type) tDao.getById(3);
    ResourceOwner owner = (ResourceOwner) oDao.getById(3);

        FoodResource newFoodResource = new FoodResource(8,
                "MUNCH ",
                type,
                owner,
                "kids food truck",
                location,
                "id",
                true);
        int id = genericDao.insert(newFoodResource);
        assertNotEquals(0,id);
        FoodResource insertedResource = (FoodResource) genericDao.getById(id);
        assertEquals(newFoodResource, insertedResource);
    }


    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {

        List<FoodResource> FoodResources = genericDao.getPropertyByName("description", "free pantry");
        assertEquals(2, FoodResources.size());
    }
}