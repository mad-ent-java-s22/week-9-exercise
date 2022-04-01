package org.davidcalabrese.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Database;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> users = userDao.getAll();

        try {
            return Response.status(200).entity(
                    new ObjectMapper().writeValueAsString(users)
            ).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e);
            return Response.status(500).entity(
                    "{ \"error\":\"Internal server error processing request\" }").build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") String idString) {
//        Database database = Database.getInstance();
//        database.runSQL("cleandb.sql");
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            return Response.status(400).entity("not a valid number").build();
        }

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(id);

        try {
            return Response.status(200).entity(
                    new ObjectMapper().writeValueAsString(user)
            ).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e);
            return Response.status(500).entity(
                    "{ \"error\":\"Internal server error processing request\" }").build();
        }
    }
}
