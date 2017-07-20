package com.aflb.debttracker.data.dao.hibernate;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.UserDao;

public class UserDaoHibernateTest {
	private static UserDao dao;
	private static EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new UserDaoHibernate();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		em = dao.createEntityManager();
		clearTables(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testRetrieveUserByName() {
		em.getTransaction().begin();
		em.createNativeQuery("INSERT INTO t_users( user_id, name, date_added, description)"
				+ " VALUES (1, 'BOOM1', '1988-09-15', 'TEST USER1');").executeUpdate();
		em.createNativeQuery("INSERT INTO t_users( user_id, name, date_added, description)"
				+ " VALUES (2, 'BOOM2', '1988-09-15', 'TEST USER2');").executeUpdate();
		em.createNativeQuery("INSERT INTO t_users( user_id, name, date_added, description)"
				+ " VALUES (3, 'BOOM3', '1988-09-15', 'TEST USER3');").executeUpdate();
		em.getTransaction().commit();
		
		List<User> users = null;
		try {
			//TODO use .equals() once we ahve overridden appropriate methods
			users = dao.getUsers();
		} catch (Throwable th){
			fail(th.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.set(1988, Calendar.SEPTEMBER, 15, 0, 0, 0);
		
		assertEquals(3, users.size());
		for (int i = 0; i < 3; i++) {
			assertEquals("BOOM" + users.get(i).getId(), users.get(i).getName());
			assertEquals("TEST USER" + users.get(i).getId(), users.get(i).getDescription());
			assertEquals(cal.getTime().toString(), users.get(i).getDate().toString());
		}
	}
	
	private static void clearTables(EntityManager em) {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM T_ENTRIES;").executeUpdate();
		em.createNativeQuery("DELETE FROM T_USERS;").executeUpdate();
		em.getTransaction().commit();
	}

}
