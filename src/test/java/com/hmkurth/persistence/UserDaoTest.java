package com.hmkurth.persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hmkurth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao dao;
    private final Logger logger = LogManager.getLogger(this.getClass());


    @BeforeEach
    void setUp() {
        dao = new UserDao();
        logger.info("running setup");
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        //assert that you get back the right number of results assuming nothing alters the table
        assertEquals(2, users.size());
    }

    @Test
    void getUsersByLastName() {
        List<User> users = dao.getUsersByLastName("k");
        assertEquals(1, users.size());

    }
}