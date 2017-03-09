/*
 *
 */

/**
 *
 * @author Kyle Beckley
 */
public abstract class ItemClass {
    private double Price;
    private String Isbn;
    private String Description;
    private String Category;
    private int    Rating;
    
    public ItemClass(){
        
    }
    
    public double getPrice(){
        return Price;
    }
    
    public void setPrice(double newPrice){
        Price = newPrice;
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
}
