package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */

//@Component
public class InventoryView {

    //@Autowired
    private UserIO io;

    
    public InventoryView(UserIO io) {

        this.io = io;

    }

    public int getUserOption() {

        return io.readInt("\nSelect one of the following options\n(1) Buy an item.\n(2) Exit.", 1, 2);

    }

    public void displayItems(List<Item> itemsList) {

        io.print("\nList of available Items and their prices:");
        itemsList.stream()
                .filter((item) -> item.getQuantity() > 0)
                .forEach(
                        (item) -> {

                            System.out.println(item.getItemId() + " : " + item.getName() + " : $" + item.getCost());
                        });

    }

    public BigDecimal getAmount() {

        return io.readPositiveBigDecimal("Add money ($): ").setScale(2, RoundingMode.FLOOR);

    }

    public int selectItem() {

        return io.readInt("\nEnter Item Number from the above list: ",0,100);

    }

    public void displayChange(Change change) {
        
        io.print("Please take your Item.\nYour change is:");
        System.out.println(change.toString());
    }

    public void exitMessage() {

        io.print("\nThank you for buying from our machine. Good by!");

    }

}
