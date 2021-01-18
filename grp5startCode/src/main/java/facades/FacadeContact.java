package facades;

import dtos.ContactDTO;
import dtos.ContactsDTO;
import entities.Contact;
import errorhandling.ContactNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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

    public ContactDTO deleteContact(String email) throws ContactNotFoundException {
        EntityManager em = emf.createEntityManager();
        Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class).setParameter("email", email).getSingleResult();
        try {
            em.getTransaction().begin();
            em.remove(contact);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ContactDTO(contact);
    }

    public ContactDTO editContact(ContactDTO c) throws ContactNotFoundException {
        EntityManager em = emf.createEntityManager();
        Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class).setParameter("email", c.getEmail()).getSingleResult();
        System.out.println("contact fra DB " + contact.getName());
        if (contact == null) {
            throw new ContactNotFoundException("Requested Person with " + c.getEmail() + " does not exist");
        }
        contact.setName(c.getName());
        contact.setEmail(c.getEmail());
        contact.setCompany(c.getCompany());
        contact.setJobtitle(c.getJobtitle());
        contact.setPhone(c.getPhone());
        try {
            em.getTransaction().begin();
            System.out.println(c.getName());
            System.out.println(contact.getName() + " efter db");
            em.merge(contact);
            em.getTransaction().commit();
            return new ContactDTO(contact);
        } finally {
            em.close();
        }
    }

    public ContactDTO CreateContact(ContactDTO C) {
        if ((C.getName().length() == 0) || (C.getEmail().length() == 0)
                || (C.getCompany().length() == 0) || (C.getJobtitle().length() == 0) || (C.getPhone() == null)) {
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

    public ContactDTO getContact(String email) throws ContactNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class).setParameter("email", email).getSingleResult();
            if (contact == null) {
                throw new ContactNotFoundException("Requested contact does not exist");
            } else {
                return new ContactDTO(contact);
            }

        } finally {
            em.close();
        }
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
