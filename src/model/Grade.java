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
 * Grade contains methods to access the result from the database
 * @author Christian Ekenstedt & Gustaf Holmström
 */
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer gradeID;
    private String name;
    private Collection<Album> albumCollection;


    /**
     * constructor reciving integer with gradeID
     * @param gradeID
     */
    public Grade(Integer gradeID) {
        this.gradeID = gradeID;
    }

    /**
     * constructor reciving gradeID and grade name
     * @param gradeID
     * @param name
     */
    public Grade(Integer gradeID, String name) {
        this.gradeID = gradeID;
        this.name = name;
    }

    /**
     * get's the grade ID in integer
     * @return
     */
    public Integer getGradeID() {
        return gradeID;
    }

    /**
     * set's the gradeID with integer
     * @param gradeID
     */
    public void setGradeID(Integer gradeID) {
        this.gradeID = gradeID;
    }

    /**
     * get's the grade name, returns string
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set's the grade name with string
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns informaiton about grade with string
     * @return
     */
    @Override
    public String toString() {
        return getName();
    }
    
}
