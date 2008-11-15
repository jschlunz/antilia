/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;
import com.antilia.web.field.factory.DefaultFieldFactory;
import com.antilia.web.field.impl.BaseFormField;
import com.antilia.web.field.impl.TextField;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AutoFieldPanel<B extends Serializable> extends Panel implements IFieldPanel {

	private static final long serialVersionUID = 1L;

	private int columns = 3;
	
	private IAutoFieldCreator<B> autoFieldModel;
	
	private class Rows extends RefreshingView<B> {
 		
		private static final long serialVersionUID = 1L;

		private AutoFieldPanel<B> autoFieldPanel;
		
		public Rows(String id, AutoFieldPanel<B> autoFieldPanel) {
			super(id);
			this.autoFieldPanel = autoFieldPanel;
			autoFieldPanel.add(this);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected Iterator getItemModels() {
			List<IModel> models = new ArrayList<IModel>();
			int columns = autoFieldPanel.getColumns();
			IAutoFieldCreator<B> model = autoFieldPanel.getAutoFieldModel();
			List<IFieldModel<B>> fieldModels =  model.getFieldModels();
			int size = fieldModels.size();
			// compute the number of rows... and create an Integer model for each row
			int rows = (size/columns) ;
			if(size%columns != 0)
				rows +=1;
			for(int i = 0; i < rows; i++) {
				models.add(new Model(new Integer(i)));
			}				
			return models.iterator();
		}
		 
		 @Override
		protected void populateItem(Item<B> item) {
			int columns = autoFieldPanel.getColumns(); 
			IAutoFieldCreator<B> model = autoFieldPanel.getAutoFieldModel();
			List<IFieldModel<B>> fieldModels =  model.getFieldModels();
			int size = fieldModels.size();
			RepeatingView cols = new RepeatingView("cols");
			cols.setRenderBodyOnly(true);
			item.add(cols);
			Integer row = (Integer)item.getModel().getObject();
			for(int i=0; i < columns; i++) {				
				int index =  columns*row + i;
				if(index < size) {
					IFieldModel<B> fieldModel = fieldModels.get(index);
					cols.add(newFormField(cols.newChildId(), fieldModel, index));
				} else
					cols.add(new Label(cols.newChildId(), ""));
			}
		}
		
	}
	
	/**
	 * @param id
	 */
	public AutoFieldPanel(String id, IAutoFieldCreator<B> autoFieldModel, int columns) {
		super(id);		
		this.columns = columns;
		
		setOutputMarkupId(true);
		
		if(autoFieldModel == null)
			throw new IllegalArgumentException("autoFieldModel cannnot be null");
		
		this.autoFieldModel = autoFieldModel;		
		new Rows("rows", this);
	}
	
	/**
	 * @param id
	 */
	public AutoFieldPanel(String id, IAutoFieldCreator<B> autoFieldModel) {
		this(id, autoFieldModel, 3);
	}
	
	/**
	 * Override this method for providing a custom form field creation:
	 * 
	 * @param id
	 * @param fieldModel
	 * @param index
	 * @return
	 */
	protected Component newFormField(String id, final IFieldModel<B> fieldModel, int index) {
		return new AutoFieldCell(id) {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected Label newLabel(String id) {
				return new Label(id, getLabelModel(fieldModel));
			}
			@SuppressWarnings("unchecked")
			@Override
			protected Component newField(String id) {
				Component field = DefaultFieldFactory.getInstance().newField(id, fieldModel);
				if(field instanceof BaseFormField) {
					((BaseFormField)field).getLabel().setVisible(false);
				}
				if(field != null)
					return field;
				TextField<B> textField = new TextField<B>(id, fieldModel);
				textField.getLabel().setVisible(false);
				return textField;
			}
		};		
	}
	
	protected IModel<String> getLabelModel(final IFieldModel<B> fieldModel) {
		String key = ResourceUtils.getPropertyResourceKey(fieldModel.getBeanClass(), fieldModel.getPropertyPath());
		return new StringResourceModel(key, this, null) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected String load() {				
				return super.load() + ":";
			}
		};		
	}

	public IAutoFieldCreator<B> getAutoFieldModel() {
		return autoFieldModel;
	}

	public void setAutoFieldModel(IAutoFieldCreator<B> autoFieldModel) {
		this.autoFieldModel = autoFieldModel;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
}
