package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.InventoryAuditDao;
import com.sg.vendingmachine.dao.InventoryDao;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */
//@Component
public class InventoryServiceImpl implements InventoryService {

    //@Autowired
    private InventoryDao dao;
    //@Autowired
    private InventoryAuditDao auditDao;

    public InventoryServiceImpl(InventoryDao dao, InventoryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getListOfItems() {

        return dao.getListOfItems();

    }

    private BigDecimal getChangeInPennies(BigDecimal changeInDollars) {

        BigDecimal changeInPennies = changeInDollars.multiply(new BigDecimal("100"));

        return changeInPennies;
    }

    @Override
    public Change executeTransaction(BigDecimal moneyAmount, int itemIDSelected) throws
            InsufficientFundsException,
            NoItemInventoryException,
            InventoryPersistenceException {

        Item item = dao.getItem(itemIDSelected);

        if (item == null) {

            auditDao.writeAuditEntry("Amount of money Entered " + moneyAmount
                    + "::Error: The item chosen does not exist. Money returned to user.");

            throw new NoItemInventoryException("No such item in the inventory."
                    + " Please take back your money.\nYour amount of money is: $" + moneyAmount);

        }

        int quantity = item.getQuantity();
        BigDecimal price = item.getCost();

        if (quantity == 0) {

            auditDao.writeAuditEntry(item.getItemId() + "::" + item.getName() + "::Amount of money Entered " + moneyAmount
                    + "::Error: Item out of stock. Money returned to user.");

            throw new NoItemInventoryException("Item out of stock."
                    + " Please take back your money.\nYour amount of money is: $" + moneyAmount);

        } else if (price.compareTo(moneyAmount) > 0) {

            auditDao.writeAuditEntry(item.getItemId() + "::" + item.getName() + "::Amount of money Entered " + moneyAmount
                    + "::Error: Insufficient amount of money. Money returned to user.");

            throw new InsufficientFundsException("You need more money to buy this item."
                    + " Please take back your money.\nYour amount of money is: $" + moneyAmount);

        } else {

            item.setQuantity(quantity - 1);
            BigDecimal changeInDollars = moneyAmount.subtract(price);
            dao.updateInventory(itemIDSelected, item);

            BigDecimal changeInPennies = getChangeInPennies(changeInDollars);
            Change changeObj = new Change();
            changeObj.calculateChange(changeInPennies);

            auditDao.writeAuditEntry(item.getItemId() + "::" + item.getName() + " sold:: " + item.getQuantity() + " units remaining. "
                    + "Amount of money Entered " + moneyAmount + " Change returned : " + changeObj.toString());
            return changeObj;
        }

    }

    @Override
    public void readInventoryRecords() throws
            InventoryPersistenceException {

        try {
            dao.readInventoryRecords();
        } catch (InventoryPersistenceException ex) {

            auditDao.writeAuditEntry("::" + ". Error: Could not load inventory information.");
            throw new InventoryPersistenceException("Could not load inventory information to memeory.");
        }

    }

    @Override
    public void writeInventoryRecords() throws
            InventoryPersistenceException {

        try {
            dao.writeInventoryRecords();
        } catch (InventoryPersistenceException ex) {

            auditDao.writeAuditEntry("::" + ". Error: Could not save inventory information to file.");
            throw new InventoryPersistenceException("Could not save inventory information to file.", ex);
        }

    }

    @Override
    public void startSessionAuditBanner() throws
            InventoryPersistenceException {

        auditDao.writeAuditEntry("Session Start");

    }

    @Override
    public void endSessionAuditBanner() throws
            InventoryPersistenceException {

        auditDao.writeAuditEntry("Session End");

    }

}
