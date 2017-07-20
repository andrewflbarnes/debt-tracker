package com.aflb.debttracker.data.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.aflb.debttracker.data.User;

/**
 * @author Barnesly
 *
 *         DAO for accessing debttracker user details
 */
public interface UserDao {
    /**
     * @return a list of all users.
     */
    List<User> getUsers();
    
    EntityManager init();
    
    void begin();
    
    void commit();
    
    void close();
}
