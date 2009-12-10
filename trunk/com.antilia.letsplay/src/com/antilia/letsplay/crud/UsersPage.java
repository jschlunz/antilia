/**
 * 
 */
package com.antilia.letsplay.crud;

import com.antilia.letsplay.domain.User;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UsersPage extends CRUDPage<User> implements IProtectedPage  {

	public UsersPage() {
		super(User.class);
	}
}
