package com.aflb.debttracker.data.dao;

import java.util.Date;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.AccountEntryType;
import com.aflb.debttracker.data.User;

/**
 * @author Barnesly
 *
 *         Helper class for filtering the {@link AccountingDao} results - can
 *         contain multiple parameters for filtering {@link AccountEntry}s
 */
public class AccountingDaoFilter {
    private User user = null;
    private Date fromDate = null;
    private AccountEntryType type = null;

    /**
     * Resets all values of this {@link AccountingDaoFilter} object to null.
     * 
     * @return This {@link AccountingDaoFilter}
     */
    public AccountingDaoFilter reset() {
        user = null;
        fromDate = null;
        type = null;
        return this;
    }

    /**
     * @return The user this {@link AccountingDaoFilter} is set to filter on
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user to filter on in this {@link AccountingDaoFilter}
     * 
     * @param user
     *            The user for this {@link AccountingDaoFilter} to filter on
     * @return This {@link AccountingDaoFilter}
     */
    public AccountingDaoFilter setUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * @return The date this {@link AccountingDaoFilter} is set to filter
     *         from
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the date to filter from in this {@link AccountingDaoFilter}
     * 
     * @param fromDate
     *            The date for this {@link AccountingDaoFilter} to filter
     *            from
     * @return This {@link AccountingDaoFilter}
     */
    public AccountingDaoFilter setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * @return The {@link AccountEntryType} this {@link AccountingDaoFilter} is
     *         set to filter on
     */
    public AccountEntryType getType() {
        return type;
    }

    /**
     * Sets the {@link AccountEntryType} to filter on in this
     * {@link AccountingDaoFilter}
     * 
     * @param type
     *            The {@link AccountEntryType} for this
     *            {@link AccountingDaoFilter} to filter on
     * @return This {@link AccountingDaoFilter}
     */
    public AccountingDaoFilter setType(AccountEntryType type) {
        this.type = type;
        return this;
    }
}
