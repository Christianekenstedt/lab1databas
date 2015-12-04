/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Album;
import model.Genre;
import model.Grade;

/**
 * FXML Controller class
 *
 * @author chris
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   /* @FXML
    private void addButtonHandle(ActionEvent event) throws SQLException{
        new Thread(){
            @Override
            public void run(){
                try {
                    ArrayList<Grade> list = getGrades();
                    gradeComboBox.setItems(FXCollections.observableList(list));
                } catch (SQLException ex) {
                    Logger.getLogger(AddViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }*/
    /*
    private ArrayList<Grade> getGrades() throws SQLException{
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
    }*/
    
}