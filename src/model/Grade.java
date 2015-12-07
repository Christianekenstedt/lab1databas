/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Grade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grade.findAll", query = "SELECT g FROM Grade g"),
    @NamedQuery(name = "Grade.findByGradeID", query = "SELECT g FROM Grade g WHERE g.gradeID = :gradeID"),
    @NamedQuery(name = "Grade.findByName", query = "SELECT g FROM Grade g WHERE g.name = :name")})
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "gradeID")
    private Integer gradeID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grade")
    private Collection<Album> albumCollection;

    /**
     *
     */
    public Grade() {
    }

    /**
     *
     * @param gradeID
     */
    public Grade(Integer gradeID) {
        this.gradeID = gradeID;
    }

    /**
     *
     * @param gradeID
     * @param name
     */
    public Grade(Integer gradeID, String name) {
        this.gradeID = gradeID;
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Integer getGradeID() {
        return gradeID;
    }

    /**
     *
     * @param gradeID
     */
    public void setGradeID(Integer gradeID) {
        this.gradeID = gradeID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    /**
     *
     * @param albumCollection
     */
    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradeID != null ? gradeID.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        if ((this.gradeID == null && other.gradeID != null) || (this.gradeID != null && !this.gradeID.equals(other.gradeID))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return getName();
    }
    
}
