/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    private Parent mainParent;
    private FXMLLoader loader;
    
    @FXML
    private Button loginButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> userList = FXCollections.observableArrayList(userOne, userTwo);
        userPicker.getItems().addAll(userList);
    }    

    @FXML
    private void handleUserPick(ActionEvent event) {
        
    }

    @FXML
    private void handlePasswdInput(ActionEvent event) throws IOException {
        handleLogin(event);
        
    }
    @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
        handleLogin(event);
    }
    
    
   private void handleLogin(ActionEvent event) throws IOException{
       
        String user = userPicker.getValue();
        String pwd = passwdTextField.getText();
        UserData data = new UserData(user,pwd);
        loader = new FXMLLoader(getClass().getResource("FXMLMainView.fxml"));
        mainParent = loader.load();
            
            Scene mainScene = new Scene(mainParent);
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLMainViewController c = loader.getController();
            c.initUserInput(data);
            if(c.login()){
                mainStage.setScene(mainScene);
                mainStage.hide();
                mainStage.show();
            }else showAlert("Invalid password!");
            
        
   }
    
    private void showAlert(String message){
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText(message);
        alert.show();
    }
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    
    
    
    
}
