package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.InventoryAuditDao;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import org.springframework.stereotype.Component;


/**
 *@author Younes Boutaleb
 *email address: boutalebyounes@gmail.com
 *Current date: 
 *Purpose of the class:
 */

@Component
public class InventoryAuditDaoImplStub implements InventoryAuditDao{

    

    @Override
    public void writeAuditEntry(String entry) throws InventoryPersistenceException {
        //do nothing
    }
    
}
