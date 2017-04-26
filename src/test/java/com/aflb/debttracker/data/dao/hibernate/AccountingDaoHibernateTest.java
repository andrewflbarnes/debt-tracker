/**
 * 
 */
package com.aflb.debttracker.data.dao.hibernate;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aflb.debttracker.data.AccountEntry;
import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.AccountingDao;
/**
 * @author Barnesly
 *
 */
public class AccountingDaoHibernateTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		User barnes = new User("barnes");
		List<AccountEntry> entries = new ArrayList<>(1);

		// Add user and account entry records
		//TODO Use real SQL statements to add the data as adding in this way
		// is dependedant on another feature which needs testing
		AccountEntry entry = new AccountEntry(barnes, 10, new Date(), "test");
		entries.add(entry);
		barnes.setAccountEntries(entries);
		
		AccountingDao dao = new AccountingDaoHibernate();
		EntityManager em = dao.createEntityManager();

		em.getTransaction().begin();
		em.persist(barnes);
		em.flush();
		em.getTransaction().commit();
		
		AccountEntry returnedEntry = null;
		try {
			//TODO use .equals() once we ahve overridden appropriate methods
			 returnedEntry = dao.getEntries(barnes).get(0);
		} catch (Throwable th){
			fail(th.getMessage());
		}
		em.close();

		assertEquals(entry.getValue(), returnedEntry.getValue(), 0.001);
		assertEquals(entry.getDescription(), returnedEntry.getDescription());
		assertEquals(entry.getType().toString(), returnedEntry.getType().toString());
		assertEquals(entry.getDate().toString(), returnedEntry.getDate().toString());
	}

}
