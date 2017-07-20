/**
 * 
 */
package com.aflb.debttracker.data.dao.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.User;

/**
 * @author Barnesly
 *
 */
public class MemoryStore implements IMemoryStore {
	private Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();
	private Map<Integer, Map<Integer, AccountEntry>> entries = new ConcurrentHashMap<Integer, Map<Integer, AccountEntry>>();
	private int entryId = 0;
	private static final List<AccountEntry> EMPTY_LIST = new ArrayList<AccountEntry>();
	
	private static class MemoryStoreHolder {
		public static final MemoryStore INSTANCE = new MemoryStore();
	}
	
	private MemoryStore() {}
	
	public static MemoryStore getInstance() {
		return MemoryStoreHolder.INSTANCE;
	}
	
	@Override
	public User getUser(String userId) {
		return users.get(userId);
	}
	
	@Override
	public List<User> getAllUsers() {
		return new ArrayList<User>(users.values());
	}
	
	@Override
	public void addUser(User user) {
		users.put(user.getId(), user);
	}
	
	@Override
	public AccountEntry getEntry(int accountEntryId) {
		for (Map<Integer, AccountEntry> userEntries : entries.values()) {
			for (Entry<Integer, AccountEntry> entry : userEntries.entrySet()) {
				if (entry.getKey() == accountEntryId) {
					return entry.getValue();
				}
			}
		}
		return null;
	}
	
	@Override
	public void addEntry(AccountEntry entry) {
		Map<Integer, AccountEntry> userEntries = entries.get(entry.getUser().getId());
		if (userEntries == null) {
			userEntries = new ConcurrentHashMap<Integer, AccountEntry>();
		}
		userEntries.put(entryId, entry);
		entryId++;
	}
	
	@Override
	public List<AccountEntry> getEntries(int userId) {
		if (entries.get(userId) != null) {
			return new ArrayList<AccountEntry>(entries.get(userId).values());
		}
		
		return EMPTY_LIST;
	}
	
	@Override
	public List<AccountEntry> getAllEntries() {
		List<AccountEntry> allEntries = new ArrayList<AccountEntry>();
		
		for (Map<Integer, AccountEntry> userEntries : entries.values()) {
			allEntries.addAll(userEntries.values());
		}
		
		return allEntries;
	}

}
