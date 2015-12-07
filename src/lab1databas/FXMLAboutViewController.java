/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Christian Ekenstedt & Gustaf Holmström
 */
public class FXMLAboutViewController implements Initializable {

    @FXML
    private Label hostLabel;
    @FXML
    private Label databaseLabel;
    @FXML
    private Label userLabel;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hostLabel.setAlignment(Pos.CENTER);
    }
    
    public void initData(String host, String database, String user){
        hostLabel.setText("Host: " + host);
        databaseLabel.setText("Database: " + database);
        userLabel.setText("User connected: " + user);
    }
    
}
