/**
 * 
 */
package com.aflb.debttracker.data.dao.memory;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.AccountEntryType;
import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.AccountingDao;
import com.aflb.debttracker.data.dao.AccountingDaoFilter;

/**
 * @author Barnesly
 *
 */
public class AccountingDaoMemory implements AccountingDao {

	private MemoryStore store = MemoryStore.getInstance();

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(com.aflb.debttracker.data.dao.AccountingDaoFilter)
	 */
	@Override
	public List<AccountEntry> getEntries(final AccountingDaoFilter filter) {
		if (filter.getUser() != null) {
			return store.getEntries(filter.getUser().getId());
		}
		// TODO Add date formatter and filter
//		if (filter.getFromDate() != null) {
//			criteria.where(builder.equal(accountEntriesRoot.get("date"), filter.getDate()));
//		}
//		if (filter.getType() != null) {
//			criteria.where(builder.equal(accountEntriesRoot.get("TYPE"), filter.getType().toString()));
//		}
		return store.getAllEntries();
	}
	
	private List<AccountEntry> getEntries(final User user, final AccountEntryType type, final Date date) {
		AccountingDaoFilter filter = new AccountingDaoFilter()
				.setUser(user)
				.setType(type)
				.setFromDate(date);
		return getEntries(filter);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(java.lang.String)
	 */
	@Override
	public List<AccountEntry> getEntries(final User user) {
		return getEntries(user, null, null);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(com.aflb.debttracker.data.AccountEntryType)
	 */
	@Override
	public List<AccountEntry> getEntries(final AccountEntryType type) {
		return getEntries(null, type, null);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(java.util.Date)
	 */
	@Override
	public List<AccountEntry> getEntries(final Date date) {
		return getEntries(null, null, date);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getBalance(java.lang.String)
	 */
	@Override
	public double getBalance(final User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getLastEntry(java.lang.String)
	 */
	@Override
	public AccountEntry getLastEntry(final User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getLastEntry(java.lang.String, com.aflb.debttracker.data.AccountEntryType)
	 */
	@Override
	public AccountEntry getLastEntry(final User user, final AccountEntryType type) {
		return null;
	}

	@Override
	public EntityManager createEntityManager() {
		return null;
	}

}
