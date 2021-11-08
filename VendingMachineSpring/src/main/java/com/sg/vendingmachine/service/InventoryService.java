package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */
public interface InventoryService {

    public List<Item> getListOfItems();

    public Change executeTransaction(BigDecimal moneyAmount, int itemIDSelected) throws
            InsufficientFundsException,
            NoItemInventoryException,
            InventoryPersistenceException;

    public void readInventoryRecords()throws InventoryPersistenceException;

    public void writeInventoryRecords()throws InventoryPersistenceException;
    
    public void startSessionAuditBanner() throws InventoryPersistenceException;

    public void endSessionAuditBanner() throws InventoryPersistenceException;

}
