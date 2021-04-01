package com.hmkurth.persistence;

import com.hmkurth.entity.Contact;
import com.hmkurth.entity.Location;
import com.hmkurth.test.util.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Log4j2
public class LocationDaoTest {


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


        genericDao = new GenericDao(Location.class);
    }

    /**
     * verifies Gets all locations success.
     */
    @Test
    void getAllLocationsSuccess() {
        List<Location> locations = genericDao.getAll();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(6, locations.size());
        log.info("get all locations test: all locations;" + genericDao.getAll());
    }
    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Location> locations = genericDao.getByPropertyEqual("nameDesc", "Easter Seals");
        assertEquals(1, locations.size());
        assertEquals(5, locations.get(0).getId());
    }


    /**
     * Verifies Gets locations by an attribute
     */
    @Test
    void getLocationsByLastNameLikeSuccess() {
        List<Location> locations = genericDao.getEntityByName("city","M");
        assertEquals(6, locations.size());
        List<Location> locations2 = genericDao.getEntityByName("zip", "58");
        assertEquals(1, locations2.size());
    }

    /**
     * Verifies Save or update.
     */
    @Test
    void saveOrUpdateSuccess() {

        String newLastName = "Church Pantry";
        Location locationsToUpdate = (Location) genericDao.getById(3);//mike Voit
        locationsToUpdate.setNameDesc(newLastName);
        genericDao.saveOrUpdate(locationsToUpdate);
        Location retrievedUser = (Location) genericDao.getById(3);
        assertEquals(locationsToUpdate, retrievedUser);
        log.info("in save or update test");

    }

    @Test
    void deleteSuccess() {
        //how many do we have to start
        List allLocations = genericDao.getAll();
        assertEquals(6, allLocations.size());
        Location toDelete = (Location) genericDao.getById(3);
        genericDao.delete(toDelete);
        assertNull(genericDao.getById(3));
        List locations = genericDao.getAll();
        assertEquals(5, locations.size());

    }

    @Test
    void getByIdSuccess() {
        Location retrievedUser = (Location) genericDao.getById(1);
        assertEquals("eastside near lansing", retrievedUser.getNameDesc());
        assertEquals("422 Lansing", retrievedUser.getStreetAddressOrIntersection());
        assertEquals("near front of property", retrievedUser.getComments());
        assertNotNull(retrievedUser);
    }

    /**
     * Verify successful insert of a location
     * */
    @Test
    void insertSuccess() {

        Location newLocation = new Location("Beacon", "322 Jones st", "Madison", "WI", "56987");
        int id = genericDao.insert(newLocation);
        assertNotEquals(0,id);
        Location insertedUser = (Location) genericDao.getById(id);
        assertEquals(newLocation, insertedUser);
    }


    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Location> locations = genericDao.getPropertyByName("streetAddressOrIntersection", "bert");
        assertEquals(1, locations.size());
    }
}