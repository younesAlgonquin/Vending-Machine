package com.sg.vendingmachin;

import com.sg.vendingmachine.controllers.InventoryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *@author Younes Boutaleb
 *email address: boutalebyounes@gmail.com
 *Current date: 
 *Purpose of the class:
 */
public class App {
    
    public static void main(String[] args) {
        
       
//        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//        appContext.scan("com.sg.vendingmachine");
//        appContext.refresh();
//        
//        InventoryController inventoryController = appContext.getBean("inventoryController", InventoryController.class);
//        
//        inventoryController.run();
//    

        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        InventoryController inventoryController = 
           ctx.getBean("inventoryController", InventoryController.class);
        inventoryController.run();
        
    }//end main
   
}//end class
