package com.sg.vendingmachine.service;


/**
 *@author Younes Boutaleb
 *email address: boutalebyounes@gmail.com
 *Current date: 
 *Purpose of the class:
 */
public class InsufficientFundsException extends Exception{
    
    
    public InsufficientFundsException(String message){
    
    
        super(message);
    }
    
    
        public InsufficientFundsException(String message, Throwable cause){
    
    
        super(message, cause);
    }

}
