package com.antilia.web.beantable;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;

public class ToggleSelectAllButton<E extends Serializable> extends AbstractLink {

	private static final long serialVersionUID = 1L;

	public static final String ID = "t_selectd";
	private Table<E> table;
	
	
	public ToggleSelectAllButton(String id,Table<E> table) {
		super(id);
		this.table = table;
	}

	@Override
	protected ResourceReference getImage() {
		if(getTable().getSourceSelector().isPageSelected())
			return DefaultStyle.IMG_CHECKBOX_CHECKED;
		else 
			return DefaultStyle.IMG_CHECKBOX_UNCHECKED;
	}

	@Override
	protected String getLabel() {
		return null;
	}

	@Override
	protected void onClick(AjaxRequestTarget target) {		
		//IPageableSource<E> pageableSource = getTable().getPageableSource();
		IProviderSelector<E> selector = getTable().getSourceSelector();
		selector.togglePageSelection();
		Iterator<WebMarkupContainer> it = getTable().getRowCheckBoxes();
		while(it.hasNext()) {
			target.addComponent(it.next());
		}
		target.addComponent(this);
	}

	public Table<E> getTable() {
		return table;
	}

	public void setTable(Table<E> table) {
		this.table = table;
	}

}
