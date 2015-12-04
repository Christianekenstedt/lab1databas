package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Platform;

/**
 *
 * @author chris
 */
public class ConnectionToDb {
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
    
    public ArrayList<Object> getAlbumsByArtist(String name) throws SQLException{
        ResultSet rs = null;
        PreparedStatement albumByArtist = con.prepareStatement("SELECT Album.name, Artist.name FROM Album, Album_Artist, Artist WHERE Album.albumID = "
                + "Album_Artist.album and Album_Artist.artist = Artist.artistID AND Artist.name = ?");
        try{
            albumByArtist.clearParameters();
            albumByArtist.setString(1,name + "%");
            
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
    
    public ArrayList<Grade> getGrades() throws SQLException{
        ResultSet rs = null;
        PreparedStatement gradesPreStatement = con.prepareStatement("SELECT * FROM Genre");
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
}
