package com.aflb.debttracker.dao;

import java.util.Date;
import java.util.List;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.AccountEntryType;

/**
 * @author Barnesly
 *
 *         DAO for accessing persisted {@link AccountEntry}s.
 */
public interface AccountingDao {
    /**
     * Get {@link AccountEntry}s for a specific {@code user}
     * 
     * @param user
     *            The user to retrieve {@link AccountEntry}s for
     * @return A list of {@link AccountEntry}s belonging to the {@code user}
     */
    List<AccountEntry> getEntries(String user);

    /**
     * Get {@link AccountEntry}s of the specific {@link AccountEntryType}
     * 
     * @param type
     *            The {@link AccountEntryType} to retrieve {@link AccountEntry}s
     *            of
     * @return A list of {@link AccountEntry}s of the type {@code type}
     */
    List<AccountEntry> getEntries(AccountEntryType type);

    /**
     * Get {@link AccountEntry}s from a specific {@code date}
     * 
     * @param date
     *            The {@code date} to retrieve {@link AccountEntry}s from
     * @return A list of {@link AccountEntry}s from the {@code date}
     */
    List<AccountEntry> getEntries(Date date);

    /**
     * Get {@link AccountEntry}s using a {@link AccountingDaoFilter}
     * 
     * @param filter
     *            The {@link AccountingDaoFilter} to retrieve
     *            {@link AccountEntry}s on.
     * @return A list of {@link AccountEntry}s matching the
     *         {@link AccountingDaoFilter}
     */
    List<AccountEntry> getEntries(AccountingDaoFilter filter);

    /**
     * Get the overall balance for a {@code user}. This is the combined value of
     * all credits and debits against the user
     * 
     * @param user
     *            The {@code user} to get the balance for
     * @return The balance of the users account
     */
    double getBalance(String user);
    
    /**
     * Retrieve the last {@link AccountEntry} for a {@code user}
     * 
     * @param user
     *            The {@code user} to get the last entry for
     * @return
     */
    AccountEntry getLastEntry(String user);
    
    /**
     * Retrieve the last {@link AccountEntry} for a {@code user} of the provided
     * {@code type}
     * 
     * @param user
     *            The {@code user} to get the last entry for
     * @param type
     *            The {@link AccountEntryType} of the last entry to retrieve
     * @return
     */
    AccountEntry getLastEntry(String user, AccountEntryType type);
}
