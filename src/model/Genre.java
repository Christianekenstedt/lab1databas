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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Genre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findByGenreID", query = "SELECT g FROM Genre g WHERE g.genreID = :genreID"),
    @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = :name")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "genreID")
    private Integer genreID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private Collection<Album> albumCollection;

    public Genre() {
    }

    public Genre(Integer genreID) {
        this.genreID = genreID;
    }

    public Genre(Integer genreID, String name) {
        this.genreID = genreID;
        this.name = name;
    }

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreID != null ? genreID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.genreID == null && other.genreID != null) || (this.genreID != null && !this.genreID.equals(other.genreID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Genre[ genreID=" + genreID + " ]";
    }
    
}
