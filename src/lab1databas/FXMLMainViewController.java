/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1databas;

import java.io.IOException;
import model.UserData;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Album;
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
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Button disconnectButton;
    private Connection con = null;
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
    private MenuItem addMenuItem;
    @FXML
    private MenuItem closeMenuItem1;
    
    
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
        	closeConnection();
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
            if(searchComboBox.getValue().equals("Get Album by Title")){
                
                new Thread(){
                    public void run() {
                        try {
                            ArrayList<Object> list = getAlbumByTitle(searchField.getText());
                            javafx.application.Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    updateUI(list);
                                }
                            });
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();
            }else if(searchComboBox.getValue().equals("Get Album by Artist")){
                //updateUI(getAlbumByTitle(searchField.getText()));
                
            }
             
        }
    }
    
    public void initUserInput(UserData data){
        user = data.getUsername();
        pwd = data.getPswd();
    }
    
    public void updateUI(ArrayList<Object> inputList){
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
    
    private ArrayList<Object> getAlbumByTitle(String name) throws SQLException{
        ResultSet rs = null;
        PreparedStatement albumByName = con.prepareStatement("SELECT * FROM Album WHERE name LIKE ?");
        try{
            albumByName.clearParameters();
            albumByName.setString(1,name + "%");
            
            rs = albumByName.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while(rs.next()){
                Album album = new Album(rs.getInt(1), rs.getString(2),rs.getDate(3));
                list.add(album);
            }
            return list;
        }finally{
            rs.close();
        }
    }
    
    private ArrayList<Object> getAlbumsByArtist(String name) throws SQLException{
        ResultSet rs = null;
        PreparedStatement albumByArtist = con.prepareStatement("SELECT Album.name, Artist.name FROM Album, Album_Artist, Artist WHERE Album.albumID = "
                + "Album_Artist.album and Album_Artist.artist = Artist.artistID AND Artist.name = ?");
        try{
            albumByArtist.clearParameters();
            albumByArtist.setString(1,name + "%");
            
            rs = albumByArtist.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while(rs.next()){
                Album album = new Album(rs.getInt(1), rs.getString(2),rs.getDate(3));
                list.add(album);
            }
            return list;
        }finally{
            rs.close();
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
    public boolean login() throws SQLException{
        return connectToDB(user,pwd);
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
    /*
      _____      _            _         __  __      _   _               _     
     |  __ \    (_)          | |       |  \/  |    | | | |             | |    
     | |__) | __ ___   ____ _| |_ ___  | \  / | ___| |_| |__   ___   __| |___ 
     |  ___/ '__| \ \ / / _` | __/ _ \ | |\/| |/ _ \ __| '_ \ / _ \ / _` / __|
     | |   | |  | |\ V / (_| | ||  __/ | |  | |  __/ |_| | | | (_) | (_| \__ \
     |_|   |_|  |_| \_/ \__,_|\__\___| |_|  |_|\___|\__|_| |_|\___/ \__,_|___/
    
    */
    private boolean connectToDB(String user, String pwd) throws SQLException{
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

    @FXML
    private void addHandle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLView/AddView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Album");
        stage.showAndWait();
    }
    
    
    
    /*
      _______ ______ __  __ _____   _____ 
     |__   __|  ____|  \/  |  __ \ / ____|
        | |  | |__  | \  / | |__) | (___  
        | |  |  __| | |\/| |  ___/ \___ \ 
        | |  | |____| |  | | |     ____) |
        |_|  |______|_|  |_|_|    |_____/ 
    */
    
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
        PreparedStatement artistByName = con.prepareStatement("SELECT * FROM Artist WHERE name LIKE ?");
        try{
            artistByName.clearParameters();
            artistByName.setString(1,name + "%");
            
            rs = artistByName.executeQuery();
            ArrayList<Artist> list = new ArrayList<>();
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
          
          ObservableList<Object> data;data = FXCollections.observableArrayList();
          try{
            String SQL = "SELECT * from " + tableType;
            
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
}
