/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scamazon;


/**
 *
 * @author Kody Martens
 */
import java.util.*;
public class ShoppingCart {
    
    //shoppingList is a vector housing the customers items that they are interested in buying
    public Vector<ItemClass> shoppingList = new Vector<ItemClass>(0, 5);
    
    //totalPrice is the total sum of the item cost in the shoppingList
    public double totalPrice = 0.0;
    
    //salesTax is the tax that we are going to charge them
    public double salesTax = 0.1;
    
    //calcTotalPrice is a function that sums up the total price of the items
    //in the shopping cart
    public double calcTotalPrice(){
        for(int i = 0; i < shoppingList.size(); ++i){
            totalPrice += shoppingList[i].getPrice();
        }
        salesTax *= totalPrice; //calculate sales tax
        totalPrice += salesTax; //add sales tax to the totalPrice
    }
    
    public void addItem(ItemClass item){
        shoppingList.addElement(item);
    }
    
    public void removeItem(ItemClass item){
        for(int i = 0; i < shoppingList.size(); ++i){
            if (item.getItemName() == shoppingList[i].getItemName()){
                shoppingList.removeElementAt(i);
            }
        }
        shoppingList.trimToSize();
    }
    
    public void clearCart(){
        shoppingList.clear();
    }
}
