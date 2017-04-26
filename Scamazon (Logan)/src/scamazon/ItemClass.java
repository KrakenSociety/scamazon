/*
 *
 */
package scamazon;

/**
 *
 * @author Kyle Beckley
 */
public class ItemClass {
    private String  ItemName;
    private double  Price;
    private String  Seller;
    private String  Isbn;
    private String  Description;
    private String  Category;
    private int     Rating;
    private int     Stock;          //false means out of stock
    
    public ItemClass(){
        
    }
    
    public void print (){
        System.out.println(getItemName());
        System.out.println(getPrice());
        System.out.println(getSeller());
        System.out.println(getIsbn());
        System.out.println(getDescription());
        System.out.println(getCategory());
        System.out.println(getRating());
        System.out.println(getStock());   
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
    
    public int getStock(){
        return Stock;
    }
    
    public void setStock(int newStock){
        Stock = newStock;
    }
    
    public String displayItem(){
        String entireItem =
        "Item Name: " + getItemName() + "\n" +
        "Price: $" + getPrice() + "\n" +
        "Seller: " + getSeller() + "\n" +
        "ISBN: " + getIsbn() + "\n" +
        "Disc: " + getDescription() + "\n" +
        "Category: " + getCategory() + "\n" +
        "Rating: " + getRating() + "\n" +
        "Remaining Stock: " + getStock();
        return entireItem;
    }
    
}

   
