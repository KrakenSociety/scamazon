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
    
    public Vector<ItemClass> shoppingList = new Vector<ItemClass>(0, 5);
    
    public double totalPrice = 0.0;
    
    public double salesTax = 0.1;
    
    public double calcTotalPrice(){
        for(int i = 0; i < shoppingList.size(); ++i){
            totalPrice += shoppingList[i].getPrice();
        }
        salesTax *= totalPrice;
        totalPrice += salesTax;
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
