/**
 * 
 */
package com.aflb.debttracker.data.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.AccountEntryType;
import com.aflb.debttracker.data.dao.AccountingDao;
import com.aflb.debttracker.data.dao.AccountingDaoFilter;

/**
 * @author Barnesly
 *
 */
public class AccountingDaoHibernate implements AccountingDao {

	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(com.aflb.debttracker.data.dao.AccountingDaoFilter)
	 */
	@Override
	public List<AccountEntry> getEntries(final AccountingDaoFilter filter) {
		CriteriaQuery<AccountEntry> criteria = getFilteredAccountEntryCriteria(filter);
		return entityManager.createQuery(criteria).getResultList();
	}
	
	private static CriteriaQuery<AccountEntry> getFilteredAccountEntryCriteria(final AccountingDaoFilter filter) {
		CriteriaBuilder builder = HibernateHelper.getCriteriaBuilder();
		CriteriaQuery<AccountEntry> criteria = builder.createQuery(AccountEntry.class);
		Root<AccountEntry> accountEntriesRoot = criteria.from(AccountEntry.class);
		criteria.select(accountEntriesRoot);
		if (filter.getUser() > 0) {
			criteria.where(builder.equal(accountEntriesRoot.get("user"), filter.getUser()));
		}
		// TODO Add date formatter and filter
//		if (filter.getFromDate() != null) {
//			criteria.where(builder.equal(accountEntriesRoot.get("date"), filter.getDate()));
//		}
//		if (filter.getType() != null) {
//			criteria.where(builder.equal(accountEntriesRoot.get("TYPE"), filter.getType().toString()));
//		}
		return criteria;
	}
	
	private List<AccountEntry> getEntries(final int user, final AccountEntryType type, final Date date) {
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
	public List<AccountEntry> getEntries(final int user) {
		return getEntries(user, null, null);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(com.aflb.debttracker.data.AccountEntryType)
	 */
	@Override
	public List<AccountEntry> getEntries(final AccountEntryType type) {
		return getEntries(0, type, null);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getEntries(java.util.Date)
	 */
	@Override
	public List<AccountEntry> getEntries(final Date date) {
		return getEntries(0, null, date);
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getBalance(java.lang.String)
	 */
	@Override
	public double getBalance(final int user) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getLastEntry(java.lang.String)
	 */
	@Override
	public AccountEntry getLastEntry(final int user) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.AccountingDao#getLastEntry(java.lang.String, com.aflb.debttracker.data.AccountEntryType)
	 */
	@Override
	public AccountEntry getLastEntry(final int user, final AccountEntryType type) {
		return null;
	}

	@Override
	public EntityManager createEntityManager() {
		entityManager = HibernateHelper.createEntityManager();
		return entityManager;
	}

}
