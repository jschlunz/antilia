/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;



/**
 * Utility class that allows to easily add restrictions to a query.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Restrictions {

	private Restrictions() {		
	}
	
	/**
	 * Adds an ID equality restriction.
	 * @param value
	 * @return
	 */
	public static IRestriction idEq(Object value) {
		return new IdentifierEqRestriction(value);
	}
	
	/**
	 * Adds an equality restriction to property name.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction eq(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.EQUAL);
	}
	
	/**
	 * Adds a not equal restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction ne(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.NOTEQUAL);
	}
	
	/**
	 * Adds a like restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction like(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.LIKE);
	}
	
	/**
	 * Adds a like restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 * @return
	 */
	public static IRestriction like(String propertyName, String value, MatchMode matchMode) {
		return new SimpleRestriction(propertyName, matchMode.toMatchString(value), Operator.LIKE);
	}
	
	/**
	 * Adds an ilike restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction ilike(String propertyName, Object value) {
		return new IlikeRestriction(propertyName, value);
	}
	
	/**
	 * Adds an ilike restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 * @return
	 */
	public static IRestriction ilike(String propertyName, Object value, MatchMode matchMode) {
		return new IlikeRestriction(propertyName, matchMode.toMatchString(value.toString()));
	}
	
	/**
	 * Adds an greater than restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction gt(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.GREATER_THAN);
	}
	
	/**
	 * Adds an less than restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction lt(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.LESS_THAN);
	}
	
	/**
	 * Adds a less equal than restriction.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static IRestriction le(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.LESS_EQUAL_THAN);
	}
	
	/**
	 * 
	 * @param propertyName The property to which the restriction will apply.
	 * @param value The value of the restriction.
	 * @return
	 */
	public static IRestriction ge(String propertyName, Object value) {
		return new SimpleRestriction(propertyName, value, Operator.GREATER_EQUAL_THAN);
	}
	
	/**
	 * Adds a between restriction.
	 * 
	 * @param propertyName The property to which the restriction will apply.
	 * @param lo Low limit.
	 * @param hi Top limit 
	 * @return
	 */
	public static IRestriction between(String propertyName, Object lo, Object hi) {
		return new BetweenRestriction(propertyName, lo, hi);
	}
	
	/**
	 * Apply an "in" constraint to the named property
	 * 
	 * @param propertyName The property to which the restriction will apply.
	 * @param values The values included in the in constraint.
	 * @return {@link InRestriction}
	 */
	public static IRestriction in(String propertyName, Object[] values) {
		return new InRestriction(propertyName, values);
	}
	/**
	 * Apply an "in" constraint to the named property
	 * @param propertyName
	 * @param values
	 * @return {@link InRestriction}
	 */
	public static IRestriction in(String propertyName, Collection<?> values) {
		return new InRestriction( propertyName, values.toArray() );
	}
	
	/**
	 * Apply an "is null" constraint to the named property
	 * @return {@link NullRestriction}
	 */
	public static IRestriction isNull(String propertyName) {
		return new NullRestriction(propertyName);
	}
	
	/**
	 * Apply an "equal" constraint to two properties
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @param otherPropertyName The other property to which the restriction will apply
	 * @return {@link PropertyRestriction}
	 */
	public static PropertyRestriction eqProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.EQUAL);
	}
	
	/**
	 * Apply a "not equal" constraint to two properties
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @param otherPropertyName The other property to which the restriction will apply
	 * @return {@link PropertyRestriction}
	 */
	public static PropertyRestriction neProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.NOTEQUAL);
	}
	
	/**
	 * Apply a "less than" constraint to two properties
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @param otherPropertyName The other property to which the restriction will apply
	 * @return {@link PropertyRestriction}
	 */
	public static PropertyRestriction ltProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.LESS_THAN);
	}
	
	/**
	 * Apply a "less than or equal" constraint to two properties
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @param otherPropertyName The other property to which the restriction will apply
	 * @return
	 */
	public static PropertyRestriction leProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.LESS_EQUAL_THAN);
	}
	
	/**
	 * Apply a "greater than" constraint to two properties
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @param otherPropertyName The other property to which the restriction will apply
	 * @return
	 */
	public static PropertyRestriction gtProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.GREATER_THAN);
	}
	
	/**
	 * Apply a "greater than or equal" constraint to two properties
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @param otherPropertyName The other property to which the restriction will apply
	 * @return
	 */
	public static PropertyRestriction geProperty(String propertyName, String otherPropertyName) {
		return new PropertyRestriction(propertyName, otherPropertyName, Operator.GREATER_EQUAL_THAN);
	}
	
	/**
	 * Apply an "is not null" constraint to the named property
	 * 
	 * @param propertyName The property to which the restriction will apply
	 * @return
	 */
	public static IRestriction isNotNull(String propertyName) {
		return new NotNullRestriction(propertyName);
	}
	
	/**
	 * Return the conjunction of two restrictions.
	 * 
	 * @param lhs
	 * @param rhs
	 * @return {@link LogicalRestriction}
	 */
	public static IRestriction and(IRestriction lhs, IRestriction rhs) {
		return new LogicalRestriction(lhs, rhs, LogicalOperator.AND);
	}
	
	/**
	 * Return the disjunction of two expressions
	 *
	 * @param lhs
	 * @param rhs
	 * @return {@link LogicalRestriction}
	 */
	public static IRestriction or(IRestriction lhs, IRestriction rhs) {
		return new LogicalRestriction(lhs, rhs, LogicalOperator.OR);
	}
	
	/**
	 * Return the negation of a restriction.
	 *
	 * @param filter
	 * @return {@link NotRestriction}
	 */
	public static IRestriction not(IRestriction filter) {
		return new NotRestriction(filter);
	}
	
	/**
	 * @return Returns a {@link ConjunctionRestriction}
	 */
	public static ConjunctionRestriction conjunction() {
		return new ConjunctionRestriction();
	}
	
	/**
	 * 
	 * @return Returns a {@link DisjunctionRestriction}
	 */
	public static DisjunctionRestriction disjunction() {
		return new DisjunctionRestriction();
	}
	
	/**
	 * Creates a conjunction with equality restrictions with pairs 
	 * (porteprtyName, value) using the values stored on the Map.
	 * 
	 * @param propertyNameValues Map with pairs (propertyName,value)
	 * @return {@link ConjunctionRestriction}
	 */
	public static IRestriction allEq(Map<String, Object> propertyNameValues) {
		ConjunctionRestriction conj = conjunction();
		Iterator<Map.Entry<String, Object>> iter = propertyNameValues.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) iter.next();
			conj.add(eq(me.getKey(), me.getValue()) );
		}
		return conj;
	}
	
	/**
	 * Constrain a collection valued property to be empty
	 * 
	 * @param propertyName
	 * @return
	 */
	public static IRestriction isEmpty(String propertyName) {
		return new EmptyRestriction(propertyName);
	}

	/**
	 * Constrain a collection valued property to be non-empty
	 * 
	 * @param propertyName
	 * @return
	 */
	public static IRestriction isNotEmpty(String propertyName) {
		return new NotEmptyRestriction(propertyName);
	}
	
	/**
	 * Constrain a collection valued property by size: EQUAL to size.
	 * 
	 * @param propertyName
	 * @param size
	 * @return
	 */
	public static IRestriction sizeEq(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.EQUAL);
	}
	
	/**
	 * Constrain a collection valued property by size: NOT EQUAL to size.
	 * 
	 * @param propertyName
	 * @param size
	 * @return
	 */
	public static IRestriction sizeNe(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.NOTEQUAL);
	}
	
	/**
	 * Constrain a collection valued property by size:  greater than to size.
	 * 
	 * @param propertyName
	 * @param size
	 * @return
	 */
	public static IRestriction sizeGt(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.GREATER_THAN);
	}
	
	/**
	 * Constrain a collection valued property by size:  less than size
	 * 
	 * @param propertyName
	 * @param size
	 * @return
	 */
	public static IRestriction sizeLt(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.LESS_THAN);
	}
	
	/**
	 * Constrain a collection valued property by size:  greater or equal than size
	 * 
	 * @param propertyName
	 * @param size
	 * @return
	 */
	public static IRestriction sizeGe(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.GREATER_EQUAL_THAN);
	}
	
	/**
	 * Constrain a collection valued property by size: less or equal than size
	 * 
	 * @param propertyName
	 * @param size
	 * @return
	 */
	public static IRestriction sizeLe(String propertyName, int size) {
		return new SizeRestriction(propertyName, size, Operator.LESS_EQUAL_THAN);
	}
}
