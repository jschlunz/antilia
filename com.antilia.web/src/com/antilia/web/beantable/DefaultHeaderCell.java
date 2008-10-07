/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;
import com.antilia.common.util.StringUtils;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.navigation.ColumnMenuItemsFactory;
import com.antilia.web.dragdrop.YuiDraggableTarget;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultHeaderCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Table<E> table;

	private int column;
	
	private Class<E> beanClass;
	
	/**
	 * @param id
	 * @param model
	 */
	public DefaultHeaderCell(String id, int column, Table<E> table, IColumnModel<E> columnModel, Class<E> beanClass) {
		super(id, columnModel);
		this.table = table;
		this.column = column;
		this.beanClass  = beanClass;
		setRenderBodyOnly(true);
		add(new HiddenField<Integer>("colWidth", new Model<Integer>() {
	
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				return DefaultHeaderCell.this.getColumnModel().getWidth();
			}
			
			@Override
			public void setObject(Integer object) {
				if(object instanceof Integer) {
					DefaultHeaderCell.this.getColumnModel().setWidth(((Integer)object).intValue());
				}
			}
			
			
		}, Integer.class));
		
		YuiDraggableTarget draggableTarget =  new YuiDraggableTarget("dragger") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onDrop(String sourceId, String targetId, AjaxRequestTarget target) {
				if(StringUtils.isEmpty(targetId)) 
					return;
				if(targetId.indexOf("drop")>0)  {
					int dropedColumn = getDropedColumnIndex(sourceId)-1;
					if(dropedColumn == -2)
						return;
					if(target != null) {
						DefaultHeaderCell.this.getTable().getTableModel().hideColumn(dropedColumn);
						target.addComponent(DefaultHeaderCell.this.getTable());
					}
				} else {
					int dropedColumn = getDropedColumnIndex(sourceId)-1;
					int thisColumn = getDropedColumnIndex(targetId)-1;
					if(dropedColumn == -2 || dropedColumn == thisColumn)
						return;
					if(target != null) {
						DefaultHeaderCell.this.getTable().getTableModel().swapColumns(thisColumn, dropedColumn);
						target.addComponent(DefaultHeaderCell.this.getTable());
					}
				}
			}
			
			/**
			 * -1 means something is wrong and the table should be reloaded.
			 * @return
			 */
			private int getDropedColumnIndex(String input) {
				if(StringUtils.isEmpty(input))
					return -1;
				String tableId = DefaultHeaderCell.this.getTable().getMarkupId();
				if(input.startsWith(tableId)) {
					try {
						String columnId = input.substring(input.lastIndexOf('_')+1);
						return Integer.parseInt(columnId);
					} catch (Exception e) {
					}
				}
				return -1;
			}
			
			@Override
			protected void renderOnDrag(MarkupStream markupStream) {
				getTable().addDraggerUrl(onDropBehavior.getCallbackUrl().toString());
			}
						
		};
		
		draggableTarget .add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getTable().getMarkupId()+"_dragger_"+ getTable().getRendringCount() + "_" +getColumn();
			}
		}));
		
		draggableTarget .add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getTable().getMarkupId();
			}
		}));
		
		draggableTarget .setOutputMarkupId(true);
		
		add(draggableTarget);
		
		Menu menu = Menu.createMenu("menu",new ColumnMenuItemsFactory<E>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public IColumnModel<E> getColumnModel() {
				return DefaultHeaderCell.this.getColumnModel();
			}
		});
		menu.setMenuStyle("width: auto; height: 16px; background: transparent; right: 0px;  float: right;");
		menu.setRenderBodyOnly(true);
		menu.setOutputMarkupId(false);
		draggableTarget.add(menu);		
		
		//HeaderTitleLabel<E> title = new HeaderTitleLabel<E>("title", this);
		
		Label title = new Label("title", DefaultHeaderCell.this.getLabelModel());
		draggableTarget.add(title);
		
		/*
		Label title = new Label("title",DefaultHeaderCell.this.getLabelModel());
		title.setOutputMarkupId(true);
		title.add(new AttributeModifier("id", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DefaultHeaderCell.this.getTable().getMarkupId()+"_title_"+DefaultHeaderCell.this.getColumn();
			}
		}));
		add(title);
		*/
		WebMarkupContainer dragTd = new WebMarkupContainer("dragTd");
		dragTd.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return DefaultHeaderCell.this.getTable().getMarkupId()+"_c_"+DefaultHeaderCell.this.getColumn();
			}
		}));	
		//dragTd.add(new Image("dragImage",  DefaultStyle.IMG_RESIZE));
		add(dragTd);
	}
	
	@SuppressWarnings("unchecked")
	protected IModel getLabelModel() {
		String key = ResourceUtils.getPropertyResourceKey(getBeanClass(), getColumnModel().getPropertyPath());
		return new StringResourceModel(key, this, null, "Not found");
	}
	
	@SuppressWarnings("unchecked")
	protected IColumnModel<E> getColumnModel() {
		return (IColumnModel<E>)getDefaultModel();
	}
	
	public Table<E> getTable() {
		return table;
	}
	public void setTable(Table<E> table) {
		this.table = table;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}

	public Class<E> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<E> beanClass) {
		this.beanClass = beanClass;
	}
}
