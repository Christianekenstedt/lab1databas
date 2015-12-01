/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.net.URL;
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
    private String userOne = "Christian", userTwo = "Gustaf";
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
        if(userPicker.getValue().equals(userOne)){
            if(passwdTextField.getText().equals("christian")) System.out.println("Correct");
            else System.out.println("Incorrect!");
        }else if (userPicker.getValue().equals(userTwo)){
            
        }
    }
    
}
