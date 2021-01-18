/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicol
 */
@Entity
@Table(name = "Opportunity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opportunity.findAll", query = "SELECT o FROM Opportunity o"),
    @NamedQuery(name = "Opportunity.findByName", query = "SELECT o FROM Opportunity o WHERE o.name = :name"),
    @NamedQuery(name = "Opportunity.findByAmount", query = "SELECT o FROM Opportunity o WHERE o.amount = :amount"),
    @NamedQuery(name = "Opportunity.findByCloseDate", query = "SELECT o FROM Opportunity o WHERE o.closeDate = :closeDate"),
    @NamedQuery(name = "Opportunity.findByContactid", query = "SELECT o FROM Opportunity o WHERE o.opportunityPK.contactid = :contactid"),
    @NamedQuery(name = "Opportunity.findByOpportunityStatusname", query = "SELECT o FROM Opportunity o WHERE o.opportunityPK.opportunityStatusname = :opportunityStatusname"),
    @NamedQuery(name = "Opportunity.findById", query = "SELECT o FROM Opportunity o WHERE o.opportunityPK.id = :id")})
public class Opportunity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OpportunityPK opportunityPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "closeDate")
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    @JoinColumn(name = "Contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contact contact;
    @JoinColumn(name = "OpportunityStatus_name", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OpportunityStatus opportunityStatus;

    public Opportunity() {
    }

    public Opportunity(OpportunityPK opportunityPK) {
        this.opportunityPK = opportunityPK;
    }

    public Opportunity(OpportunityPK opportunityPK, String name) {
        this.opportunityPK = opportunityPK;
        this.name = name;
    }

    public Opportunity(int contactid, String opportunityStatusname, int id) {
        this.opportunityPK = new OpportunityPK(contactid, opportunityStatusname, id);
    }

    public OpportunityPK getOpportunityPK() {
        return opportunityPK;
    }

    public void setOpportunityPK(OpportunityPK opportunityPK) {
        this.opportunityPK = opportunityPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public OpportunityStatus getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(OpportunityStatus opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opportunityPK != null ? opportunityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opportunity)) {
            return false;
        }
        Opportunity other = (Opportunity) object;
        if ((this.opportunityPK == null && other.opportunityPK != null) || (this.opportunityPK != null && !this.opportunityPK.equals(other.opportunityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Opportunity[ opportunityPK=" + opportunityPK + " ]";
    }
    
}
