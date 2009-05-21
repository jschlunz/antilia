/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.model.FirstColumnModel;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.beantable.provider.SelectionMode;
import com.antilia.web.beantable.provider.impl.InMemoryPageableProvider;
import com.antilia.web.beantable.provider.impl.SourceSelector;
import com.antilia.web.button.AjaxRefreshableMenuItem;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.menu.IMenuItemsAuthorizer;
import com.antilia.web.osgi.MenuFactoryService;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.resources.ResourceLocator;
import com.antilia.web.utils.RequestUtils;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Table<E extends Serializable> extends Panel implements IPageableComponent<E>, IPageableNavigationListener {

	private static final long serialVersionUID = 1L;

	private IPageableProvider<E> pageableProvider; 
	
	private IProviderSelector<E> sourceSelector;
	
	private ITableModel<E> tableModel;	
	
	/**
	 * Component used to display navigation errors
	 */
	private IFeedback feedback;	
			
	private FirstColumnModel firstColumnModel;
	
	private boolean ie6;
	
	private MenuItemsFactory beforeNavigationMenuItemsFactory = new MenuItemsFactory(BEFORE_NAVIGATION_MENU);
	
	private MenuItemsFactory afterNavigationMenuItemsFactory = new MenuItemsFactory(AFTER_NAVIGATION_MENU);
	
	private MenuItemsFactory firstHeaderMenuItemsFactory = new MenuItemsFactory(FIRST_HEADER_MENU);
	
	private List<WebMarkupContainer> rowCheckboxes = new ArrayList<WebMarkupContainer>();
	
	private List<String> draggerURL= new ArrayList<String>();
	
	private String firstColumnUrl;
	
	public static final String AFTER_NAVIGATION_MENU = "AFTER_NAVIGATION_MENU";
	
	public static final String BEFORE_NAVIGATION_MENU = "BEFORE_NAVIGATION_MENU";
	
	public static final String FIRST_HEADER_MENU = "FIRST_HEADER_MENU";
	
	private List<IPageableNavigationListener> navigationListeners = new ArrayList<IPageableNavigationListener>();
	
	private static final String TABLE_CSS_ID = "ANT_TABLE_CSS_ID";
	
	/**
	 * Flag to set first column re-sizable or not.
	 */
	private boolean firstColumnResizable = true;
	
	/**
	 * Flag to set all columns re-sizable or not.
	 */
	private boolean columnsResizable = true;
	
	/**
	 * This variable is needed t fix a problem with drag and drop not working for IE
	 */
	private int rendringCount = 0;
	
	/**
	 * Authorizer for top menu.
	 */
	private IMenuItemsAuthorizer topMenuAuthorizer;
	
	/**
	 * True to keep selection when navigating (the default). False to clear it.
	 */
	private boolean keepSelectionOnNavigation = true;
	
	/**
	 * Constructor accepting a List.
	 * 
	 * @param id
	 * @param tableModel
	 * @param elements
	 */
	public Table(String id, ITableModel<E> tableModel, Collection<E> elements) {
		this(id, tableModel, new InMemoryPageableProvider<E>(elements, tableModel.getBeanClass()));
	}
	
	/**
	 * Constructor accepting oageable source.
	 * 
	 * @param id
	 */
	public Table(String id, ITableModel<E> tableModel, IPageableProvider<E> pageablePrivider)  {
		super(id, new Model<IPageableProvider<E>>(pageablePrivider));	
		setOutputMarkupId(true);		
		this.pageableProvider = pageablePrivider;
		this.sourceSelector = new SourceSelector<E>(this.pageableProvider ,tableModel.getSelectionMode());
		this.tableModel = tableModel;
		this.firstColumnModel = new FirstColumnModel(65);
		this.ie6 = RequestUtils.isBrowserIeExplorer6();
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_PROTOTYPE));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_EFFECT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_DRAGDROP));
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_MAIN));
		add(CSSPackageResource.getHeaderContribution(getTableCSS()));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_MIN));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_ANIMATION));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DRAG_DROP));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_COMMON));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_TABLE));
		
		addMenuItemsAfterNavidation(getAfterNavigationMenuItemsFactory());
		
		configureFirstHeaderMenuItemsFactory(tableModel);
		
		addMenuItemsBeforeNavigation(getBeforeNavigationMenuItemsFactory());
		
	}
	
	protected void configureFirstHeaderMenuItemsFactory(ITableModel<E> tableModel) {
		if(tableModel.getSelectionMode().equals(SelectionMode.MULTIPLE)) {
			getFirstHeaderMenuItemsFactory().addItem(
				AjaxRefreshableMenuItem.createRefreshableMenuItem(
						ToggleSelectAllButton.ID, 
						new ToggleSelectAllButton<E>(
								AjaxRefreshableMenuItem.getItemId(), this)));
			getFirstHeaderMenuItemsFactory().addItem(				
						new RevertSelectionButton<E>(RevertSelectionButton.ID, this));		
		} 
		addFirstHeaderMenuItems(getFirstHeaderMenuItemsFactory());
	}	
	
	@Override
	protected void onBeforeRender() {	
		
		rendringCount++;
		
		draggerURL.clear();
		
		addOrReplace(newTableHeader("header"));

		// add table header
		addOrReplace(getHeaderRows("hcols"));
		
		
	
		WebMarkupContainer lastHeader = new WebMarkupContainer("lastHeader");
		lastHeader.setOutputMarkupId(true); 
		lastHeader.add(new AttributeModifier("id",  new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return  Table.this.getMarkupId() + "_dropLas";
			}
		}));
		
		lastHeader.add(new AttributeModifier("style",  new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(Table.this.ie6)
					return  "min-width: 30px; height: 22px;";					
				return  "min-width: 30px; height: 22px; border: 1px solid transparent;";
			}
		}));
		
		
		
		addOrReplace(lastHeader);
		
		// add table body
		addOrReplace(getBodyRows("rows"));
		
		Label script = new Label("script", new Model<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				String tableId =  Table.this.getMarkupId()!=null?Table.this.getMarkupId():"table";
				StringBuffer sb = new StringBuffer();
				sb.append("var ");
				sb.append(tableId);
				sb.append(" = new Table('" + tableId + "','");
				sb.append(getFirstColumnUrl()+ "',");
				sb.append("new Array(");
				IPageableProvider<E> source = Table.this.getPageableProvider();
				IProviderSelector<E> selector = Table.this.getSourceSelector();
				Iterator<IModel<E>> it = source.getCurrentPage();
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
				sb.append("," + Table.this.isIe6());
				sb.append(");");
				sb.append(tableId+".");
				sb.append("createDraggables();");								
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		addOrReplace(script);
		
		super.onBeforeRender();
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
		ResourceReference global = ResourceLocator.getInstance().getResource(TABLE_CSS_ID);
		if(global != null) {
			return global;
		}
		return DefaultStyle.CSS_TABLE;
	}
	
	protected RefreshingView<E> getHeaderRows(String id) {
		return new HeaderRows("hcols", this);
	}
	
	protected RefreshingView<E> getBodyRows(String id) {
		return new BodyRows(id, this);
	}
	
	
	private class HeaderRows extends RefreshingView<E> {

		private static final long serialVersionUID = 1L;
		
		private Table<E> table;
		
		public HeaderRows(String id, Table<E> table) {
			super(id);
			this.table = table;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Iterator<IModel<E>> getItemModels() {
			ITableModel tableModel = getTable().getTableModel();		
			List<IModel<E>> models = new ArrayList<IModel<E>>();
			models.add(new Model(""));
			Iterator<IModel> it =  tableModel.getColumnModels();
			while(it.hasNext()) {
				IModel model = it.next();
				models.add(model);
			}
			return models.iterator();
		}
		
		@Override
		protected Item<E> newItem(String id, int index, final IModel<E> model) {
			Item<E> item = super.newItem(id, index, model);
			if(model instanceof IColumnModel) {			
				item.add(new AttributeModifier("width", true, new Model<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
						String width = ((IColumnModel<E>)model).getWidth()+"px";
						return width;
					}
					
				}));
			} else {
				item.add(new AttributeModifier("width", new Model<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
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
	
	/**
	 * Called when a row is clicked.
	 * @param target
	 * @param row
	 */
	protected void onRowClickedEvent(AjaxRequestTarget target, int row, E bean, boolean selected) {
		
	}
	
	protected void addMenuItemsBeforeNavigation(MenuItemsFactory factory) {
		
	}
	
	protected void addMenuItemsAfterNavidation(MenuItemsFactory factory) {
		MenuFactoryService.getInstance().populateFactory(AFTER_NAVIGATION_MENU, factory);
	}

	protected void addFirstHeaderMenuItems(MenuItemsFactory factory) {
		
	}
	
	private class BodyRows extends RefreshingView<E> {

		private static final long serialVersionUID = 1L;
	
		private List<IModel<E>> models;
		
		private Table<E> table;
		
		public BodyRows(String id, Table<E> table) {
			super(id);
			this.table = table;			
		}
		
		@SuppressWarnings("unchecked")
		private void initialize() {
			Table.this.clearRowCheckBoxes();
			models = new ArrayList<IModel<E>>();
			ITableModel tableModel = getTable().getTableModel();
			if(tableModel == null)
				return;	
			Iterator<IModel<E>> it = getTable().getPageableProvider().getCurrentPage();
			while(it.hasNext()) {
				IModel<E> object = it.next();
				models.add(tableModel.newModel(object));
			}
		}

		@Override
		protected Item<E> newItem(String id, int index, IModel<E> model) {
			return new RowItem<E>(id, index, (IComponentInheritedModel<E>)model, table);
		}
		
		@Override
		protected Iterator<IModel<E>> getItemModels() {
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
		return new DefaultHeaderCell<E>(id, index, table, columnModel, beanClass);
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

	public String getFirstColumnUrl() {
		return firstColumnUrl;
	}

	public void setFirstColumnUrl(String firstColumnUrl) {
		this.firstColumnUrl = firstColumnUrl;
	}

	/**
	 * @return the ie6
	 */
	public boolean isIe6() {
		return ie6;
	}

	/**
	 * @param ie6 the ie6 to set
	 */
	public void setIe6(boolean ie6) {
		this.ie6 = ie6;
	}

	/**
	 * @return the feedback
	 */
	public IFeedback getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(IFeedback feedback) {
		this.feedback = feedback;
	}

	public boolean isFirstColumnResizable() {
		return firstColumnResizable;
	}

	public void setFirstColumnResizable(boolean firstColumnResizable) {
		this.firstColumnResizable = firstColumnResizable;
	}

	public boolean isColumnsResizable() {
		return columnsResizable;
	}

	public void setColumnsResizable(boolean columnsResizable) {
		this.columnsResizable = columnsResizable;
	}
	
	public void addNavigationListener(IPageableNavigationListener listener) {
		navigationListeners.add(listener);
	}
	
	public void removeNavigationListener(IPageableNavigationListener listener) {
		navigationListeners.remove(listener);
	}

	public final void onFirstPage(AjaxRequestTarget target) {
		firstPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onFirstPage(target);
		}
	}
	
	/**
	 * Override this method to do something when first page button is clicked.
	 * 
	 * @param target
	 */
	public void firstPage(AjaxRequestTarget target) {
		
	}


	public void onLastPage(AjaxRequestTarget target) {
		lastPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onLastPage(target);
		}
	}
	
	/**
	 * Override this method to do something when last page button is clicked.
	 * 
	 * @param target
	 */
	public void lastPage(AjaxRequestTarget target) {
		
	}

	public void onNextPage(AjaxRequestTarget target) {
		nextPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onNextPage(target);
		}
	}
	
	/**
	 * Override this method to do something when next page button is clicked.
	 * 
	 * @param target
	 */
	public void nextPage(AjaxRequestTarget target) {
		
	}

	public void onPreviousPage(AjaxRequestTarget target) {
		previousPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onPreviousPage(target);
		}
	}
	
	/**
	 * Override this method to do something when previous page button is clicked.
	 * 
	 * @param target
	 */
	public void previousPage(AjaxRequestTarget target) {
		
	}

	/**
	 * @return the topMenuAuthorizer
	 */
	public IMenuItemsAuthorizer getTopMenuAuthorizer() {
		return topMenuAuthorizer;
	}

	/**
	 * @param topMenuAuthorizer the topMenuAuthorizer to set
	 */
	public void setTopMenuAuthorizer(IMenuItemsAuthorizer topMenuAuthorizer) {
		this.topMenuAuthorizer = topMenuAuthorizer;
	}

	/**
	 * @return the keepSelectionOnNavigation
	 */
	public boolean isKeepSelectionOnNavigation() {
		return keepSelectionOnNavigation;
	}

	/**
	 * @param keepSelectionOnNavigation the keepSelectionOnNavigation to set
	 */
	public void setKeepSelectionOnNavigation(boolean keepSelectionOnNavigation) {
		this.keepSelectionOnNavigation = keepSelectionOnNavigation;
	}
}
