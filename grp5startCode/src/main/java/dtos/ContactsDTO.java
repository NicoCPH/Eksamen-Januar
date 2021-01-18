/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Contact;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicol
 */
public class ContactsDTO {
    private String name;
    private String email;
    private String company;
    private String jobtitle;
    private Integer phone;

     public ContactsDTO(Contact C) {
       this.name = C.getName();
       this.email = C.getEmail();
       this.company = C.getCompany();
       this.jobtitle = C.getJobtitle();
       this.phone = C.getPhone();
}
 List<ContactDTO> all = new ArrayList();

    public ContactsDTO(List<Contact> contactEntities) {
        contactEntities.forEach((c) -> {
            all.add(new ContactDTO(c));
        });
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
     
     

}
