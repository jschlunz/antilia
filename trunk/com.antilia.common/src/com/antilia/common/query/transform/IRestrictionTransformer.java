/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query.transform;

import com.antilia.common.query.IRestriction;

/**
 * Defines a transformer for {@link IRestriction}s into somethig else (e.g. Hibernate criterias).
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IRestrictionTransformer<R> extends ITransformer<IRestriction, R> {

}
