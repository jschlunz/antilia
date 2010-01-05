package com.antilia.letsplay;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class NullSafeModel<T> extends AbstractReadOnlyModel<T> {

	private static final long serialVersionUID = 1L;

	private IModel<T> defaultModel;
	private IModel<T> nullModel;
	
	public NullSafeModel(IModel<T> defaultModel, IModel<T> nullModel) {
		this.defaultModel = defaultModel;
		this.nullModel = nullModel;
	}
	
	@Override
	public T getObject() {
		T object = this.defaultModel.getObject();
		if(object != null) 
			return object;				
		return this.nullModel.getObject();
	}
}
