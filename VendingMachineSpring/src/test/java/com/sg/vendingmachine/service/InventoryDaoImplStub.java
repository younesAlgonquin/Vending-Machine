package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.InventoryDao;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */

@Component
public class InventoryDaoImplStub implements InventoryDao {

    public Item item1;

    public InventoryDaoImplStub() {

        item1 = new Item(1);
        item1.setName("Soda");
        item1.setCost(new BigDecimal("3.00"));
        item1.setQuantity(1);
    }

    public InventoryDaoImplStub(Item item1) {

        this.item1 = item1;
    }

    @Override
    public List<Item> getListOfItems() {

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        return itemsList;
    }

    @Override
    public Item getItem(int itemID) {
        
        if(itemID == item1.getItemId()){
            
            return item1;
        }else{      
            return null;
        }
    }

    @Override
    public Item updateInventory(int itemID, Item item) {
        
        if( itemID == item1.getItemId()){
            
            return item1;
        }else{
        
            return null;
        }
    }

    @Override
    public void readInventoryRecords() throws InventoryPersistenceException {
        
        //do nothing
    }

    @Override
    public void writeInventoryRecords() throws InventoryPersistenceException {
        
        //Do nothing
    }

}
