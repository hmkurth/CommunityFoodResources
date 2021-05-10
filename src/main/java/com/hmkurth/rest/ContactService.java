package com.hmkurth.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.entity.Contact;
import com.hmkurth.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Contact service.
 */
@Log4j2
@Path("/contact")
public class ContactService {
    /**
     * Gets contact.
     *
     * @return the contact
     * @throws JsonProcessingException the json processing exception
     */
// The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "application/json"
    @Produces("application/json")
    public Response getContact() throws JsonProcessingException {
        // Return contact details from dao
        GenericDao dao = new GenericDao(Contact.class);
        List<Contact> contacts = dao.getAll();
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(contacts);
        log.info(jsonString);

        return Response.status(200).entity(jsonString).build();
    }
}