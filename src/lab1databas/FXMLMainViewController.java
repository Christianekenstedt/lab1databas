/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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
        String database = "medialibrary";
        String server ="jdbc:mysql://db.christianekenstedt.se:3306/" + database +
			"?UseClientEnc=UTF8";
        
        try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(server, user, pwd);
			System.out.println("Connected!");
                        return true;
        }
		catch(Exception e) {
                    // Here we should throw the exception to the calling method and handle it there.
                        return false;
		}
        finally {
        	//Maybe something important here.
                /*try {
        		if(con != null) {
        			con.close();
        			System.out.println("Connection closed.");
        		}
        	} catch(SQLException e) {}
        */
        }
    }
    
    public void closeConnection() throws SQLException{
        try {
        		if(con != null) {
        			con.close();
        			System.out.println("Connection closed.");
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
    
}
