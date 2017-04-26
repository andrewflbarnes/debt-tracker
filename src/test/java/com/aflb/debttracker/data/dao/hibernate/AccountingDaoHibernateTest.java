/**
 * 
 */
package com.aflb.debttracker.data.dao.hibernate;

import static org.junit.Assert.fail;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aflb.debttracker.data.AccountEntry;
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
		AccountingDao dao = new AccountingDaoHibernate();
		int barnes = 1;
		AccountEntry entry = new AccountEntry(1, 10, new Date(), "test");
		EntityManager em = dao.createEntityManager();
		em.persist(entry);
		try {
			dao.getEntries(barnes);
		} catch (Throwable th){
			fail(th.getMessage());
		}
		em.close();
	}

}
