package dtos;

import entities.User;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nicol
 */
public class UserDTO {

    private String fname;
    private String password;


    private static EntityManagerFactory emf;

    public UserDTO(User u) {
        this.fname = u.getUserName();
        this.password = u.getUserPass();
    }

    public String getFname() {
        return fname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
