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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Artist;

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
    private ObservableList<Object> data;
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
    
    private boolean connectToDB(String user, String pwd) throws SQLException{
        String database = "medialibrary";
        String server ="jdbc:mysql://db.christianekenstedt.se:3306/" + database +
			"?UseClientEnc=UTF8";
        
        try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(server, user, pwd);
			System.out.println("Connected!");
                        //executeQuery(con, "SELECT * FROM Employee");
                        
                        return true;
        }
		catch(Exception e) {
                    // Here we should throw the exception to the calling method and handle it there.
                    /*javax.swing.JOptionPane.showMessageDialog(null, 
				"Database error, " + e.toString());
                       */ return false;
		}
        finally {
            if(con.isValid(1)){
                connectedLabel.setTextFill(Color.GREEN);
                connectedLabel.setText("Connected as " + user.toUpperCase() );
            }else{
                connectedLabel.setTextFill(Color.RED);
                connectedLabel.setText("Not connected");
            }
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
    
    public boolean login() throws SQLException{
        return connectToDB(user,pwd);
    }
    public static void executeQuery(Connection con, String query) throws SQLException {
            new Thread(){
                @Override
                public void run(){
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
                    catch (SQLException ex) {
                        Logger.getLogger(FXMLMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }                    finally {
                        if (stmt != null) { 
                            try { 
                                stmt.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }.start();
    }
    // TEST MED PREPARED STATEMENTS OCH ATT GÖRA OM OBJEKT FRÅN DBn.
    public ArrayList<Artist> getArtistByName(String name) throws SQLException{
        ResultSet rs = null;
        PreparedStatement artistByName = con.prepareStatement("SELECT * FROM Artist WHERE name = ?");
        try{
            artistByName.clearParameters();
            artistByName.setString(1,name);
            rs = artistByName.executeQuery();
            ArrayList<Artist> list = new ArrayList<Artist>();
            while(rs.next()){
                Artist artist = new Artist(rs.getInt(1), rs.getString(2),rs.getString(3));
                list.add(artist);
            }
            return list;
        }finally{
            rs.close();
        }
    }
    
    // THIS SHOULD 
     public void buildData(String tableType){
          
          data = FXCollections.observableArrayList();
          try{
            String SQL = "SELECT * from "+tableType;
            
            ResultSet rs = con.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){ // Gets the number of columns dynamically
                //We are using non property style for making dynamic table
                int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                table.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row added "+row );
                data.add(row);
            }
            table.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }

    @FXML
    private void showAlbumButtonHandle(ActionEvent event) {
        table.getColumns().clear();
        buildData("Album");
    }

    @FXML
    private void showArtistButtonHandle(ActionEvent event) {
        table.getColumns().clear();
        buildData("Artist");
    }

    @FXML
    private void handleSearchButn(ActionEvent event) throws SQLException {
        if(!searchField.getText().isEmpty()){
            
            ArrayList<Artist> list = new ArrayList<>();
            list = getArtistByName(searchField.getText());
            
            //System.out.println(list.toString());
            for(int i = 0; i < list.size(); i++){
                System.out.println("["+list.get(i).getArtistID()+"] Name: "+list.get(i).getName() +"\tNationality: "+ list.get(i).getNationality());
            }
            tempLabel.setTextFill(Color.RED);
            tempLabel.setText("OBS! OUTPUT I KONSOL!");
        }
    }
    
    private void presentTableWithList(ArrayList<Object> list){
        // VET EJ :O
    }
    
    
}
