package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */

//@Component
public class InventoryDaoImpl implements InventoryDao {

    private Map<Integer, Item> inventory = new HashMap<>();
    private static final String DELIMITER = "::";
    private final String FILE_NAME;

    public InventoryDaoImpl() {

        FILE_NAME = "inventory.txt";

    }

    public InventoryDaoImpl(String File_Name) {

        this.FILE_NAME = File_Name;

    }

    @Override
    public List<Item> getListOfItems() {

        return new ArrayList(inventory.values());
    }

    @Override
    public Item getItem(int itemIDSelected) {

        return inventory.get(itemIDSelected);
    }

    @Override
    public Item updateInventory(int itemIDSelected, Item item) {

        return inventory.put(itemIDSelected, item);
    }

    private Item unmmarshallDVD(String itemAsString) throws
            InventoryPersistenceException {

        String[] array = itemAsString.split(DELIMITER);
        Item item;

        try {
            item = new Item(Integer.parseInt(array[0]));
            item.setName(array[1]);
            item.setCost(new BigDecimal(array[2]));
            item.setQuantity(Integer.parseInt(array[3]));
        } catch (NumberFormatException nfe) {

            throw new InventoryPersistenceException("Invalid item format");

        }

        return item;

    }

    @Override
    public void readInventoryRecords() throws InventoryPersistenceException {

        Scanner loader;

        try {
            loader = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));

        } catch (FileNotFoundException e) {

            throw new InventoryPersistenceException("Could not load Items inventory information to memeory.", e);

        }

        Item loadedItem;
        String line;

        while (loader.hasNextLine()) {

            line = loader.nextLine();
            loadedItem = this.unmmarshallDVD(line);
            inventory.put(loadedItem.getItemId(), loadedItem);

        }

        loader.close();

    }

    private String marshallItem(Item item) {

        String itemAsString = item.getItemId() + DELIMITER + item.getName()
                + DELIMITER + item.getCost() + DELIMITER + item.getQuantity();

        return itemAsString;
    }

    @Override
    public void writeInventoryRecords() throws InventoryPersistenceException {

        PrintWriter printer;

        try {

            printer = new PrintWriter(new FileWriter(FILE_NAME));

        } catch (IOException io) {

            throw new InventoryPersistenceException("Could not save inventory information.", io);
        }

        List<Item> list = this.getListOfItems();
        String itemAsString;

        for (Item item : list) {

            itemAsString = marshallItem(item);
            printer.println(itemAsString);
            printer.flush();
        }
        printer.close();

    }
}
