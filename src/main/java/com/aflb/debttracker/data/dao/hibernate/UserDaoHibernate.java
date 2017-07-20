/**
 * 
 */
package com.aflb.debttracker.data.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.UserDao;

/**
 * @author Barnesly
 *
 */
public class UserDaoHibernate implements UserDao {
	
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.aflb.debttracker.data.dao.UserDao#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		CriteriaBuilder builder = HibernateHelper.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public EntityManager createEntityManager() {
		entityManager = HibernateHelper.createEntityManager();
		return entityManager;
	}

}
