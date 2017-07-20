/**
 * 
 */
package com.aflb.debttracker.data.dao.memory;

import java.util.List;

import javax.persistence.EntityManager;

import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.UserDao;

/**
 * @author Barnesly
 *
 */
public class UserDaoMemory implements UserDao {
	
	private MemoryStore store = MemoryStore.getInstance();

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.UserDao#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		return store.getAllUsers();
	}

	@Override
	public EntityManager init() {
		return null;
	}
	
	@Override
	public void begin() {}
	
	@Override
	public void commit() {}
	
	@Override
	public void close() {}

}
