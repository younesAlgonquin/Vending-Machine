package com.sg.vendingmachine.controllers;

import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.InventoryService;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.ui.InventoryView;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */

//@Component
public class InventoryController {

   //@Autowired
    private InventoryService service;
    //@Autowired
    private InventoryView view;

    
    public InventoryController(InventoryService service, InventoryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        int option = 0;

        try {

            service.startSessionAuditBanner();
            loadInventory();
            while (option != 2) {
                try {

                    displayItems();
                    option = view.getUserOption();

                    switch (option) {

                        case 1:
                            //write to audit file when vending is successful
                            displayTransactionResut();
                            break;

                        case 2:
                            view.exitMessage();
                            service.endSessionAuditBanner();
                            break;
                    }

                } catch (InventoryPersistenceException
                        | InsufficientFundsException
                        | NoItemInventoryException ipe) {

                    System.out.println(ipe.getMessage());
                }
            }
            saveInventory();
        } catch (InventoryPersistenceException ipe) {

            System.out.println(ipe.getMessage());

        }
    }//end main

    public void displayItems() {

        List<Item> itemsList = service.getListOfItems();
        view.displayItems(itemsList);

    }

    public void displayTransactionResut() throws
            InsufficientFundsException,
            NoItemInventoryException,
            InventoryPersistenceException {

        BigDecimal moneyAmount = view.getAmount();
        int itemIDSelected = view.selectItem();
        Change change = service.executeTransaction(moneyAmount, itemIDSelected);

        view.displayChange(change);

    }

    public void loadInventory() throws InventoryPersistenceException {

        service.readInventoryRecords();

    }

    public void saveInventory() throws InventoryPersistenceException {

        service.writeInventoryRecords();

    }

}//end class
