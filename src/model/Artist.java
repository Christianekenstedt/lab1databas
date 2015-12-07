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
 * Artist contains methods to access the result from the database
 * @author Christian Ekenstedt & Gustaf Holmström
 */
public class Artist implements Serializable {

    private Integer artistID;
    private String name;
    private String nationality;
    private Collection<Album> albumCollection;

    /**
     * empty constructor
     */
    public Artist() {
    }

    /**
     * Recives integer with artistID
     * @param artistID
     */
    public Artist(Integer artistID) {
        this.artistID = artistID;
    }

    /**
     * recives integer with artistID, string with name and string with nationality
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
     * returns the artist's ID in integer
     * @return
     */
    public Integer getArtistID() {
        return artistID;
    }

    /**
     * set's the artist ID, recives integer
     * @param artistID
     */
    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
    }

    /**
     * get's the artist's name in string
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set's the artist's name with string
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get's the artist's nationality
     * @return
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * set's the artist's nationality
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    /**
     * returns string with information of what it is
     * @return
     */
    @Override
    public String toString() {
        return "model.Artist[ artistID=" + artistID + " ]";
    }
    
}
