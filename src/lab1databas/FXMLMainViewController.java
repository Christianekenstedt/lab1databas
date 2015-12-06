/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ConnectionToDb;

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
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Button disconnectButton;
    private String user, pwd;
    @FXML
    private TableView<Object> table;
    @FXML
    private Label connectedLabel;
    @FXML
    private Button showAlbumButton;
    @FXML
    private Button showArtistButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButn;
    @FXML
    private Label tempLabel;
    @FXML
    private ComboBox<String> searchComboBox;
    @FXML
    private MenuItem closeMenuItem1;
    @FXML
    private MenuItem addAlbumMenuItem;
    
    private ConnectionToDb connection;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String choiceOne = "Get Album by Title", choiceTwo = "Get Album by Artist";
        ObservableList<String> choices = FXCollections.observableArrayList(choiceOne, choiceTwo);
        searchComboBox.getItems().addAll(choices);
        searchComboBox.getSelectionModel().select("Get Album by Title");
        searchField.setPromptText("Type here");
        
    }    
    
    @FXML
    private void disconnectButtonHandle(ActionEvent event) throws SQLException {
        	connection.closeConnection();
    }
    
    @FXML
    private void showAlbumButtonHandle(ActionEvent event) {
        table.getColumns().clear();
    }

    @FXML
    private void showArtistButtonHandle(ActionEvent event) {
        table.getColumns().clear();
    }

    @FXML
    private void handleSearchButn(ActionEvent event) throws SQLException {
        if(!searchField.getText().isEmpty()){
            if(searchComboBox.getValue().equals("Get Album by Title")){
                new Thread(){
                    @Override
                    public void run(){
                        try {
                            ArrayList<Object> list = connection.getAlbumByTitle(searchField.getText());
                            javafx.application.Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    updateUI(list,1);
                                }
                            });
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();            
            }else if(searchComboBox.getValue().equals("Get Album by Artist")){
                new Thread(){
                    @Override
                    public void run(){
                        try {
                            ArrayList<Object> list = connection.getAlbumsByArtist(searchField.getText());
                            javafx.application.Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    updateUI(list,2);
                                }
                            });
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();
            }
        }
    }
    
    @FXML
    public void addAlbumHandle(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLView/FXMLAddView.fxml"));
        Parent root = loader.load();
        
        AddViewController c = loader.getController();
        c.initData(connection);
        c.updateComboBoxes();
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Album");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        }
    
    public void initConnection(ConnectionToDb connection){
        this.connection = connection;
        connectedLabel.setTextFill(Color.GREEN);
        connectedLabel.setText("Connected as " + connection.getConnectedUser());
    }
    
    public void updateUI(ArrayList<Object> inputList, int select){
            if(select == 1){ // 1 = AlbumByTitle
                ObservableList<Object> list =  FXCollections.observableArrayList(inputList);
                table.getColumns().clear();
                TableColumn<Object, Integer> cID = new TableColumn<>("AlbumID");
                cID.setCellValueFactory(new PropertyValueFactory("albumID"));
                TableColumn<Object, String> cName = new TableColumn<>("Title");
                cName.setCellValueFactory(new PropertyValueFactory("name"));
                TableColumn<Object, LocalDate> cDate = new TableColumn<>("Release Date");
                cDate.setCellValueFactory(new PropertyValueFactory("releaseDate"));
                table.getColumns().addAll(cID,cName,cDate);
                table.setItems(list);
                
            }else if (select == 2){ // AlbumsByArtist
                ObservableList<Object> list =  FXCollections.observableArrayList(inputList);
                table.getColumns().clear();
                TableColumn<Object, Integer> cID = new TableColumn<>("AlbumID");
                cID.setCellValueFactory(new PropertyValueFactory("albumID"));
                TableColumn<Object, String> cName = new TableColumn<>("Title");
                cName.setCellValueFactory(new PropertyValueFactory("name"));
                TableColumn<Object, LocalDate> cDate = new TableColumn<>("Release Date");
                cDate.setCellValueFactory(new PropertyValueFactory("releaseDate"));
                table.getColumns().addAll(cID,cName,cDate);
                table.setItems(list);
            }
    }
    
    /*
       _____                            _   _               __  __      _   _               _     
      / ____|                          | | (_)             |  \/  |    | | | |             | |    
     | |     ___  _ __  _ __   ___  ___| |_ _  ___  _ __   | \  / | ___| |_| |__   ___   __| |___ 
     | |    / _ \| '_ \| '_ \ / _ \/ __| __| |/ _ \| '_ \  | |\/| |/ _ \ __| '_ \ / _ \ / _` / __|
     | |___| (_) | | | | | | |  __/ (__| |_| | (_) | | | | | |  | |  __/ |_| | | | (_) | (_| \__ \
      \_____\___/|_| |_|_| |_|\___|\___|\__|_|\___/|_| |_| |_|  |_|\___|\__|_| |_|\___/ \__,_|___/
    
    */
    
    /*
      _____      _            _         __  __      _   _               _     
     |  __ \    (_)          | |       |  \/  |    | | | |             | |    
     | |__) | __ ___   ____ _| |_ ___  | \  / | ___| |_| |__   ___   __| |___ 
     |  ___/ '__| \ \ / / _` | __/ _ \ | |\/| |/ _ \ __| '_ \ / _ \ / _` / __|
     | |   | |  | |\ V / (_| | ||  __/ | |  | |  __/ |_| | | | (_) | (_| \__ \
     |_|   |_|  |_| \_/ \__,_|\__\___| |_|  |_|\___|\__|_| |_|\___/ \__,_|___/
    
    */

    
    /*
      _______ ______ __  __ _____   _____ 
     |__   __|  ____|  \/  |  __ \ / ____|
        | |  | |__  | \  / | |__) | (___  
        | |  |  __| | |\/| |  ___/ \___ \ 
        | |  | |____| |  | | |     ____) |
        |_|  |______|_|  |_|_|    |_____/ 
    */
    
}
