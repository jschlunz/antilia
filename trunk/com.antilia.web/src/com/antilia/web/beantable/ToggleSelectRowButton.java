package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;

public class ToggleSelectRowButton<E extends Serializable> extends AbstractLink {

	private static final long serialVersionUID = 1L;

	public static final String ID = "t_selectd";
	private Table<E> table;
	
	private int index;

	public ToggleSelectRowButton(String id,Table<E> table, int index) {
		super(id);
		this.table = table;
		this.index = index;
	}

	@Override
	protected ResourceReference getImage() {
		IProviderSelector<E> source = getTable().getSourceSelector();
		boolean selected = source.isSelected(getIndex());
		if(selected)
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
		getTable().getSourceSelector().toggleSelected(getIndex());
		getTable().onRowClickedEvent(target, getIndex());
		if(target != null) {
			target.addComponent(this.getParent());
		}
	}

	public Table<E> getTable() {
		return table;
	}

	public void setTable(Table<E> table) {
		this.table = table;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}