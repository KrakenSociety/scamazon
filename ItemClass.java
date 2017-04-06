/*
 *
 */
package scamazon
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public File_to_Database(string FileName){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        FileReader fr = null;

        String url = 
        String user = 
        String password = 

        try {

            con = DriverManager.getConnection(url, user, password);
           
            CopyManager cm = new CopyManager((BaseConnection) con);

            fr = new FileReader();
            cm.copyIn("COPY items FROM STDIN WITH DELIMITER '/n'", fr);
            
        } catch (SQLException | IOException ex) {
            Logger lgr = Logger.getLogger(CopyFrom.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
                if (fr != null) {
                    fr.close();
                }

            } catch (SQLException | IOException ex) {
                Logger lgr = Logger.getLogger(CopyFrom.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
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
