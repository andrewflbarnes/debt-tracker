/**
 * 
 */
package com.aflb.debttracker.accounting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aflb.debttracker.accounting.AccountEntry.AccountEntryIncompleteException;

/**
 * @author Barnesly
 *
 */
public class AccountEntryTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#AccountEntry(java.lang.String, double, java.util.Date, java.lang.String)}.
     */
    @Test
    public void testAccountEntryStringDoubleDateString() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry("user", 10, date, "description");
        assertEquals("user", entry.getUser());
        assertEquals(10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.CREDIT, entry.getType());
        assertEquals(date, entry.getDate());
        assertEquals("description", entry.getDescription());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#AccountEntry(java.lang.String, double, java.util.Date)}.
     */
    @Test
    public void testAccountEntryStringDoubleDate() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry("user", -10, date);
        assertEquals("user", entry.getUser());
        assertEquals(-10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
        assertEquals(date, entry.getDate());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#AccountEntry(java.lang.String, double, java.lang.String)}.
     */
    @Test
    public void testAccountEntryStringDoubleString() {
        AccountEntry entry = new AccountEntry("user", -10, "description");
        assertEquals("user", entry.getUser());
        assertEquals(-10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
        assertEquals("description", entry.getDescription());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#AccountEntry(java.lang.String, double)}.
     */
    @Test
    public void testAccountEntryStringDouble() {
        AccountEntry entry = new AccountEntry("user", -10);
        assertEquals("user", entry.getUser());
        assertEquals(-10, entry.getValue(), 0.001);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#complete()}.
     */
    @Test
    public void testComplete() {
        AccountEntry entry = new AccountEntry("user", -10);
        entry.complete();
        assertTrue(entry.getDescription().length() > 0);
        assertTrue(entry.getDate() != null);
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#validate()}.
     */
    @Test
    public void testValidatePass() {
        AccountEntry entry = new AccountEntry("user", -10);
        try {
            entry.validate();
        } catch (AccountEntryIncompleteException e) {
            fail("validate() failed: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#validate()}.
     */
    @Test
    public void testValidateUserFail() {
        AccountEntry entry = new AccountEntry("user", -10);
        entry.setUser(null);
        try {
            entry.validate();
        } catch (AccountEntryIncompleteException e) {
            return;
        }
        fail("validate() passed when it shouldn't have");
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#validate()}.
     */
    @Test
    public void testValidateValueFail() {
        AccountEntry entry = new AccountEntry("user", 0);
        entry.setUser(null);
        try {
            entry.validate();
        } catch (AccountEntryIncompleteException e) {
            return;
        }
        fail("validate() passed when it shouldn't have");
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#validateAndComplete()}.
     */
    @Test
    public void testValidateAndComplete() {
        AccountEntry entry = new AccountEntry("user", 10);
        try {
            entry.validateAndComplete();
        } catch (AccountEntryIncompleteException e) {
            fail("validateAndComplete() failed: " + e.getMessage());
        }
        assertTrue(entry.getDescription().length() > 0);
        assertTrue(entry.getDate() != null);
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#toJson()}.
     */
    @Test
    public void testToJsonCredit() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry("user", 10, date, "description");
        
        String expected =
                "{\"name\":\"user\","
                + "\"value\":\"10.00\","
                + "\"type\":\"Credit\","
                + "\"date\":\"" + date.toString() + "\","
                + "\"description\":\"description\"}";
        
        assertEquals(expected, entry.toJson());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#toJson()}.
     */
    @Test
    public void testToJsonDebit() {
        Date date = new Date();
        AccountEntry entry = new AccountEntry("user", -10, date, "description");
        
        String expected =
                "{\"name\":\"user\","
                + "\"value\":\"-10.00\","
                + "\"type\":\"Debit\","
                + "\"date\":\"" + date.toString() + "\","
                + "\"description\":\"description\"}";
        
        assertEquals(expected, entry.toJson());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#setValue(double)}.
     */
    @Test
    public void testSetValueCredit() {
        AccountEntry entry = new AccountEntry("user", -10, new Date(), "description");
        entry.setValue(10);
        assertEquals(AccountEntryType.CREDIT, entry.getType());
    }

    /**
     * Test method for {@link com.aflb.debttracker.accounting.AccountEntry#setValue(double)}.
     */
    @Test
    public void testSetValueDebit() {
        AccountEntry entry = new AccountEntry("user", 10, new Date(), "description");
        entry.setValue(-10);
        assertEquals(AccountEntryType.DEBIT, entry.getType());
    }

}
