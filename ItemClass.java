/*
 *
 */

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
}
