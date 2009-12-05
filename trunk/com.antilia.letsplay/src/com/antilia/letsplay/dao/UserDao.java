/**
 * 
 */
package com.antilia.letsplay.dao;

import com.antilia.hibernate.dao.impl.HibernateQuerableUpdatableDao;
import com.antilia.letsplay.domain.User;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UserDao extends HibernateQuerableUpdatableDao<User> implements IUserDao {

	private static final long serialVersionUID = 1L;

	public UserDao() {
	}
	
	public User findByLogname(String logName) {
		User user = new User();
		user.setLogname(logName);		
		return findByExample(user).get(0);
	}
}
