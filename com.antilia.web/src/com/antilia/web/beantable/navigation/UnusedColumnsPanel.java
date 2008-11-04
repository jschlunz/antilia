/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.dialog.util.CancelDialogButton;
import com.antilia.web.dialog.util.OkDialogButton;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UnusedColumnsPanel<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private ColumnModelPalette<E> palette;
	/**
	 * @param id
	 */
	public UnusedColumnsPanel(String id, UnusedColumnsDialog<E> dialog ) {
		super(id);
		
		
		add(new OkButton("ok", dialog) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onOk(AjaxRequestTarget target, Form<?> form) {
				List<IColumnModel<E>>  selected = palette.getSelected();
				List<IColumnModel<E>>  hidden = palette.getAvailable();
				Table<E> table = findTable();
				table.getTableModel().setColumnModels(selected);
				table.getTableModel().setHiddenModels(hidden);
				if(target != null) {
					target.addComponent(table);
				}
			}
		});
		
		add(new CancelDialogButton("cancel", dialog));
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		palette = new ColumnModelPalette<E>("palette", findTable());
		palette.setRenderBodyOnly(true);
		addOrReplace(palette);
	}
	
	@SuppressWarnings("unchecked")
	private Table<E> findTable() {
		return (Table<E>)findParent(Table.class);
	}
	
	
	
}
