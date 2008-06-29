package com.antilia.hibernate.command;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;

public interface ILoadCommand<E extends Serializable> {

	/**
	 * @return the hql
	 */
	public abstract String getHql();

	/**
	 * @return the criteria
	 */
	public abstract Criterion[] getCriteria();

	/**
	 * @return the sample
	 */
	public abstract E getSample();

}