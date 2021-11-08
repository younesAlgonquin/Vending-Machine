package com.sg.vendingmachine.service;


/**
 *@author Younes Boutaleb
 *email address: boutalebyounes@gmail.com
 *Current date:
 *Purpose of the class:
 */
public class NoItemInventoryException  extends Exception{
    

    public NoItemInventoryException(String message) {

        super(message);

    }
    
    public NoItemInventoryException (String message , Throwable cause){
    
        super(message, cause);
    
    }
}
