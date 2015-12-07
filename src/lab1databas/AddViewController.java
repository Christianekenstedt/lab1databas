/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.ConnectionToDb;
import model.Genre;
import model.Grade;

/**
 * FXML Controller class
 *
 * @author Christian Ekenstedt & Gustaf Holmström
 */
public class AddViewController implements Initializable {

    @FXML
    private TextField titleTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField artistTextField;
    @FXML
    private ComboBox<Genre> genreComboBox;
    @FXML
    private ComboBox<Grade> gradeComboBox;
    @FXML
    private Button addButton;
    
    private ConnectionToDb connection;
    @FXML
    private TextField nationalityTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void addButtonHandle(ActionEvent event) throws SQLException{
        String title = titleTextField.getText();
        String artist = artistTextField.getText();
        LocalDate date = datePicker.getValue();
        Genre genre = genreComboBox.getValue();
        Grade grade = gradeComboBox.getValue();
        String nationality = nationalityTextField.getText();
        
        if(title.length()>0 && artist.length() > 0 && genre.getGenreID() != null && grade.getGradeID() != null){
            Date d = Date.valueOf(date);
            connection.addAlbum(title, artist, nationality, d, genre, grade);
        }else{
            System.out.println("Fill all the fields!");
        }
        
    
    }
    
    public void initData(ConnectionToDb connection){
        this.connection = connection;
    }
    
    public void updateComboBoxes(){// Lägg till för grade också
        try {
            // TODO
            genreComboBox.setItems(FXCollections.observableArrayList(connection.getGenre()));
            gradeComboBox.setItems(FXCollections.observableArrayList(connection.getGrades()));
        } catch (SQLException ex) {
            Logger.getLogger(AddViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
