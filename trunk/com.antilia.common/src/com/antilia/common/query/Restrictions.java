/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Restrictions {

	private Restrictions() {		
	}
	
	public static IRestrictionFilter idEq(Object value) {
		return new IdentifierEqRestriction(value);
	}
	
	public static IRestrictionFilter eq(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.EQUAL);
	}
	
	public static IRestrictionFilter ne(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.NOTEQUAL);
	}
	
	public static IRestrictionFilter like(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.LIKE);
	}
	
	public static IRestrictionFilter like(String propertyName, String value, MatchMode matchMode) {
		return new SimpleRestriction(propertyName, matchMode.toMatchString(value), Operator.LIKE);
	}
	
	public static IRestrictionFilter ilike(String propertyName, Object value) {
		return new IlikeRestriction(propertyName, value);
	}
	
	public static IRestrictionFilter ilike(String propertyName, Object value, MatchMode matchMode) {
		return new IlikeRestriction(propertyName, matchMode.toMatchString(value.toString()));
	}
	
	public static IRestrictionFilter gt(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.GREATER_THAN);
	}
	
	public static IRestrictionFilter lt(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.LESS_THAN);
	}
	
	public static IRestrictionFilter le(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.LESS_EQUAL_THAN);
	}
	
	public static IRestrictionFilter ge(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.GREATER_EQUAL_THAN);
	}
	
	public static IRestrictionFilter between(String propertyName, Object lo, Object hi) {
		return new BetweenRestriction(propertyName, lo, hi);
	}
	
	/**
	 * Apply an "in" constraint to the named property
	 * @param propertyName
	 * @param values
	 * @return Criterion
	 */
	public static IRestrictionFilter in(String propertyName, Object[] values) {
		return new InRestriction(propertyName, values);
	}
	/**
	 * Apply an "in" constraint to the named property
	 * @param propertyName
	 * @param values
	 * @return Criterion
	 */
	public static IRestrictionFilter in(String propertyName, Collection<?> values) {
		return new InRestriction( propertyName, values.toArray() );
	}
	
	/**
	 * Apply an "is null" constraint to the named property
	 * @return Criterion
	 */
	public static IRestrictionFilter isNull(String propertyName) {
		return new NullRestriction(propertyName);
	}
	
	/**
	 * Apply an "equal" constraint to two properties
	 */
	public static PropertyRestriction eqProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.EQUAL);
	}
	/**
	 * Apply a "not equal" constraint to two properties
	 */
	public static PropertyRestriction neProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.NOTEQUAL);
	}
	/**
	 * Apply a "less than" constraint to two properties
	 */
	public static PropertyRestriction ltProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.LESS_THAN);
	}
	/**
	 * Apply a "less than or equal" constraint to two properties
	 */
	public static PropertyRestriction leProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.LESS_EQUAL_THAN);
	}
	/**
	 * Apply a "greater than" constraint to two properties
	 */
	public static PropertyRestriction gtProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.GREATER_THAN);
	}
	/**
	 * Apply a "greater than or equal" constraint to two properties
	 */
	public static PropertyRestriction geProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.GREATER_EQUAL_THAN);
	}
	
	/**
	 * Apply an "is not null" constraint to the named property
	 * @return Criterion
	 */
	public static IRestrictionFilter isNotNull(String propertyName) {
		return new NotNullRestriction(propertyName);
	}
	/**
	 * Return the conjuction of two expressions
	 *
	 * @param lhs
	 * @param rhs
	 * @return Criterion
	 */
	public static IRestrictionFilter and(IRestrictionFilter lhs, IRestrictionFilter rhs) {
		return new LogicalRestriction(lhs, rhs, LogicalOperator.AND);
	}
	
	/**
	 * Return the disjunction of two expressions
	 *
	 * @param lhs
	 * @param rhs
	 * @return Criterion
	 */
	public static IRestrictionFilter or(IRestrictionFilter lhs, IRestrictionFilter rhs) {
		return new LogicalRestriction(lhs, rhs, LogicalOperator.OR);
	}
	/**
	 * Return the negation of an expression
	 *
	 * @param expression
	 * @return Criterion
	 */
	public static IRestrictionFilter not(IRestrictionFilter filter) {
		return new NotRestriction(filter);
	}
	
	public static ConjunctionFilter conjunction() {
		return new ConjunctionFilter();
	}
	
	public static DisjunctionFilter disjunction() {
		return new DisjunctionFilter();
	}
	
	public static IRestrictionFilter allEq(Map<String, Object> propertyNameValues) {
		ConjunctionFilter conj = conjunction();
		Iterator<Map.Entry<String, Object>> iter = propertyNameValues.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) iter.next();
			conj.add( eq( (String) me.getKey(), me.getValue() ) );
		}
		return conj;
	}
	
	/**
	 * Constrain a collection valued property to be empty
	 */
	public static IRestrictionFilter isEmpty(String propertyName) {
		return new EmptyRestriction(propertyName);
	}

	/**
	 * Constrain a collection valued property to be non-empty
	 */
	public static IRestrictionFilter isNotEmpty(String propertyName) {
		return new NotEmptyRestriction(propertyName);
	}
	
	/**
	 * Constrain a collection valued property by size
	 */
	public static IRestrictionFilter sizeEq(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.EQUAL);
	}
	
	/**
	 * Constrain a collection valued property by size
	 */
	public static IRestrictionFilter sizeNe(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.NOTEQUAL);
	}
	
	/**
	 * Constrain a collection valued property by size
	 */
	public static IRestrictionFilter sizeGt(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.LESS_THAN);
	}
	
	/**
	 * Constrain a collection valued property by size
	 */
	public static IRestrictionFilter sizeLt(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.GREATER_THAN);
	}
	
	/**
	 * Constrain a collection valued property by size
	 */
	public static IRestrictionFilter sizeGe(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.LESS_EQUAL_THAN);
	}
	
	/**
	 * Constrain a collection valued property by size
	 */
	public static IRestrictionFilter sizeLe(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.GREATER_EQUAL_THAN);
	}
}
