package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */
public interface InventoryDao {

    public List<Item> getListOfItems();

    public Item getItem(int itemIDSelected);

    public Item updateInventory(int itemIDSelected, Item item);

    public void readInventoryRecords()throws InventoryPersistenceException;

    public void writeInventoryRecords()throws InventoryPersistenceException;

}
