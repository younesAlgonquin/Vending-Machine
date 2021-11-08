package com.sg.vendingmachine.dto;

import java.math.BigDecimal;


/**
 *@author Younes Boutaleb
 *email address: boutalebyounes@gmail.com
 *Current date: 
 *Purpose of the class:
 */
public enum Coins {
    
     QUARTRES(new BigDecimal("25")),
     DIMES(new BigDecimal("10")),
     NICKELS(new BigDecimal("5")),
     PENNIES(new BigDecimal("1"));
     
     public final BigDecimal value;
     private Coins (BigDecimal value){
     
         this.value = value;
     }

}
