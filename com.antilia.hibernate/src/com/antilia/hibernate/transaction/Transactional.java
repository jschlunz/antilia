package com.antilia.hibernate.transaction;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The <code>Transactional</code> annotation describes all attributes
 * of interest for a transaction:
 * propagation, isolationLevel, timeout and readOnly. It can be applied
 * to an <code>ICommand</code> implementation to indicate if it needs a transaction,
 * what isolationLevel it should use, what timeout it should have, and if it
 * is a readOnly service. The <code>CommandExecuter</code> implementation
 * takes these values into account when executing the command. Depending on
 * the implementation of <code>CommandExecuter</code>, some of these
 * transaction settings might not be supported.
 *
 */
@Target({METHOD})
@Retention(RUNTIME)
@Inherited
@Documented
public @interface Transactional {
	
	static final int TIMEOUT_DEFAULT = -1;

	/**
	 * The transaction propagation type.
     * <p>Defaults to {@link Propagation#REQUIRED}.
	 */
	Propagation propagation() default Propagation.SUPPORTS;
	
    /**
     * The transaction isolation level.
     * <p>Defaults to {@link Isolation#DEFAULT}.
     */
	Isolation isolation() default Isolation.DEFAULT;
	
	/**
     * The transaction timeout.
     * <p>Defaults to {@link TIMEOUT_DEFAULT}.
	 */
	int timeout() default TIMEOUT_DEFAULT;
	
	/**
	 * <code>true</code> if the transaction is read-only.
     * <p>Defaults to <code>false</code>. 
	 */
	boolean readOnly() default false;
}
