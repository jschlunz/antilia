/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.Model;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class RowItem<E extends Serializable> extends Item<E> {

	private static final long serialVersionUID = 1L;

	private Table<E> table; 
	
	
	/**
	 * @param id
	 * @param index
	 * @param model
	 */
	public RowItem(String id, int index, final IComponentInheritedModel<E> model, Table<E> table) {
		super(id, index, model);
		this.table = table;
		String tableId = table.getMarkupId(); 
		add(new AttributeModifier("id", new Model<String>(tableId+"_r_"+index)));
		String clazz = "tbodyrow"+(index%2);
		add(new AttributeModifier("class", new Model<String>(clazz)));
		add(new AttributeModifier("onmouseover",new Model<String>(tableId+".highlight("+index+");")));
		add(new AttributeModifier("onmouseout",new Model<String>(tableId+".unhighlight("+index+");")));
		add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				getTable().onRowClickedEvent(target, getIndex());
			}
					
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new IAjaxCallDecorator() 
				{
					private static final long serialVersionUID = 1L;

					public CharSequence decorateOnFailureScript(CharSequence script) {
						return script;
					}

					public CharSequence decorateOnSuccessScript(CharSequence script) {
						return getTable().getMarkupId()+".toggleSelected("+getIndex()+");"+script;
					}

					public CharSequence decorateScript(CharSequence script) {
						return  script;
					}
					
				};
			}
		});
	}
	public Table<E> getTable() {
		return table;
	}
	public void setTable(Table<E> table) {
		this.table = table;
	}
}
