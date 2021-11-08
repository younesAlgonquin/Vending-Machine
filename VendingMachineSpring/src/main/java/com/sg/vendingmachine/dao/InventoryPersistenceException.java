package com.sg.vendingmachine.dao;


/**
 *@author Younes Boutaleb
 *email address: boutalebyounes@gmail.com
 *Current date: 
 *Purpose of the class:
 */
public class InventoryPersistenceException extends Exception{
    
    public InventoryPersistenceException(String message){
    
    
        super(message);
    }
    
        public InventoryPersistenceException(String message, Throwable cause){
    
    
        super(message, cause);
    }

}
