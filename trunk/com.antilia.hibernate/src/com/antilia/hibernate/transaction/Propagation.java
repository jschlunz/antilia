package com.antilia.hibernate.transaction;

/**
 * Enumeration for transaction propagation.
 *
 */
public enum Propagation {	
	/**
	 * Support a current transaction, throw an exception if none exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	MANDATORY,

	/**
	 * Support a current transaction, create a new one if none exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	REQUIRED,

	/**
	 * Create a new transaction, suspend the current transaction if one exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	REQUIRES_NEW,

	/**
	 * Support a current transaction, execute non-transactionally if none exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	SUPPORTS,

	/**
	 * Execute non-transactionally, suspend the current transaction if one exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	NOT_SUPPORTED,

	/**
	 * Execute non-transactionally, throw an exception if a transaction exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	NEVER
}
