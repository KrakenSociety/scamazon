/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scamazon;

import java.util.ArrayList;
/**
 * @author Kody Martens
 */

public class ShoppingCart {
    
    public ShoppingCart(){}
    
    ArrayList<ItemClass> shoppingList = new ArrayList<>();

    //totalPrice is the total sum of the item cost in the shoppingList
    public double totalPrice = 0.0;
    
    //salesTax is the tax that we are going to charge them
    public double salesTax = 0.1;
    
    //calcTotalPrice is a function that sums up the total price of the items
    //in the shopping cart
    public double calcTotalPrice(){
        for(int i = 0; i < shoppingList.size(); ++i){
            totalPrice += shoppingList.get(i).getPrice();
        }
        salesTax *= totalPrice; //calculate sales tax
        totalPrice += salesTax; //add sales tax to the totalPrice
        return totalPrice;
    }
    
    /**
     *
     * @param item
     */
    public void addItem(ItemClass item){
        shoppingList.add(item);
    }
    
    /**
     *
     * @param item
     */
    public void removeItem(ItemClass item){
        for(int i = 0; i < shoppingList.size(); ++i){
            if (item.getItemName().equals(shoppingList.get(i).getItemName())){
                shoppingList.remove(i);
            } else {
            }
        }
        shoppingList.trimToSize();
    }
    
    public void clearCart(){
        shoppingList.clear();
    }
}
