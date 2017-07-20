/**
 * 
 */
package com.aflb.debttracker.data.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.AccountingDao;
/**
 * @author Barnesly
 *
 */
@Ignore
public class AccountingDaoHibernateTest {
	private static AccountingDao dao;
	private static EntityManager em;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AccountingDaoHibernate();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		em = dao.createEntityManager();
		clearTables(em);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testRetrieveUserEntries() {
		User boom = new User("BOOM");
		boom.setId(1);
		em.getTransaction().begin();
		em.createNativeQuery("INSERT INTO t_users( user_id, name, date_added, description)"
				+ " VALUES (1, 'BOOM', '1988-09-15', 'TEST USER');").executeUpdate();
		em.createNativeQuery("INSERT INTO t_entries( entry_id, user_id, val, date_added, description)"
				+ " VALUES (1, 1, 10, '1988-09-15', 'TEST ENTRY');").executeUpdate();
		em.createNativeQuery("INSERT INTO t_entries( entry_id, user_id, val, date_added, description)"
				+ " VALUES (2, 1, 20, '1988-09-15', 'TEST ENTRY');").executeUpdate();
		em.createNativeQuery("INSERT INTO t_entries( entry_id, user_id, val, date_added, description)"
				+ " VALUES (3, 1, 30, '1988-09-15', 'TEST ENTRY');").executeUpdate();
		em.getTransaction().commit();
		
		List<AccountEntry> entries = null;
		try {
			//TODO use .equals() once we ahve overridden appropriate methods
			 entries = dao.getEntries(boom);
		} catch (Throwable th){
			fail(th.getMessage());
		}

		User returnedUser = entries.get(0).getUser();
		Calendar cal = Calendar.getInstance();
		cal.set(1988, Calendar.SEPTEMBER, 15, 0, 0, 0);
		
		assertEquals("BOOM", returnedUser.getName());
		assertEquals(1, returnedUser.getId());
		assertEquals("TEST USER", returnedUser.getDescription());
		assertEquals(cal.getTime().toString(), returnedUser.getDate().toString());
		
		assertEquals(3, entries.size());
		for (int i = 0; i < 3; i++) {
			assertEquals("TEST ENTRY", entries.get(i).getDescription());
			assertEquals(1, entries.get(i).getUser().getId());
			assertEquals(cal.getTime().toString(), entries.get(i).getDate().toString());
		}
	}
	
	private static void clearTables(EntityManager em) {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM T_ENTRIES;").executeUpdate();
		em.createNativeQuery("DELETE FROM T_USERS;").executeUpdate();
		em.getTransaction().commit();
	}
}
