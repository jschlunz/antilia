/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.model.FirstColumnModel;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.beantable.provider.impl.InMemoryPageableProvider;
import com.antilia.web.beantable.provider.impl.SourceSelector;
import com.antilia.web.button.AjaxRefreshableMenuItem;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Table<E extends Serializable> extends Panel implements IPageableComponent<E> {

	private static final long serialVersionUID = 1L;

	private IPageableProvider<E> pageableProvider; 
	
	private IProviderSelector<E> sourceSelector;
	
	private ITableModel<E> tableModel;
			
	private FirstColumnModel firstColumnModel;
	
	private MenuItemsFactory beforeNavigationMenuItemsFactory = new MenuItemsFactory();
	
	private MenuItemsFactory afterNavigationMenuItemsFactory = new MenuItemsFactory();
	
	private MenuItemsFactory firstHeaderMenuItemsFactory = new MenuItemsFactory();
	
	private List<WebMarkupContainer> rowCheckboxes = new ArrayList<WebMarkupContainer>();
	
	private List<String> draggerURL= new ArrayList<String>();
	
	/**
	 * This variable is needed t fix a problem with drag and drop not working for IE
	 */
	private int rendringCount = 0;
	
	/**
	 * Constructor accepting a List.
	 * 
	 * @param id
	 * @param tableModel
	 * @param elements
	 */
	public Table(String id, ITableModel<E> tableModel, List<E> elements) {
		this(id, tableModel, new InMemoryPageableProvider<E>(elements, tableModel.getBeanClass()));
	}
	
	/**
	 * Constructor accepting oageable source.
	 * 
	 * @param id
	 */
	public Table(String id, ITableModel<E> tableModel, IPageableProvider<E> pageableSource) {
		super(id);
		setOutputMarkupId(true);
		this.pageableProvider = pageableSource;
		this.sourceSelector = new SourceSelector<E>(this.pageableProvider ,tableModel.getSelectionModel());
		this.tableModel = tableModel;
		this.firstColumnModel = new FirstColumnModel(65);
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_PROTOTYPE));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_EFFECT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_DRAGDROP));
		add(HeaderContributor.forCss(DefaultStyle.CSS_MAIN));
		add(HeaderContributor.forCss(getTableCSS()));
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_EVENT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_MIN));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_EVENT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_ANIMATION));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DRAG_DROP));
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_TABLE));
		
		addMenuItemsAfterNavidation(getAfterNavigationMenuItemsFactory());
		
		getFirstHeaderMenuItemsFactory().addItem(
				AjaxRefreshableMenuItem.createRefreshableMenuItem(
						ToggleSelectAllButton.ID, 
						new ToggleSelectAllButton<E>(
								AjaxRefreshableMenuItem.getItemId(), this)));
		getFirstHeaderMenuItemsFactory().addItem(				
						new RevertSelectionButton<E>(RevertSelectionButton.ID, this));		
		addFirstHeaderMenuItems(getFirstHeaderMenuItemsFactory());
		
		addMenuItemsBeforeNavigation(getBeforeNavigationMenuItemsFactory());
		
	}
	
	@Override
	protected void onBeforeRender() {	
		super.onBeforeRender();		
	
		rendringCount++;
		
		draggerURL.clear();
		
		addOrReplace(newTableHeader("header"));

		// add table header
		addOrReplace(getHeaderRows("hcols"));
	
		// add table body
		addOrReplace(getBodyRows("rows"));
		
		Label script = new Label("script", new Model()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				String tableId =  Table.this.getMarkupId()!=null?Table.this.getMarkupId():"table";
				StringBuffer sb = new StringBuffer();
				sb.append("var ");
				sb.append(tableId);
				sb.append(" = new Table('" + tableId + "',");
				sb.append("new Array(");
				IPageableProvider<E> source = Table.this.getPageableProvider();
				IProviderSelector<E> selector = Table.this.getSourceSelector();
				Iterator<E> it = source.getCurrentPage();
				int i=0;
				while(it.hasNext()) {
					boolean selected = selector.isSelected(i);					
					it.next();
					sb.append("new Row('");
					sb.append(tableId);
					sb.append("',");
					sb.append(i);
					sb.append(",");
					sb.append(selected);
					sb.append(")");
					if(it.hasNext())
						sb.append(",");
					i++;					
				}		
				sb.append(")");
				sb.append("," + (Table.this.getTableModel().getColumns()+1));
				sb.append("," + (Table.this.getRendringCount()));
				sb.append("," + Table.this.getDraggerUrlAsArray());
				sb.append(");");
				sb.append(tableId+".");
				sb.append("removeOldDroppables();");				
				sb.append(tableId+".");
				sb.append("createDraggables();");				
				/*
				for(String dragger: draggerIds) {
					sb.append(dragger);
				}
				*/
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		addOrReplace(script);
	}
	
	public void addDraggerUrl(String id) {
			draggerURL.add(id);
	}
	
	public String getDraggerUrlAsArray() {
		StringBuffer sb = new StringBuffer();
		sb .append("new Array(");
		Iterator<String> it = draggerURL.iterator();
		while(it.hasNext()) {
			String url = it.next();			
			sb .append("'");	
			sb .append(url);
			sb .append("'");
			if(it.hasNext()) {
				sb .append(",");	
			}
		}
		sb .append(")");
		return sb.toString();		
	}
	
	public void populateRowMenu(IMenuItemHolder menu, int row, E bean) {
		
	}
	
	protected ResourceReference getTableCSS() {
		return DefaultStyle.CSS_TABLE;
	}
	
	protected RefreshingView getHeaderRows(String id) {
		return new HeaderRows("hcols", this);
	}
	
	protected RefreshingView getBodyRows(String id) {
		return new BodyRows(id, this);
	}
	
	
	private class HeaderRows extends RefreshingView {

		private static final long serialVersionUID = 1L;
		
		private Table<E> table;
		
		public HeaderRows(String id, Table<E> table) {
			super(id);
			this.table = table;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Iterator<IModel> getItemModels() {
			ITableModel tableModel = getTable().getTableModel();		
			List<IModel> models = new ArrayList<IModel>();
			models.add(new Model(""));
			Iterator<IModel> it =  tableModel.getColumnModels();
			while(it.hasNext()) {
				IModel model = it.next();
				models.add(model);
			}
			return models.iterator();
		}
		
		@Override
		protected Item newItem(String id, int index, final IModel model) {
			Item item = super.newItem(id, index, model);
			if(model instanceof IColumnModel) {			
				item.add(new AttributeModifier("width", true, new Model() {
					
					private static final long serialVersionUID = 1L;
	
					@SuppressWarnings("unchecked")
					@Override
					public Object getObject() {
						String width = ((IColumnModel<E>)model).getWidth()+" px";
						return width;
					}
					
				}));
			} else {
				item.add(new AttributeModifier("width", new Model() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public Object getObject() {
						String width = Table.this.getFirstColumnModel().getWidth()+ "px";
						return width;
					}
					
				}));
			}
			return item;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void populateItem(Item item) {			
			IModel model = (IModel)item.getModel();
			if(model instanceof IColumnModel) {
				IColumnModel<E> columnModel = (IColumnModel<E>)model;
				item.add(Table.this.newHeaderCell("hcell", item.getIndex(), getTable(), columnModel, columnModel.getTableModel().getBeanClass()));
			} else {
				item.add(Table.this.newFirstHeaderCell("hcell", item.getIndex(), getTable()));
			}
		}
		
		private Table<E> getTable() {
			return table;
		}
		
	}
	
	protected void addRowCheckBox(WebMarkupContainer rowCheckBox) {
		rowCheckboxes.add(rowCheckBox);
	}
	
	protected void clearRowCheckBoxes() {
		rowCheckboxes.clear();
	}
	
	protected Iterator<WebMarkupContainer> getRowCheckBoxes() {
		return rowCheckboxes.iterator();
	}
	
	protected void onRowClickedEvent(AjaxRequestTarget target, int row) {
		
	}
	
	protected void addMenuItemsBeforeNavigation(MenuItemsFactory factory) {
		
	}
	
	protected void addMenuItemsAfterNavidation(MenuItemsFactory factory) {
		
	}

	protected void addFirstHeaderMenuItems(MenuItemsFactory factory) {
		
	}
	
	private class BodyRows extends RefreshingView {

		private static final long serialVersionUID = 1L;
	
		private List<IComponentInheritedModel> models;
		
		private Table<E> table;
		
		public BodyRows(String id, Table<E> table) {
			super(id);
			this.table = table;			
		}
		
		@SuppressWarnings("unchecked")
		private void initialize() {
			Table.this.clearRowCheckBoxes();
			models = new ArrayList<IComponentInheritedModel>();
			ITableModel tableModel = getTable().getTableModel();
			if(tableModel == null)
				return;	
			Iterator<E> it = getTable().getPageableProvider().getCurrentPage();
			while(it.hasNext()) {
				E object = it.next();
				models.add(tableModel.newModel(object));
			}
		}

		@Override
		protected Item newItem(String id, int index, IModel model) {
			return new RowItem<E>(id, index, (IComponentInheritedModel)model, table);
		}
		
		@Override
		protected Iterator<IComponentInheritedModel> getItemModels() {
			initialize();
			return models.iterator();
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void populateItem(Item item) {		
			ITableModel tableModel = getTable().getTableModel();
			if(tableModel == null)
				return;
			RepeatingView rowView = new RepeatingView("cols");
			item.add(rowView);
			
			Iterator<IColumnModel> it = tableModel.getColumnModels();
			E bean = (E)item.getModelObject();
			rowView.add(newFirstBodyCell(rowView.newChildId(), item.getIndex(),getTable(), (RowItem)item));
			// Populate the row
			while (it.hasNext()) {
				IColumnModel columnModel = it.next();
				rowView.add(Table.this.newBodyCell(rowView.newChildId(), columnModel, bean));
			}		
		}
		
		private Table<E> getTable() {
			return table;
		}
		
	}
	
	protected Panel newTableHeader(String id) {
		return new DefaultTableHeader<E>(id, this);
	}
	
	protected WebMarkupContainer newFirstBodyCell(String id, int row, Table<E> table, RowItem<E> item) {
		return new FirstBodyCell<E>(id, row, table, item);
	}
	
	protected WebMarkupContainer newBodyCell(String id, IColumnModel<E> columnModel, E object) {
		return new DefaultBodyCell<E>(id, columnModel, object);
	}
	
	protected  WebMarkupContainer newFirstHeaderCell(String id, int index, Table<E> table) {
		return new FirstHeaderCell<E>(id, index, table);
	}
	
	protected  WebMarkupContainer newHeaderCell(String id, int index, Table<E> table, IColumnModel<E> columnModel, Class<E> beanClass) {
		//return new DefaultHeaderCell<E>(id, index, table, columnModel, beanClass);
		return new DefaultHeaderCell1<E>(id, index, table, columnModel, beanClass);
	}

	
	/**
	 * @return the pageableSource
	 */
	public IPageableProvider<E> getPageableProvider() {
		return pageableProvider;
	}

	/**
	 * @param pageableProvider the pageableSource to set
	 */
	public void setPageableProvider(IPageableProvider<E> elements) {
		this.pageableProvider = elements;
	}

	/**
	 * @return the tableModel
	 */
	public ITableModel<E> getTableModel() {
		return tableModel;
	}
	
	public MenuItemsFactory getBeforeNavigationMenuItemsFactory() {
		return beforeNavigationMenuItemsFactory;
	}
	
	public MenuItemsFactory getAfterNavigationMenuItemsFactory() {
		return afterNavigationMenuItemsFactory;
	}

	public MenuItemsFactory getFirstHeaderMenuItemsFactory() {
		return firstHeaderMenuItemsFactory;
	}

	public IProviderSelector<E> getSourceSelector() {
		return sourceSelector;
	}

	public void setSourceSelector(IProviderSelector<E> sourceSelector) {
		this.sourceSelector = sourceSelector;
	}

	public FirstColumnModel getFirstColumnModel() {
		return firstColumnModel;
	}

	public void setFirstColumnModel(FirstColumnModel firstColumnModel) {
		this.firstColumnModel = firstColumnModel;
	}
	
	public Component getUpdatableComponent() {
		return this;
	}

	/**
	 * @return the rendringCount
	 */
	public int getRendringCount() {
		return rendringCount;
	}

	/**
	 * @param rendringCount the rendringCount to set
	 */
	public void setRendringCount(int rendringCount) {
		this.rendringCount = rendringCount;
	}
	
	
}
