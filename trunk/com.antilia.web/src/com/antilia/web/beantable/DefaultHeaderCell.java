/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.wicketstuff.minis.veil.VeilResources;

import com.antilia.common.util.ResourceUtils;
import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.query.IOrder;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Order;
import com.antilia.hibernate.query.IOrder.OrderType;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.navigation.ColumnMenuItemsFactory;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.dragdrop.YuiDraggableTarget;
import com.antilia.web.menu.Menu;
import com.antilia.web.utils.RequestUtils;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultHeaderCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Table<E> table;

	private int column;
	
	private Class<E> beanClass;
	
	private IDialogScope dialogScope;
	
	/**
	 * @param id
	 * @param model
	 */
	public DefaultHeaderCell(String id, int column, Table<E> table, final IColumnModel<E> columnModel, Class<E> beanClass) {
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
			public String getMarkupId() {
				return getTable().getMarkupId()+"_dragger_"+ getTable().getRendringCount() + "_" +getColumn();
			}
			
			public void onDrop(String sourceId, String targetId, AjaxRequestTarget target) {				
				if(StringUtils.isEmpty(targetId)) 
					return;				
				if(targetId.indexOf("dropCol")>0)  {
					int dropedColumn = getDropedColumnIndex(sourceId)-1;
					if(dropedColumn == -2)
						return;
					if(target != null) {
						DefaultHeaderCell.this.getTable().getTableModel().hideColumn(dropedColumn);
						target.addComponent(DefaultHeaderCell.this.getTable());
					}
				} else if(targetId.indexOf("dropLas")>0)  {
					int dropedColumn = getDropedColumnIndex(sourceId)-1;
					if(dropedColumn == -2 || dropedColumn ==DefaultHeaderCell.this.getTable().getTableModel().getColumns()-1) {
						if(target != null) {
							target.addComponent(DefaultHeaderCell.this.getTable());
						}
						return;
					}
					if(target != null) {
						DefaultHeaderCell.this.getTable().getTableModel().moveColumnBefore(dropedColumn, DefaultHeaderCell.this.getTable().getTableModel().getColumns());
						target.addComponent(DefaultHeaderCell.this.getTable());
					}
				}  else {
					int dropedColumn = getDropedColumnIndex(sourceId)-1;
					int thisColumn = getDropedColumnIndex(targetId)-1;
					if(dropedColumn == -2 || dropedColumn == thisColumn)
						return;
					if(target != null) {
						//DefaultHeaderCell.this.getTable().getTableModel().swapColumns(thisColumn, dropedColumn);
						DefaultHeaderCell.this.getTable().getTableModel().moveColumnBefore(dropedColumn, thisColumn);
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
		
		draggableTarget.add(new AttributeModifier("style", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(RequestUtils.isBrowserIeExplorer6())
					return "border: none;";
				return "border: 1px solid transparent;";
			}
		}));

		draggableTarget.add(new AttributeModifier("id", new Model<String>() {
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
		
	
		
		if(columnModel.isSortable()) {			
			draggableTarget.add(new AjaxEventBehavior("ondblclick") {
				
				private static final long serialVersionUID = 1L;
	
				@Override
				protected void onEvent(AjaxRequestTarget target) {
					if(target != null) {
						IColumnModel<E> columnModel = DefaultHeaderCell.this.getColumnModel();
						if(!columnModel.isSortable()) {
							return;
						}
						IPageableComponent<E> component = getTable();
						IQuery<E> query = component.getPageableProvider().getQuery();				
						IOrder<E> order = query.getOrder(columnModel.getPropertyPath());
						if(order == null || order.getType().equals(OrderType.DESCENDING))
							order = Order.asc(columnModel.getPropertyPath());
						else 
							order = Order.des(columnModel.getPropertyPath());
						query.clearOrders();
						query.addOrder(order);
						component.getPageableProvider().reset();
						target.addComponent((Component)component);
					}
				}
				
				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return  new IAjaxCallDecorator() {
						
						private static final long serialVersionUID = 1L;

						public CharSequence decorateOnFailureScript(CharSequence script) {
							IDialogScope dialogScope = getDialogScope();
							if(dialogScope != null) {
								return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";" ;
							} 
							return script + ";" + VeilResources.Javascript.Generic.toggle("AT_body") + ";" ;
						}
						
						public CharSequence decorateOnSuccessScript(CharSequence script) {
							IDialogScope dialogScope = getDialogScope();
							if(dialogScope != null) {
								return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";" ;
							}
							return script + ";" + VeilResources.Javascript.Generic.toggle("AT_body") + ";" ;
						}
						
						public CharSequence decorateScript(CharSequence script) {
							IDialogScope dialogScope = getDialogScope();
							if(dialogScope != null) {
								return VeilResources.Javascript.Generic.show(dialogScope.getDialogId()) + ";" + script;
							} 
							return VeilResources.Javascript.Generic.toggle("AT_body") + ";" + script;									
						}
					};
				}
			});
		}
		
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
		dragTd.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(columnModel.isResizable() )
					return "resCol";
				return "noResCol";
			}
		}));
		
		//dragTd.add(new Image("dragImage",  DefaultStyle.IMG_RESIZE));
		add(dragTd);
		
	}
	
	@SuppressWarnings("unchecked")
	protected IModel getLabelModel() {
		String key = ResourceUtils.getPropertyResourceKey(getBeanClass(), getColumnModel().getPropertyPath());
		return new StringResourceModel(key, this, null, getColumnModel().getPropertyPath());
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
	
	public IDialogScope getDialogScope() {
		if(dialogScope == null)
			dialogScope = findParentDialog();
		return dialogScope;
	}
	
	private IDialogScope findParentDialog() {
		return (IDialogScope)findParent(IDialogScope.class);
	}
}
