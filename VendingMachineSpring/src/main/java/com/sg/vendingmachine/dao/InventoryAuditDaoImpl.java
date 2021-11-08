package com.sg.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */

//@Component
public class InventoryAuditDaoImpl implements InventoryAuditDao {

    
    public static final String AUDIT_FILE= "auditInventory.txt";
    
    public InventoryAuditDaoImpl(){
    
    }
    
    @Override
    public void writeAuditEntry(String entry) throws
            InventoryPersistenceException {

        PrintWriter printer;

        try {

            printer = new PrintWriter(new FileWriter(AUDIT_FILE, true));

        } catch (IOException io) {

            throw new InventoryPersistenceException("Could not write transaction's details", io);

        }

        LocalDateTime date = LocalDateTime.now();
        String dateAsString = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd---hh:mm:ss-a"));

        printer.println(dateAsString + "::" + entry);
        printer.flush();
        printer.close();

    }

}
