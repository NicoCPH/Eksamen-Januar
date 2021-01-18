package facades;

import dtos.ContactDTO;
import dtos.ContactsDTO;
import entities.Contact;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeContact {

    private static FacadeContact instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeContact() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeContact getFacadeContact(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeContact();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getRenameMeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    public ContactDTO CreateContact(ContactDTO C) {
        if ((C.getName().length() == 0) || (C.getEmail().length() == 0) ||
                (C.getCompany().length() == 0) || (C.getJobtitle().length() == 0) || (C.getPhone() == null)) {
            //throw new MissingInputException("first name or/and last name missing");
        }
       EntityManager em = emf.createEntityManager(); 
       Contact con = new Contact(C.getName(), C.getEmail(), C.getCompany(), C.getJobtitle(), C.getPhone());
        try {
            em.getTransaction().begin();
            em.persist(con);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ContactDTO(con);
    }
    
     public ContactsDTO getAllContacts() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c", Contact.class);
            return new ContactsDTO(query.getResultList());
        } finally {
            em.close();
        }

    }
}
