package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 * @author Younes Boutaleb email address: boutalebyounes@gmail.com Current date:
 * Purpose of the class:
 */
public class Change {

    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    public void calculateChange(BigDecimal ChangeInPennies) {

        BigDecimal[] bigQuarters = ChangeInPennies.divideAndRemainder(Coins.QUARTRES.value);

        BigDecimal[] bigDimes = bigQuarters[1].divideAndRemainder(Coins.DIMES.value);

        BigDecimal[] bigNickels = bigDimes[1].divideAndRemainder(Coins.NICKELS.value);

        BigDecimal bigPennies = bigNickels[1];

        quarters = bigQuarters[0].intValue();
        dimes = bigDimes[0].intValue();
        nickels = bigNickels[0].intValue();
        pennies = bigPennies.intValue();

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.quarters;
        hash = 23 * hash + this.dimes;
        hash = 23 * hash + this.nickels;
        hash = 23 * hash + this.pennies;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.quarters != other.quarters) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.nickels != other.nickels) {
            return false;
        }
        if (this.pennies != other.pennies) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        
        int[] numberOfCoins = {quarters, dimes, nickels, pennies};
        String[] coinsNames = {"Quarters", "Dimes", "Nickels", "Pennies"};
        String changeAsString = "";

        for (int i = 0; i < 4; i++) {

                changeAsString += numberOfCoins[i] + " " + coinsNames[i] + "  ";
            }
        
        return changeAsString;

    }

}
