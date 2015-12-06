package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christian Ekenstedt & Gustaf Holmström
 */
public interface DBCommunication {
    
    public void addAlbum(String title, String artist, String nationality, Date date, Genre genre, Grade grade)throws SQLException;
    public ArrayList<Object> getAlbumsByArtist(String name)throws SQLException;
    public ArrayList<Object> getAlbumByTitle(String name)throws SQLException;
    public ArrayList<Genre> getGenre()throws SQLException;
    public ArrayList<Grade> getGrades()throws SQLException;
    public ArrayList<Object> getAlbumByGenre(int genre) throws SQLException;
    public ArrayList<Object> getAlbumByGrade(int grade) throws SQLException;
}   
