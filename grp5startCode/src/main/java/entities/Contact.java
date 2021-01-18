/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nicol
 */
@Entity
@Table(name = "Contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
    @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
    @NamedQuery(name = "Contact.findByCompany", query = "SELECT c FROM Contact c WHERE c.company = :company"),
    @NamedQuery(name = "Contact.findByJobtitle", query = "SELECT c FROM Contact c WHERE c.jobtitle = :jobtitle"),
    @NamedQuery(name = "Contact.findByPhone", query = "SELECT c FROM Contact c WHERE c.phone = :phone"),
    @NamedQuery(name = "Contact.deleteAllRows", query = "DELETE from Contact"),
        })
    
public class Contact implements Serializable {

    @Size(max = 45)
    @Column(name = "name")
    private String name;

    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "company")
    private String company;
    @Size(max = 45)
    @Column(name = "jobtitle")
    private String jobtitle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private List<Opportunity> opportunityList;
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private static final long serialVersionUID = 1L;
    @Column(name = "phone")
    private Integer phone;

    public Contact(String name, String email, String company, String jobtitle, Integer phone) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.jobtitle = jobtitle;
        this.phone = phone;
   
    }

    
    
    public Contact() {
    }

  


    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
 

    @Override
    public String toString() {
        return "entities.Contact[ name=" + name + " ]";
    }

    public Contact(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

  
}
