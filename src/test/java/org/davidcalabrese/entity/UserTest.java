package org.davidcalabrese.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Database;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    GenericDao<User> userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        userDao = new GenericDao<>(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void getAllUsersSuccess() {
        logger.info("in getAllUsersSuccess");
        List<User> users = userDao.getAll();
        assertEquals(100, users.size());
    }

    @Test
    public void getUserByIdSuccess() {
        logger.info("in getUserByIdSuccess");
        User user1 = userDao.getById(1);
        assertEquals("Astrix", user1.getFirstName());
    }

    @Test
    public void shouldBuildJSON() {
        JSONObject output = new JSONObject();
        User user = userDao.getById(1);
        output.put("id", user.getId());
        output.put("first_name", user.getFirstName());
        output.put("last_name", user.getLastName());
        output.put("email", user.getEmail());
        output.put("gender", user.getGender());
        output.put("role", user.getROLE());

        assertEquals("{\"role\":\"Project Manager\",\"gender\":\"Female\",\"last_name\":\"Stow\",\"id\":1,\"first_name\":\"Astrix\",\"email\":\"astow0@homestead.com\"}", output);
    }
}