package com.aflb.debttracker.dao;

import java.util.Date;
import java.util.List;

import com.aflb.debttracker.accounting.AccountEntry;
import com.aflb.debttracker.accounting.AccountEntryType;

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
}
