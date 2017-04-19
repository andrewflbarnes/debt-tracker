package com.aflb.debttracker.dao;

import java.util.List;

/**
 * @author Barnesly
 *
 *         DAO for accessing debttracker user details
 */
public interface UserDao {
	/**
	 * @return a list of all users.
	 */
	List<String> getUsers();
}
