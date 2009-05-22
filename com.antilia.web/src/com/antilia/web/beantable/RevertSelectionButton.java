package com.antilia.web.beantable;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.button.AbstractLink;
import com.antilia.web.navigator.INavigatorSelector;
import com.antilia.web.resources.DefaultStyle;

public class RevertSelectionButton<E extends Serializable> extends AbstractLink {

	private static final long serialVersionUID = 1L;

	public static final String ID = "t_revert";
	private Table<E> table;
	
	
	public RevertSelectionButton(String id,Table<E> table) {
		super(id);
		this.table = table;
	}

	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_REVERT_SELECTED;
	}

	@Override
	protected String getLabel() {
		return null;
	}

	@Override
	protected void onClick(AjaxRequestTarget target) {		
		//IPageableSource<E> pageableSource = getTable().getPageableSource();
		INavigatorSelector<E> selector = getTable().getSourceSelector();
		selector.revertPageSelection();
		Iterator<WebMarkupContainer> it = getTable().getRowCheckBoxes();
		while(it.hasNext()) {
			target.addComponent(it.next());
		}
	}

	public Table<E> getTable() {
		return table;
	}

	public void setTable(Table<E> table) {
		this.table = table;
	}

}
