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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByReviewID", query = "SELECT r FROM Review r WHERE r.reviewID = :reviewID"),
    @NamedQuery(name = "Review.findByText", query = "SELECT r FROM Review r WHERE r.text = :text")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reviewID")
    private Integer reviewID;
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "album", referencedColumnName = "albumID")
    @ManyToOne(optional = false)
    private Album album;
    @JoinColumn(name = "user", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private TUser user;

    /**
     *
     */
    public Review() {
    }

    /**
     *
     * @param reviewID
     */
    public Review(Integer reviewID) {
        this.reviewID = reviewID;
    }

    /**
     *
     * @return
     */
    public Integer getReviewID() {
        return reviewID;
    }

    /**
     *
     * @param reviewID
     */
    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public Album getAlbum() {
        return album;
    }

    /**
     *
     * @param album
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     *
     * @return
     */
    public TUser getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(TUser user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewID != null ? reviewID.hashCode() : 0);
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
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
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
        return "model.Review[ reviewID=" + reviewID + " ]";
    }
    
}
