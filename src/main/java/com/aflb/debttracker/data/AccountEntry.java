package com.aflb.debttracker.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Barnesly
 * 
 *         Class containing details related to a single account entry. This
 *         typically includes:
 *         <ul>
 *         <li>The {@code user} this entry is for</li>
 *         <li>The {@code value} of this entry (non-zero)</li>
 *         <li>The {@code date} of this entry</li>
 *         <li>The {@code description} of this entry</li>
 *         <li>The {@code type} ({@link AccountEntryType}) representing whether
 *         this is a credit or debit</li>
 *         </ul>
 */
@Entity
@Table(name = "T_ENTRIES")
public class AccountEntry {
    
    private static final String DEFAULT_DESCRIPTION = "No more details";

    @Id
    @Column(name = "ENTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "USER_ID")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Cascade({CascadeType.ALL})
    private User user;
    
    @Column(name = "VAL")
    private double value = 0;
    
    @Column(name = "DATE_ADDED")
    private Date date;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Transient
    private AccountEntryType type;

    /**
     * Prevent @link {@link AccountEntry}s from being created without required
     * data.
     */
    @SuppressWarnings("unused")
    private AccountEntry() {

    }

    /**
     * Constructor setting the {@code user}, {@code value}, {@code date} and
     * {@:code description}. The {@code type} is implicitly set based on the
     * {@code value}.
     * 
     * @param user
     *            The user this {@link AccountEntry} is for
     * @param value
     *            The value of this {@link AccountEntry}
     * @param date
     *            The date of this {@link AccountEntry}
     * @param description
     *            The description of this {@link AccountEntry}
     */
    public AccountEntry(User user, double value, Date date, String description) {
        this.user = user;
        // Use setValue to ensure the type is correctly set
        setValue(value);
        this.date = new Date(date.getTime());
        this.description = description;
    }

    /**
     * Constructor setting the {@code user}, {@code value} and
     * {@:code date}. The {@code type} is implicitly set based on the
     * {@code value}.
     * 
     * @param user
     *            The user this {@link AccountEntry} is for
     * @param value
     *            The value of this {@link AccountEntry}
     * @param date
     *            The date of this {@link AccountEntry}
     */
    public AccountEntry(User user, double value, Date date) {
        this(user, value, date, DEFAULT_DESCRIPTION);
    }

    /**
     * Constructor setting the {@code user}, {@code value} and
     * {@:code description}. The {@code type} is implicitly set based on the
     * {@code value}.
     * 
     * @param user
     *            The user this {@link AccountEntry} is for
     * @param value
     *            The value of this {@link AccountEntry}
     * @param description
     *            The description of this {@link AccountEntry}
     */
    public AccountEntry(User user, double value, String description) {
        this(user, value, new Date(), description);
    }
    
    /**
     * Constructor setting the {@code user} and {@code value}. The {@code type}
     * is implicitly set based on the {@code value}.
     * 
     * @param user
     *            The user this {@link AccountEntry} is for
     * @param value
     *            The value of this {@link AccountEntry}
     */
    public AccountEntry(User user, double value) {
        this(user, value, new Date(), DEFAULT_DESCRIPTION);
    }
    
    /**
     * Completes optional fields with defaults if they are not set
     */
    public void complete() {
        if (this.date == null) {
            this.date = new Date();
        }
        if (this.description == null || this.description.trim().isEmpty()) {
            this.description = DEFAULT_DESCRIPTION;
        }
    }
    
    /**
     * Validates whether the minimal amount of information is set for this
     * {@link AccountEntry}.
     * 
     * @throws AccountEntryIncompleteException if the required fields are not set.
     */
    public void validate() throws AccountEntryIncompleteException {
        if (this.user == null) {
            throw new AccountEntryIncompleteException("User is not set");
        }
        if (this.value == 0) {
            throw new AccountEntryIncompleteException("Value is not set");
        }
    }
    
    /**
     * Validates whether the minimal amount of information is set for this
     * {@link AccountEntry} and calls {@link #complete()} to fill in optional fields.
     * 
     * @throws AccountEntryIncompleteException if the required fields are not set.
     */
    public void validateAndComplete() throws AccountEntryIncompleteException {
        complete();
        validate();
    }
    
    /**
     * TODO
     * @return
     */
    public String toJson() {
        StringBuilder builder = new StringBuilder(16384);
        
        builder
            .append("{")
            .append("\"name\":\"").append(this.user.getName().toString()).append("\"")
            .append(",")
            .append("\"value\":\"").append(String.format("%.2f", this.value)).append("\"")
            .append(",")
            .append("\"type\":\"").append(this.type).append("\"")
            .append(",")
            .append("\"date\":\"").append(this.date).append("\"")
            .append(",")
            .append("\"description\":\"").append(this.description).append("\"")
            .append("}");
        
        return builder.toString();
    }

    /**
     * @return The date this {@link AccountEntry} is for.
     */
    public Date getDate() {
        return new Date(this.date.getTime());
    }

    /**
     * Sets the date this {@link AccountEntry} is for.
     * 
     * @param date The date this {@link AccountEntry} is for.
     */
    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    /**
     * @return The description of this {@link AccountEntry}.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this {@link AccountEntry}.
     * 
     * @param description The description of this {@link AccountEntry}.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The value of this {@link AccountEntry}.
     */
    public double getValue() {
        return value;
    }

    /**
     * Set the value of this {@link AccountEntry}. Also sets the {@code type}.
     * 
     * @param value The value of the {@link AccountEntry}.
     */
    public void setValue(double value) {
        this.value = value;
        if (value < 0) {
            type = AccountEntryType.DEBIT;
        } else {
            type = AccountEntryType.CREDIT;
        }
    }

    /**
     * @return the user this {@link AccountEntry} is for.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user that this {@link AccountEntry} is for.
     * 
     * @param user The user this enty is for.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the {@link AccountEntryType} for this {@link AccountEntry}.
     */
    public AccountEntryType getType() {
        return type;
    }
    
    public class AccountEntryIncompleteException extends Exception {
        /**
         * Default serialVersionUID
         */
        private static final long serialVersionUID = 1L;

        public AccountEntryIncompleteException(String reason) {
            super(reason);
        }
    }
}
