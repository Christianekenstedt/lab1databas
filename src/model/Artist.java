/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Christian Ekenstedt & Gustaf Holmström
 */
@Entity
@Table(name = "Artist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a"),
    @NamedQuery(name = "Artist.findByArtistID", query = "SELECT a FROM Artist a WHERE a.artistID = :artistID"),
    @NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name = :name"),
    @NamedQuery(name = "Artist.findByNationality", query = "SELECT a FROM Artist a WHERE a.nationality = :nationality")})
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "artistID")
    private Integer artistID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "nationality")
    private String nationality;
    @ManyToMany(mappedBy = "artistCollection")
    private Collection<Album> albumCollection;

    /**
     *
     */
    public Artist() {
    }

    /**
     *
     * @param artistID
     */
    public Artist(Integer artistID) {
        this.artistID = artistID;
    }

    /**
     *
     * @param artistID
     * @param name
     * @param nationality
     */
    public Artist(Integer artistID, String name, String nationality) {
        this.artistID = artistID;
        this.name = name;
        this.nationality = nationality;
    }

    /**
     *
     * @return
     */
    public Integer getArtistID() {
        return artistID;
    }

    /**
     *
     * @param artistID
     */
    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
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
    public String getNationality() {
        return nationality;
    }

    /**
     *
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
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
        hash += (artistID != null ? artistID.hashCode() : 0);
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
        if (!(object instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) object;
        if ((this.artistID == null && other.artistID != null) || (this.artistID != null && !this.artistID.equals(other.artistID))) {
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
        return "model.Artist[ artistID=" + artistID + " ]";
    }
    
}
