package com.aflb.debttracker.data;

/**
 * @author Barnesly
 *
 *         Enum represeting the type of an {@link AccountEntry} i.e. Credit or
 *         Debit
 */
public enum AccountEntryType {
    CREDIT("Credit"),
    DEBIT("Debit");
    
    private String value;
    
    /**
     * Enum constructor
     * 
     * @param value
     *            The String value of the enum, returned by the overridden
     *            {@link #toString()) method
     */
    private AccountEntryType(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value;
    }

}
