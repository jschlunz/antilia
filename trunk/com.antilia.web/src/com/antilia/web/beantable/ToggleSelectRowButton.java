package com.antilia.web.beantable;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.beantable.provider.SelectionMode;
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
		if(this.table.getTableModel().getSelectionMode().equals(SelectionMode.MULTIPLE)) {		
			if(selected)
				return DefaultStyle.IMG_CHECKBOX_CHECKED;
			else 
				return DefaultStyle.IMG_CHECKBOX_UNCHECKED;
		} else if(this.table.getTableModel().getSelectionMode().equals(SelectionMode.SINGLE)) {
			if(selected)
				return DefaultStyle.IMG_RADIO_CHECKED;
			else 
				return DefaultStyle.IMG_RADIO_UNCHECKED;
		}
		return null;
	}

	@Override
	protected String getLabel() {
		return null;
	}

	@Override
	protected void onClick(AjaxRequestTarget target) {
		E bean = getTable().getSourceSelector().toggleSelected(getIndex());		
		getTable().onRowClickedEvent(target, getIndex(), bean, getTable().getSourceSelector().isSelected(getIndex()));
		if(this.table.getTableModel().getSelectionMode().equals(SelectionMode.MULTIPLE)) {
			if(target != null) {
				target.addComponent(this.getParent());
			}
		} else if(this.table.getTableModel().getSelectionMode().equals(SelectionMode.SINGLE)) {
			if(target != null) {
				Iterator<WebMarkupContainer> it = getTable().getRowCheckBoxes();
				while(it.hasNext()) {
					target.addComponent(it.next());
				}
			}
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
