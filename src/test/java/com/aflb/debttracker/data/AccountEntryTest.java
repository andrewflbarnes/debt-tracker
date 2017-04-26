/**
 * 
 */
package com.aflb.debttracker.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import com.aflb.debttracker.data.AccountEntry.AccountEntryIncompleteException;

/**
 * @author Barnesly
 *
 */
public class AccountEntryTest {

	private static final User user = new User("boom");

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#AccountEntry(java.lang.String, double, java.util.Date, java.lang.String)}.
     */
    @Test
    public void testAccountEntryStringDoubleDateString() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry(user, 10, date, "description");
        assertEquals(user, entry.getUser());
        assertEquals(10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.CREDIT, entry.getType());
        assertEquals(date, entry.getDate());
        assertEquals("description", entry.getDescription());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#AccountEntry(java.lang.String, double, java.util.Date)}.
     */
    @Test
    public void testAccountEntryStringDoubleDate() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry(user, -10, date);
        assertEquals(user, entry.getUser());
        assertEquals(-10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
        assertEquals(date, entry.getDate());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#AccountEntry(java.lang.String, double, java.lang.String)}.
     */
    @Test
    public void testAccountEntryStringDoubleString() {
        AccountEntry entry = new AccountEntry(user, -10, "description");
        assertEquals(user, entry.getUser());
        assertEquals(-10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
        assertEquals("description", entry.getDescription());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#AccountEntry(java.lang.String, double)}.
     */
    @Test
    public void testAccountEntryStringDouble() {
        AccountEntry entry = new AccountEntry(user, -10);
        assertEquals(user, entry.getUser());
        assertEquals(-10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#complete()}.
     */
    @Test
    public void testComplete() {
        AccountEntry entry = new AccountEntry(user, -10);
        entry.complete();
        assertTrue(entry.getDescription().length() > 0);
        assertTrue(entry.getDate() != null);
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#validate()}.
     */
    @Test
    public void testValidatePass() {
        AccountEntry entry = new AccountEntry(user, -10);
        try {
            entry.validate();
        } catch (AccountEntryIncompleteException e) {
            fail("validate() failed: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#validate()}.
     */
    @Test
    public void testValidateUserFail() {
        AccountEntry entry = new AccountEntry(user, -10);
        entry.setUser(null);
        try {
            entry.validate();
        } catch (AccountEntryIncompleteException e) {
            return;
        }
        fail("validate() passed when it shouldn't have");
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#validate()}.
     */
    @Test
    public void testValidateValueFail() {
        AccountEntry entry = new AccountEntry(user, 0);
        entry.setUser(null);
        try {
            entry.validate();
        } catch (AccountEntryIncompleteException e) {
            return;
        }
        fail("validate() passed when it shouldn't have");
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#validateAndComplete()}.
     */
    @Test
    public void testValidateAndComplete() {
        AccountEntry entry = new AccountEntry(user, 10);
        try {
            entry.validateAndComplete();
        } catch (AccountEntryIncompleteException e) {
            fail("validateAndComplete() failed: " + e.getMessage());
        }
        assertTrue(entry.getDescription().length() > 0);
        assertTrue(entry.getDate() != null);
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#toJson()}.
     */
    @Test
    public void testToJsonCredit() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry(user, 10, date, "description");
        
        String expected =
                "{\"name\":\"" + user.getName() + "\","
                + "\"value\":\"10.00\","
                + "\"type\":\"Credit\","
                + "\"date\":\"" + date.toString() + "\","
                + "\"description\":\"description\"}";
        
        assertEquals(expected, entry.toJson());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#toJson()}.
     */
    @Test
    public void testToJsonDebit() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry(user, -10, date, "description");
        
        String expected =
                "{\"name\":\"" + user.getName() + "\","
                + "\"value\":\"-10.00\","
                + "\"type\":\"Debit\","
                + "\"date\":\"" + date.toString() + "\","
                + "\"description\":\"description\"}";
        
        assertEquals(expected, entry.toJson());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#setValue(double)}.
     */
    @Test
    public void testSetValueCredit() {
        AccountEntry entry = new AccountEntry(user, -10, new Date(), "description");
        entry.setValue(10);
        assertEquals(AccountEntryType.CREDIT, entry.getType());
    }

    /**
     * Test method for {@link com.aflb.debttracker.data.AccountEntry#setValue(double)}.
     */
    @Test
    public void testSetValueDebit() {
        AccountEntry entry = new AccountEntry(user, 10, new Date(), "description");
        entry.setValue(-10);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
    }

}
