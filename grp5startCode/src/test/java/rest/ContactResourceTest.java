package rest;

import entities.Contact;
import entities.Role;
import entities.User;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class ContactResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Contact r1, r2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManager em = emf.createEntityManager();
        r1 = new Contact("julu", "email@email.dk", "TUC", "CEO", 32142123);
        r2 = new Contact("juju", "email@emails.dk", "ToC", "CEO", 32142124);
        try {
            em.getTransaction().begin();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Contact.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            User user = new User("user", "test");
            user.addRole(userRole);
            User admin = new User("admin", "test");
            admin.addRole(adminRole);
            User both = new User("user_admin", "test");
            both.addRole(userRole);
            both.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(both);
            //System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static String securityToken;

    //Utility method to login and set the returned securityToken
    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
        //System.out.println("TOKEN ---> " + securityToken);
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/xxx").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/xxx/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void testGetContact() throws Exception {
        login("user", "test");
        given()
                .contentType("application/json").
                header("x-access-token", securityToken)
                .get("/contact/email@email.dk").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", equalTo("julu"));
    }

    @Test
    public void testCreateContact() throws Exception {
        String json = String.format("{name: \"%s\", email: \"%s\", company: \"%s\", jobtitle: \"%s\", phone: \"%s\"}", "Julu dulu", "email@emailsd.dk", "TUCss", "CEOss", 32142133);
        login("user", "test");
        Response response = given().contentType("application/json").
                header("x-access-token", securityToken).
                body(json)
                .when()
                .post("/contact/create")
                .then()
                .extract().response();
        
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Julu dulu", response.jsonPath().getString("name"));
    }
    
    @Test
    public void testUpdateContact() throws Exception {
        String json = String.format("{name: \"%s\", email: \"%s\", company: \"%s\", jobtitle: \"%s\", phone: \"%s\"}", "Julu dulus", "email@emailsd.dk", "TUCsss", "CEOsss", 32152133);
        login("user", "test");
       Response response = given().contentType("application/json").
                header("x-access-token", securityToken).
                body(json)
                .when()
                .put("/contact/update/email@emailsd.dk")
                .then()
                .extract().response();
       
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Julu dulus", response.jsonPath().getString("name"));
    }
    @Test
    public void testDeleteContact() {
        login("user", "test");
         Response response = given().contentType("application/json").
                 header("x-access-token", securityToken)
                .when()
                .delete("/contact/delete/email@emails.dk")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
    
}
