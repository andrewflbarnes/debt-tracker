/**
 * 
 */
package com.aflb.debttracker.data.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Barnesly
 * 
 *         Helper class for providing {@link EntityManager}s for hibernate
 *         implementations of the DAOs
 */
public class HibernateHelper {
	
	private static EntityManagerFactory entityManagerFactory;
	private static final Logger logger = LoggerFactory.getLogger(HibernateHelper.class);

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("debttracker");
		} catch (Throwable th) {
			logger.error("Unable to create SessionFactory: " + th.getMessage());
			throw new ExceptionInInitializerError(th);
		}
	}
	
	/**
	 * @return
	 * @see javax.persistence.EntityManagerFactory#createEntityManager()
	 */
	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * @return
	 * @see javax.persistence.EntityManagerFactory#getCriteriaBuilder()
	 */
	public static CriteriaBuilder getCriteriaBuilder() {
		return entityManagerFactory.getCriteriaBuilder();
	}

}
