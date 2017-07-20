/**
 * 
 */
package com.aflb.debttracker.data.dao.memory;

import java.util.List;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.User;

/**
 * @author Barnesly
 *
 */
public interface IMemoryStore {
	
	User getUser(String userId);
	
	List<User> getAllUsers();
	
	void addUser(User user);
	
	AccountEntry getEntry(int accountEntryId);
	
	void addEntry(AccountEntry entry);
	
	List<AccountEntry> getEntries(int userId);
	
	List<AccountEntry> getAllEntries();

}
