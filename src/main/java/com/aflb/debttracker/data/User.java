package com.aflb.debttracker.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "T_USERS")
public class User {
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DATE_ADDED")
	private Date date;
	
	@Column(name = "DESCRIPTION")
	private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade({CascadeType.ALL})
    private List<AccountEntry> accountEntries;

//    /**
//     * Prevent @link {@link User}s from being created without required
//     * data.
//     */
	public User() {
		
	}

	/**
	 * TODO
	 * @param name
	 * @param date
	 * @param description
	 */
	public User(String name, Date date, String description) {
		this.name = name;
		this.date = new Date(date.getTime());
		this.description = description;
	}
	
	/**
	 * TODO
	 * @param name
	 * @param date
	 */
	public User(String name, Date date) {
		this(name, date, "No more details");
	}
	
	/**
	 * TODO
	 * @param name
	 * @param description
	 */
	public User(String name, String description) {
		this(name, new Date(), description);
	}
	
	/**
	 * TODO
	 * @param name
	 */
	public User(String name) {
		this(name, new Date(), "No more details");
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return new Date(this.date.getTime());
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the accountEntries
	 */
	public List<AccountEntry> getAccountEntries() {
		return accountEntries;
	}

	/**
	 * @param accountEntries the accountEntries to set
	 */
	public void setAccountEntries(List<AccountEntry> accountEntries) {
		this.accountEntries = accountEntries;
	}
    
    /**
     * TODO
     * @return
     */
    public String toJson() {
        StringBuilder builder = new StringBuilder(16384);
        
        builder
            .append("{")
            .append("\"name\":\"").append(this.getName().toString()).append("\"")
            .append(",")
            .append("\"date\":\"").append(this.date).append("\"")
            .append(",")
            .append("\"description\":\"").append(this.description).append("\"")
            .append("}");
        
        return builder.toString();
    }

}
