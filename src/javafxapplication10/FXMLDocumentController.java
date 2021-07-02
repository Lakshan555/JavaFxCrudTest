/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication10;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Ishanka
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitel;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfPages;
    @FXML
    private TableView<Books> tvBooks;
    @FXML
    private TableColumn<Books, Integer> colId;
    @FXML
    private TableColumn<Books, String> colTitel;
    @FXML
    private TableColumn<Books, String> colAuthor;
    @FXML
    private TableColumn<Books, Integer> colYear;
    @FXML
    private TableColumn<Books, Integer> colPages;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
           if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBooks();
    }    
    
    public Connection getConnection(){
        Connection conn;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libaray", "root","");
            return conn;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }
    
    public ObservableList<Books>getBookList(){
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String qurey = "SELECT * FROM books";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(qurey);
            Books books;
            while(rs.next()){
                books = new Books(rs.getInt("id"),rs.getString("titel"), rs.getString("author"),rs.getInt("year"), rs.getInt("pages"));
                bookList.add(books);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }
    
    public void showBooks(){
        ObservableList<Books> list = getBookList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        colTitel.setCellValueFactory(new PropertyValueFactory<Books, String>("titel"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        colPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));
        
        tvBooks.setItems(list);
    }
    
    //insert
    
      private void insertRecord(){
        String query = "INSERT INTO books VALUES (" + tfId.getText() + ",'" + tfTitel.getText() + "','" + tfAuthor.getText() + "',"
                + tfYear.getText() + "," + tfPages.getText() + ")";
        executeQuery(query);
        showBooks();
    }
      
      //update
      private void updateRecord(){
        String query = "Update  books SET titel = '" + tfTitel.getText() + "', author = '" + tfAuthor.getText() + "', year = " +
                tfYear.getText() + ", pages = " + tfPages.getText() + " where id = " + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
     private void deleteButton(){
        String query = "DELETE FROM books WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
