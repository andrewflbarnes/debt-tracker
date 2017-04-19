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
	List<AccountEntry> getEntries(String user);
	List<AccountEntry> getEntries(AccountEntryType type);
	List<AccountEntry> getEntries(Date date);
	List<AccountEntry> getEntries(AccountingDaoFilter helper);
}
