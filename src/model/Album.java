/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Album")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByAlbumID", query = "SELECT a FROM Album a WHERE a.albumID = :albumID"),
    @NamedQuery(name = "Album.findByName", query = "SELECT a FROM Album a WHERE a.name = :name"),
    @NamedQuery(name = "Album.findByReleaseDate", query = "SELECT a FROM Album a WHERE a.releaseDate = :releaseDate")})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "albumID")
    private Integer albumID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "releaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @JoinTable(name = "Album_Artist", joinColumns = {
        @JoinColumn(name = "album", referencedColumnName = "albumID")}, inverseJoinColumns = {
        @JoinColumn(name = "artist", referencedColumnName = "artistID")})
    @ManyToMany
    private Collection<Artist> artistCollection;
    @JoinColumn(name = "genre", referencedColumnName = "genreID")
    @ManyToOne(optional = false)
    private Genre genre;
    @JoinColumn(name = "grade", referencedColumnName = "gradeID")
    @ManyToOne(optional = false)
    private Grade grade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private Collection<Review> reviewCollection;

    public Album() {
    }

    public Album(Integer albumID) {
        this.albumID = albumID;
    }

    public Album(Integer albumID, String name, Date releaseDate) {
        this.albumID = albumID;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @XmlTransient
    public Collection<Artist> getArtistCollection() {
        return artistCollection;
    }

    public void setArtistCollection(Collection<Artist> artistCollection) {
        this.artistCollection = artistCollection;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumID != null ? albumID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.albumID == null && other.albumID != null) || (this.albumID != null && !this.albumID.equals(other.albumID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Album[ albumID=" + albumID + " ]";
    }
    
}
