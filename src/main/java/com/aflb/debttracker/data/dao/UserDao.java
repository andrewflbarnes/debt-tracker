package com.aflb.debttracker.data.dao;

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
