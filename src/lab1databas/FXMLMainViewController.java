/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import model.UserData;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class FXMLMainViewController implements Initializable {        
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private Menu editMenu;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Button disconnectButton;
    private Connection con = null;
    private String user, pwd;
    @FXML
    private TableView table;
    @FXML
    private TableColumn c1; 
    @FXML
    private TableColumn c2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void disconnectButtonHandle(ActionEvent event) throws SQLException {
        	closeConnection();
    }
    
    private boolean connectToDB(String user, String pwd){
        String database = "Company";
        String server ="jdbc:mysql://db.christianekenstedt.se:3306/" + database +
			"?UseClientEnc=UTF8";
        
        try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(server, user, pwd);
			System.out.println("Connected!");
                        executeQuery(con, "SELECT * FROM Employee");
                        return true;
        }
		catch(Exception e) {
                    // Here we should throw the exception to the calling method and handle it there.
                    /*javax.swing.JOptionPane.showMessageDialog(null, 
				"Database error, " + e.toString());
                       */ return false;
		}
        finally {
        	//Maybe something important here.
        }
    }
    
    public void closeConnection() throws SQLException{
        try {
        		if(con != null) {
        			con.close();
        			System.out.println("Connection closed.");
                                Platform.exit();
                        }
        } catch(SQLException e) {}
    }
    
    public void initUserInput(UserData data){
        user = data.getUsername();
        pwd = data.getPswd();
    }
    
    public boolean login(){
        return connectToDB(user,pwd);
    }
    public static void executeQuery(Connection con, String query) throws SQLException {
		Statement stmt = null;
	    try {
	    	// Execute the SQL statement
	    	stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(query);
	    	
	    	// Get the attribute names
	    	ResultSetMetaData metaData = rs.getMetaData();
	    	int ccount = metaData.getColumnCount();
                
	    	for(int c = 1; c <= ccount; c++) {
                        
	    		System.out.print(metaData.getColumnName(c) + "\t");
                        
	    	}
	    	System.out.println();
	    	// Get the attribute values
	    	while (rs.next()) {
    			// NB! This is an example, -not- the preferred way to retrieve data.
    			// You should use methods that return a specific data type, like
    			// rs.getInt(), rs.getString() or such.
    			// It's also advisable to store each tuple (row) in an object of
    			// custom type (e.g. Employee).
	    		for(int c = 1; c <= ccount; c++) {
                                
	    			System.out.print(rs.getObject(c) + "\t");
	    		}
		        System.out.println();
	    	}
	    	
	    }	
	    finally {
	    	if (stmt != null) { 
	    		stmt.close(); 
	    	}
	    }
	}
}
