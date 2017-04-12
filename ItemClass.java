/*
 *
 */

import java.sql.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
/**
 *
 * @author Kyle Beckley
 */
public abstract class ItemClass {
    private String  ItemName;
    private double  Price;
    private String  Seller;
    private String  Isbn;
    private String  Description;
    private String  Category;
    private int     Rating;
    private boolean Stock;          //false means out of stock
    
    public ItemClass(){
        
    }
    
    public void setItemName(String newItemName){
        ItemName = newItemName;
    }
    
    public String getItemName(){
        return ItemName;
    }
    
    public double getPrice(){
        return Price;
    }
    
    public void setPrice(double newPrice){
        Price = newPrice;
    }
    
    public String getSeller(){
        return Seller;
    }
    
    public void setSeller(String newSeller){
        Seller = newSeller;
    }
    
    public String getIsbn(){
        return Isbn;
    }
    
    public void setIsbn(String newIsbn){
        Isbn = newIsbn;
    }
    
    public String getDescription(){
        return Description;
    }
    
    public void setDescription(String newDescription){
        Description = newDescription;
    }
    
    public String getCategory(){
        return Category;
    }
    
    public void setCategory(String newCategory){
        Category = newCategory;
    }
    
    public int getRating(){
        return Rating;
    }
    
    public void setRating(int newRating){
        Rating = newRating;
    }
    
    public boolean getStock(){
        return Stock;
    }
    
    public void setStock(boolean newStock){
        Stock = newStock;
    }
    
    public void File_to_Database(String FileName){
        
        String dbName, userName, userPassword, serverIP;
        dbName="item database";  //your database name.  In class, you have one same as your login id
        userName="kyle.beckley";  // your login id to the database
        userPassword = "50392569";  //your password to database (not the machine)
        serverIP="147.97.156.236";
        
        try {
            // Step 1: Allocate a database 'Connection' object
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+serverIP+"/"+dbName, userName, userPassword);
                //"jdbc:postgresql://hostname:port/databaseName", "username", "password"
 
            CopyManager cm = new CopyManager((BaseConnection) con);
            
            FileReader fr = new FileReader(FileName);
            cm.copyIn("COPY item FROM STDIN WITH DELIMITER '|'", fr);
           
        } catch (SQLException | IOException ex) {
            Logger lgr = Logger.getLogger(ItemClass.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }
    
    public void All_to_Database(int max){
        int j = 1;
        for (int i = 0; i < max; i++){
            String fileName = "item" + j;
            File_to_Database(fileName);
            j++;
        }
    }
}

