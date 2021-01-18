/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicol
 */
@Embeddable
public class OpportunityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Contact_id")
    private int contactid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "OpportunityStatus_name")
    private String opportunityStatusname;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    public OpportunityPK() {
    }

    public OpportunityPK(int contactid, String opportunityStatusname, int id) {
        this.contactid = contactid;
        this.opportunityStatusname = opportunityStatusname;
        this.id = id;
    }

    public int getContactid() {
        return contactid;
    }

    public void setContactid(int contactid) {
        this.contactid = contactid;
    }

    public String getOpportunityStatusname() {
        return opportunityStatusname;
    }

    public void setOpportunityStatusname(String opportunityStatusname) {
        this.opportunityStatusname = opportunityStatusname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) contactid;
        hash += (opportunityStatusname != null ? opportunityStatusname.hashCode() : 0);
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpportunityPK)) {
            return false;
        }
        OpportunityPK other = (OpportunityPK) object;
        if (this.contactid != other.contactid) {
            return false;
        }
        if ((this.opportunityStatusname == null && other.opportunityStatusname != null) || (this.opportunityStatusname != null && !this.opportunityStatusname.equals(other.opportunityStatusname))) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpportunityPK[ contactid=" + contactid + ", opportunityStatusname=" + opportunityStatusname + ", id=" + id + " ]";
    }
    
}
