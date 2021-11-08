/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.InventoryAuditDao;
import com.sg.vendingmachine.dao.InventoryDao;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pc
 */
public class InventoryServiceImplTest {

    InventoryService testService;

    public InventoryServiceImplTest() {

//        InventoryDao daoStub = new InventoryDaoImplStub();
//        InventoryAuditDao auditDaoStub = new InventoryAuditDaoImplStub();
//
//        testService = new InventoryServiceImpl(daoStub, auditDaoStub);

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        testService
                = ctx.getBean("inventoryServiceTest", InventoryServiceImpl.class);

    }

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getListOfItemsTest() {

        /**
         * Test plan testService.getListOfItems().size(); -> 1;
         * testService.getListOfItems().contains(itemTest); -> true
         */
        // ARRANGE
        Item itemTest = new Item(1);
        itemTest.setName("Soda");
        itemTest.setCost(new BigDecimal("3.00"));
        itemTest.setQuantity(1);

        // ACT
        List<Item> itemsList = testService.getListOfItems();
        int size = itemsList.size();

        // ASSERT
        assertEquals(size, 1, "The list contains only the stub Item");
        assertTrue(testService.getListOfItems().contains(itemTest), "The only item in the inventory is a clone of itemTest");

    }

    @Test
    public void executeTransactionAvailableItemTest() {

        /**
         * Test plan testService.getListOfItems().size(); -> 1;
         * testService.getListOfItems().contains(itemTest); -> true
         */
        // ARRANGE
        int itemTestId = 1;
        Item itemTest = new Item(itemTestId);
        itemTest.setName("Soda");
        itemTest.setCost(new BigDecimal("3.00"));
        itemTest.setQuantity(1);

        BigDecimal enteredAmount = new BigDecimal("3.41");
        Change change = new Change();
        change.setQuarters(1);
        change.setDimes(1);
        change.setNickels(1);
        change.setPennies(1);

        // ACT
        try {
            Change returnedChange = testService.executeTransaction(enteredAmount, itemTestId);
            // ASSERT
            assertEquals(returnedChange, change, "Return 41 pennies");
        } catch (InsufficientFundsException
                | NoItemInventoryException
                | InventoryPersistenceException ex) {

            fail("Valid Item and valid aamount of money. No exception has to be thrown");
        }

    }

    @Test
    public void executeTransactionItemOutOfStockTest() {

        /**
         * Test plan testService.getListOfItems().size(); -> 1;
         * testService.getListOfItems().contains(itemTest); -> true
         */
        // ARRANGE
        int itemTestId = 1;
        BigDecimal enteredAmount = new BigDecimal("4.00");

        // ACT
        try {
            testService.executeTransaction(enteredAmount, itemTestId);
            testService.executeTransaction(enteredAmount, itemTestId);
            // ASSERT
            fail("Expected NoItemInventoryException was not thrown");
        } catch (InsufficientFundsException
                | InventoryPersistenceException ex) {

            fail("Invalid exception was thrown");
        } catch (NoItemInventoryException niex) {

            return;

        }

    }

    @Test
    public void executeTransactionNoSuchItemTest() {

        /**
         * Test plan testService.getListOfItems().size(); -> 1;
         * testService.getListOfItems().contains(itemTest); -> true
         */
        // ARRANGE
        int itemTestId = 2;
        BigDecimal enteredAmount = new BigDecimal("4.00");

        // ACT
        try {
            testService.executeTransaction(enteredAmount, itemTestId);
            // ASSERT
            fail("Expected NoItemInventoryException was not thrown");
        } catch (InsufficientFundsException
                | InventoryPersistenceException ex) {

            fail("Invalid exception was thrown");
        } catch (NoItemInventoryException niex) {

            return;

        }
    }

    @Test
    public void executeTransactionInsufficientFunds() {

        /**
         * Test plan testService.getListOfItems().size(); -> 1;
         * testService.getListOfItems().contains(itemTest); -> true
         */
        // ARRANGE
        int itemTestId = 1;
        BigDecimal enteredAmount = new BigDecimal("2.00");

        // ACT
        try {
            testService.executeTransaction(enteredAmount, itemTestId);
            // ASSERT
            fail("Expected InsufficientFundsException was not thrown");
        } catch (NoItemInventoryException
                | InventoryPersistenceException ex) {

            fail("Invalid exception was thrown");
        } catch (InsufficientFundsException niex) {

            return;

        }
    }
}
