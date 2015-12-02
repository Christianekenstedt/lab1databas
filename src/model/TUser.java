/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "T_User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUser.findAll", query = "SELECT t FROM TUser t"),
    @NamedQuery(name = "TUser.findByPersNr", query = "SELECT t FROM TUser t WHERE t.persNr = :persNr"),
    @NamedQuery(name = "TUser.findByName", query = "SELECT t FROM TUser t WHERE t.name = :name")})
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "persNr")
    private Integer persNr;
    @Column(name = "name")
    private String name;

    public TUser() {
    }

    public TUser(Integer persNr) {
        this.persNr = persNr;
    }

    public Integer getPersNr() {
        return persNr;
    }

    public void setPersNr(Integer persNr) {
        this.persNr = persNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (persNr != null ? persNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUser)) {
            return false;
        }
        TUser other = (TUser) object;
        if ((this.persNr == null && other.persNr != null) || (this.persNr != null && !this.persNr.equals(other.persNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TUser[ persNr=" + persNr + " ]";
    }
    
}
