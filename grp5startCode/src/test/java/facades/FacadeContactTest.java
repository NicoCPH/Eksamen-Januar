/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.ContactDTO;
import entities.Contact;
import errorhandling.ContactNotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Nicol
 */
public class FacadeContactTest {

//Uncomment the line below, to temporarily disable this test
//@Disabled
    private static EntityManagerFactory emf;
    private static FacadeContact facade;
    private static Contact r1, r2;

    public FacadeContactTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = FacadeContact.getFacadeContact(emf);

        EntityManager em = emf.createEntityManager();
        r1 = new Contact("julu", "email@email.dk", "TUC", "CEO", 32142123);
        r2 = new Contact("juju", "email@emails.dk", "ToC", "CEO", 32142124);;
        try {
            em.getTransaction().begin();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Contact.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testCreate() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Contact c = em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class).setParameter("email", r1.getEmail()).getSingleResult();
            em.getTransaction().commit();
            assertEquals(r1.getEmail(), c.getEmail());
        } finally {
            em.close();
        }
    }

    @Test
    public void testReadFromFacade() throws ContactNotFoundException {
        ContactDTO c = facade.getContact(r1.getEmail());
        assertEquals(r1.getJobtitle(), c.getJobtitle(), "Expects that both email from Facade and r1 are the same");
    }

    @Test
    public void testUpdateFromFacade() throws ContactNotFoundException {
        Contact C = new Contact("Julu dulus", "email@email.dk", "TUC", "CEO", 32142123);
        ContactDTO C1 = new ContactDTO(C);
        ContactDTO c = facade.editContact(C1);
        assertNotEquals(r1.getName(), c.getName());
    }

    @Test
    public void testCreateFromFacade() throws ContactNotFoundException {
        Contact C = new Contact("Julu dulu", "email@emailsd.dk", "TUCss", "CEOss", 32142133);
        ContactDTO C1 = new ContactDTO(C);
        ContactDTO c = facade.CreateContact(C1);
        assertEquals("Julu dulu", c.getName());
    }

}
