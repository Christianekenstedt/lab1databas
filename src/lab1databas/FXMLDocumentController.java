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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author chris
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwdLabel;
    @FXML
    private ComboBox<String> userPicker;
    @FXML
    private TextField passwdTextField;
    @FXML
    private Label titleLabel;
    
    private String userOne = "christian", userTwo = "gustaf";
    private Connection con = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> userList = FXCollections.observableArrayList(userOne, userTwo);
        userPicker.getItems().addAll(userList);
    }    

    @FXML
    private void handleUserPick(ActionEvent event) {
        
    }

    @FXML
    private void handlePasswdInput(ActionEvent event) {
        String database = "medialibrary";
        String server ="jdbc:mysql://db.christianekenstedt.se:3306/" + database +
			"?UseClientEnc=UTF8";
        String user = userPicker.getValue();
        String pwd = passwdTextField.getText();
        connectToDB(server,user,pwd);
        
    }
    
    private void connectToDB(String server, String user, String pwd){
        try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(server, user, pwd);
			System.out.println("Connected!");
        }
		catch(Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, 
				"Database error, " + e.toString());
		}
        finally {
        	try {
        		if(con != null) {
        			con.close();
        			System.out.println("Connection closed.");
        		}
        	} 
        	catch(SQLException e) {}
        }
    }
    
}
