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
 * @author Christian Ekenstedt & Gustaf Holmström
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

    /**
     *
     */
    public Genre() {
    }

    /**
     *
     * @param genreID
     */
    public Genre(Integer genreID) {
        this.genreID = genreID;
    }

    /**
     *
     * @param genreID
     * @param name
     */
    public Genre(Integer genreID, String name) {
        this.genreID = genreID;
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Integer getGenreID() {
        return genreID;
    }

    /**
     *
     * @param genreID
     */
    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
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
        hash += (genreID != null ? genreID.hashCode() : 0);
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
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.genreID == null && other.genreID != null) || (this.genreID != null && !this.genreID.equals(other.genreID))) {
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
