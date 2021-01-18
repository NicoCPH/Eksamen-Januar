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
    
    /*public ContactDTO deletePerson(int id) throws ContactNotFoundException{
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        if(p == null){
            throw new ContactNotFoundException("Could not delete, provided id does not exist");
        }
        Adress address = p.getAdress();
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.adress.id = :id");
        q.setParameter("id", address.getId());
        
        try {
            em.getTransaction().begin();
            if(q.getResultList().size() > 1){
                p.getAdress().getPerson().remove(p);
                em.remove(p);
            }else{
                em.remove(p);
                em.remove(address);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ContactDTO(p);
    }
*/
    public ContactDTO editContact(ContactDTO c) throws ContactNotFoundException {
        EntityManager em = emf.createEntityManager();
        Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class).setParameter("email", c.getEmail()).getSingleResult();
        if (contact == null) {
            throw new ContactNotFoundException("Requested Person with "+ c.getEmail()+ " does not exist");
        }
        contact.setName(c.getName());
        contact.setEmail(c.getEmail());
        contact.setCompany(c.getCompany());
        contact.setJobtitle(c.getJobtitle());
        contact.setPhone(c.getPhone());
        try {
            em.getTransaction().begin();
            em.merge(contact);
            em.getTransaction().commit();
            return new ContactDTO(contact);
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
    
     public ContactDTO getContact(String email) throws ContactNotFoundException  {
        EntityManager em = emf.createEntityManager();
        try {
            Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class).setParameter("email", email).getSingleResult();
           if (contact == null) {
               throw new ContactNotFoundException("Requested contact does not exist");
            } else {
               return new ContactDTO(contact);
            }
            
        
        }finally {
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
