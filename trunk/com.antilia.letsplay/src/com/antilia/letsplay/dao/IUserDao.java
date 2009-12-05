/**
 * 
 */
package com.antilia.letsplay.dao;

import com.antilia.common.dao.IQuerableUpdatableDao;
import com.antilia.letsplay.domain.User;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IUserDao extends IQuerableUpdatableDao<User> {

	User findByLogname(String logName);
	
}
