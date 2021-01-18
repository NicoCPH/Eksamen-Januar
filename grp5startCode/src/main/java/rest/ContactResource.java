/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ContactDTO;
import dtos.ContactsDTO;
import errorhandling.ContactNotFoundException;
import facades.FacadeContact;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Nicol
 */
@Path("contact")
public class ContactResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private static final FacadeContact FACADE = FacadeContact.getFacadeContact(EMF);

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of ContactResource
     */
    public ContactResource() {
    }

    @Path("/create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String contact) {
        ContactDTO C = GSON.fromJson(contact, ContactDTO.class);
        ContactDTO newContact = FACADE.CreateContact(C);
        return GSON.toJson(newContact);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @Path("/all")
    @GET
    @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        ContactsDTO contacts = FACADE.getAllContacts();
        return GSON.toJson(contacts);
    }

    @Path("/{email}")
    @GET
    @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    public String getContact(@PathParam("email") String email) throws ContactNotFoundException {
        ContactDTO c = FACADE.getContact(email);
        return GSON.toJson(c);
    }

    @PUT
    @Path("update/{email}")
    @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("email") String email, String contact) throws ContactNotFoundException {
        ContactDTO cDTO = GSON.fromJson(contact, ContactDTO.class);
        cDTO.setEmail(email);
        ContactDTO updatePerson = FACADE.editContact(cDTO);
        return GSON.toJson(updatePerson);
    }

    @DELETE
    @Path("delete/{email}")
    @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String deleteContact(@PathParam("email") String email) throws ContactNotFoundException {
        ContactDTO contact = FACADE.deleteContact(email);
        return GSON.toJson(contact);
    }

}
