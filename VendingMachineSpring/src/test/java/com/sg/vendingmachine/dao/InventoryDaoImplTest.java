/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author pc
 */
public class InventoryDaoImplTest {

    InventoryDaoImpl testDao;

    public InventoryDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        String file = "testInventory.txt";

        PrintWriter printer = new PrintWriter(new FileWriter(file));

        printer.println("1::Soda::3.40::1");
        printer.println("2::Juice::3.60::2");

        printer.flush();
        printer.close();

        testDao = new InventoryDaoImpl(file);
        testDao.readInventoryRecords();
    }

    @Test
    public void getItemTest() {

        /**
         * Test plan 
         * Create two item1, item2 Items same as in the file
         * testDao.getItem(item1ID) -> item1 testDao.getItem(item2ID) -> item2
         * testDao.getItem(inexistingItemId) -> null
         *
         */
        // Create our method test inputs
        //Item1
        int itemId1 = 1;
        Item item1 = new Item(itemId1);
        item1.setName("Soda");
        item1.setCost(new BigDecimal("3.40"));
        item1.setQuantity(1);

        //Item1
        int itemId2 = 2;
        Item item2 = new Item(itemId2);
        item2.setName("Juice");
        item2.setCost(new BigDecimal("3.60"));
        item2.setQuantity(2);

        Item tetsItem1 = testDao.getItem(1);
        Item tetsItem2 = testDao.getItem(2);
        Item tetsItem3 = testDao.getItem(3);

        assertEquals(tetsItem1, item1, "Item1 loaded from the file at the strat of the program");
        assertEquals(tetsItem2, item2, "Item2 loaded from the file at the strat of the program");
        assertNull(tetsItem3, "Item3 Does not exist in inventory");

    }

    @Test
    public void getListOfItemsTest() {

        /**
         * Test Plan  
         * testDao.getListOfItems().size(); -> 2;
         * testDao.getListOfItems().contains(item1); -> true;
         * testDao.getListOfItems().contains(item2); -> true;



         *
         */
        // Create our method test inputs
        //Item1
        int itemId1 = 1;
        Item item1 = new Item(itemId1);
        item1.setName("Soda");
        item1.setCost(new BigDecimal("3.40"));
        item1.setQuantity(1);

        //Item1
        int itemId2 = 2;
        Item item2 = new Item(itemId2);
        item2.setName("Juice");
        item2.setCost(new BigDecimal("3.60"));
        item2.setQuantity(2);

        List<Item> testList = testDao.getListOfItems();
        int size = testList.size();

        assertEquals(size, 2, "List has two Items");
        assertTrue(testList.contains(item1), "Inventory contains Item1");
        assertTrue(testList.contains(item2), "Inventory contains Item1");

    }

    @Test
    public void updateInventoryTest() {

        /**
         * Test Plan 
         * testDao.updateInventory(updatedItem1); -> item1;
         * testDao.updateInventory(updatedItem2); -> item2;
         * testDao.updateInventory(updatedItem1); -> updatedItem1;
         * testDao.updateInventory(updatedItem2); -> updatedItem2;
         *
         */
        // Create our method test inputs
        //Item1
        int itemId1 = 1;
        Item item1 = new Item(itemId1);
        item1.setName("Soda");
        item1.setCost(new BigDecimal("3.40"));
        item1.setQuantity(1);

        //Item1
        int itemId2 = 2;
        Item item2 = new Item(itemId2);
        item2.setName("Juice");
        item2.setCost(new BigDecimal("3.60"));
        item2.setQuantity(2);
        
        //updatedItem1
        int updatedItem1Id = 1;
        Item updatedItem1 = new Item(updatedItem1Id);
        updatedItem1.setName("Soda");
        updatedItem1.setCost(new BigDecimal("4.40"));
        updatedItem1.setQuantity(10);

        //updateItem1
        int updatedItem2Id = 2;
        Item updatedItem2 = new Item(updatedItem2Id);
        updatedItem2.setName("Juice");
        updatedItem2.setCost(new BigDecimal("3.60"));
        updatedItem2.setQuantity(2);
        
        Item returnedItem1 = testDao.updateInventory(updatedItem1Id, updatedItem1);
        Item returnedItem2 = testDao.updateInventory(updatedItem2Id, updatedItem2);
        List<Item> testList = testDao.getListOfItems();

        assertEquals(returnedItem1, item1, "Item1 one overwritten");
        assertEquals(returnedItem2, item2, "Item2 one overwritten");
        assertTrue(testList.contains(updatedItem1), "Inventory contains Item1");
        assertTrue(testList.contains(updatedItem2), "Inventory contains Item1");

    }

}
