package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;

import javafx.application.Platform;

/**
 *
 * @author Christian Ekenstedt & Gustaf Holmström
 */
public class ConnectionToDb implements DBCommunication{
    private Connection con;
    private String username;
    private String password;
    private String host;
    private String database;
       
    public ConnectionToDb(String host, String database, String username, String password){
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        con = null;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }
    
    
    
    public boolean connectToDatabase()throws SQLException{
        String server = "jdbc:mysql://" + host + ":3306/" + database +
			"?UseClientEnc=UTF8";
        try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
                        
			con = DriverManager.getConnection(server, username, password);
			System.out.println("Connected!");
                        return true;
        }
		catch(Exception e) {
                    // Here we should throw the exception to the calling method and handle it there.
                     return false;
		}
        finally {
            
        	//Maybe something important here.
        }
    }
    
    public void closeConnection() throws SQLException{
        if(con != null) {
            con.close();
            System.out.println("Connection closed.");
            Platform.exit();
        }
    }
    
    @Override
    public void addAlbum(String title, String artist, String nationality, Date date, Genre genre, Grade grade) throws SQLException{
        
        PreparedStatement addAlbumPrepSt = con.prepareStatement("INSERT INTO Album(name, releaseDate, genre, grade) VALUES(?, ?, ?, ?)");
        PreparedStatement addArtistPrepSt = con.prepareStatement("INSERT INTO Artist(name, nationality) VALUES(?, ?)");
        PreparedStatement checkIfArtistExists = con.prepareStatement("SELECT artistID from Artist where name = ? and nationality = ?");
        PreparedStatement getAlbumID = con.prepareStatement("SELECT albumID from Album where name = ?");
                
        PreparedStatement addArtistToAlbumSt = con.prepareStatement("INSERT INTO Album_Artist(album,artist) VALUES (?,?)");
        
        try{
            int artistID;
            int albumID;
            con.setAutoCommit(false);
            addAlbumPrepSt.clearParameters();
            addArtistPrepSt.clearParameters();
            checkIfArtistExists.clearParameters();
            getAlbumID.clearParameters();
            addArtistToAlbumSt.clearParameters();
            
            checkIfArtistExists.setString(1, artist);
            checkIfArtistExists.setString(2, nationality);
            ResultSet rs = checkIfArtistExists.executeQuery();
            
            addAlbumPrepSt.setString(1, title); addAlbumPrepSt.setDate(2, date); addAlbumPrepSt.setInt(3, genre.getGenreID()); addAlbumPrepSt.setInt(4, grade.getGradeID());
            addAlbumPrepSt.execute();
            
            getAlbumID.setString(1, title);
            ResultSet rsAlbumID = getAlbumID.executeQuery();
            if(rsAlbumID.next()){
              albumID = rsAlbumID.getInt(1);
            if ( !rs.next() ){
                addArtistPrepSt.setString(1, artist); addArtistPrepSt.setString(2, nationality);
                addArtistPrepSt.execute();
                rs = checkIfArtistExists.executeQuery();
                if(rs.next()){
                   artistID = rs.getInt(1); 
                }else{
                    artistID = -1;
                }
            }else{
                artistID = rs.getInt(1);
            }
            
            addArtistToAlbumSt.setInt(1,albumID);
            addArtistToAlbumSt.setInt(2,artistID);
                
            addArtistToAlbumSt.execute();
            
            }
            
            
            con.commit();
            
        }catch(SQLException e){
            if (con != null) con.rollback();
            System.err.print("Transaction rollback");
            throw e;
        }finally{
            con.setAutoCommit(true);
        }
    
    }
    
    
    
    @Override
    public ArrayList<Object> getAlbumsByArtist(String name) throws SQLException{
        ResultSet rs = null;
        PreparedStatement albumByArtist = con.prepareStatement("SELECT Album.* FROM Album, Album_Artist, Artist WHERE Album.albumID = "
                + "Album_Artist.album and Album_Artist.artist = Artist.artistID AND Artist.name = ?");
        try{
            albumByArtist.clearParameters();
            albumByArtist.setString(1,name);
            
            rs = albumByArtist.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while(rs.next()){
                Album album = new Album(rs.getInt(1), rs.getString(2),rs.getDate(3));
                list.add(album);
            }
            return list;
        }finally{
            rs.close();
        }
    }
    
    @Override
    public ArrayList<Object> getAlbumByTitle(String name) throws SQLException{
        ResultSet rs = null;
        PreparedStatement albumByName = con.prepareStatement("SELECT * FROM Album WHERE name LIKE ?");
        try{
            albumByName.clearParameters();
            albumByName.setString(1,name + "%");
            
            rs = albumByName.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while(rs.next()){
                Album album = new Album(rs.getInt(1), rs.getString(2),rs.getDate(3));
                list.add(album);
            }
            return list;
        }finally{
            rs.close();
        }
    }
    
    @Override
    public ArrayList<Genre> getGenre() throws SQLException{
        ResultSet rs = null;
        PreparedStatement gradesPreStatement = con.prepareStatement("SELECT * FROM Genre");
        try{
            gradesPreStatement.clearParameters();
            
            rs = gradesPreStatement.executeQuery();
            ArrayList<Genre> list = new ArrayList<>();
            while(rs.next()){
                Genre genre = new Genre(rs.getInt(1), rs.getString(2));
                list.add(genre);
            }
            return list;
        }finally{
            rs.close();
        }
    }
    
    @Override
    public ArrayList<Grade> getGrades() throws SQLException{
        ResultSet rs = null;
        PreparedStatement gradesPreStatement = con.prepareStatement("SELECT * FROM Grade");
        try{
            gradesPreStatement.clearParameters();
            
            rs = gradesPreStatement.executeQuery();
            ArrayList<Grade> list = new ArrayList<>();
            while(rs.next()){
                Grade grade = new Grade(rs.getInt(1), rs.getString(2));
                list.add(grade);
            }
            return list;
        }finally{
            rs.close();
        }
    }
    public String getConnectedUser(){
        return username;
    }

    @Override
    public ArrayList<Object> getAlbumByGenre(int genre) throws SQLException {
        ResultSet rs = null;
        PreparedStatement genreByName = con.prepareStatement("SELECT * FROM Album WHERE genre = ?");
        try{
            genreByName.clearParameters();
            genreByName.setInt(1,genre);
            
            rs = genreByName.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while(rs.next()){
                Album album = new Album(rs.getInt(1), rs.getString(2),rs.getDate(3));
                list.add(album);
            }
            return list;
        }finally{
            rs.close();
        }
    }

    @Override
    public ArrayList<Object> getAlbumByGrade(int grade) throws SQLException {
        ResultSet rs = null;
        PreparedStatement gradeByName = con.prepareStatement("SELECT * FROM Album WHERE grade = ?");
        try{
            gradeByName.clearParameters();
            gradeByName.setInt(1,grade);
            
            rs = gradeByName.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while(rs.next()){
                Album album = new Album(rs.getInt(1), rs.getString(2),rs.getDate(3));
                list.add(album);
            }
            return list;
        }finally{
            rs.close();
        }
    }
}
