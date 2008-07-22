/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import com.antilia.common.util.StringUtils;
import com.antilia.web.dragdrop.DraggableTargetBehavior;
import com.antilia.web.dragdrop.IDraggableTarget;
import com.antilia.web.effect.JavascriptHelper;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HeaderTitleLabel<E extends Serializable> extends Label implements IDraggableTarget {

	private static final long serialVersionUID = 1L;
	
	private DefaultHeaderCell<E> headerCell;

	private DraggableTargetBehavior onDropBehavior;
	
	/**
	 * @param id
	 * @param model
	 */
	public HeaderTitleLabel(String id, final DefaultHeaderCell<E> headerCell)  {
		super(id, headerCell.getLabelModel());		
		this.headerCell = headerCell;
		
		setOutputMarkupId(true);
		add(new AttributeModifier("id", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return headerCell.getTable().getMarkupId()+"_title_"+headerCell.getColumn();
			}
		}));
		
		add(new AttributeModifier("class", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return headerCell.getTable().getMarkupId();
			}
		}));		
		headerCell.add(this);
		
		onDropBehavior = new DraggableTargetBehavior(this);
		onDropBehavior.setHoverClass("ondrop");
		add(onDropBehavior);
	}

	protected void onRender(MarkupStream markupStream)
	{
		super.onRender(markupStream);

		onDropBehavior.addAcceptClass(headerCell.getTable().getMarkupId());

		onDropBehavior.getDropOptions().put("onDrop", new JavascriptHelper.JavascriptFunction("function(draggable, droppable, event) { wicketAjaxGet('" + onDropBehavior.getCallbackUrl()
				+ "&id=' + draggable.id); }"));

		JavascriptHelper builder = new JavascriptHelper();
		builder.addLine("Droppables.add('" + headerCell.getTable().getMarkupId()+"_title_"+headerCell.getColumn() + "', ");
		builder.addOptions(onDropBehavior.getDropOptions());
		builder.addLine(");");

		getResponse().write(builder.buildScript());
	}	
	
	@Override
	public void accepts(Component component) {
		if(component instanceof HeaderTitleLabel) {
			System.out.println("Hi");
		}
	}
	
	@Override
	public void onDrop(String input, AjaxRequestTarget target) {
		System.out.println("onDrop: " +  input);
		int dropedColumn = getDropedColumnIndex(input)-1;
		int thisColumn = this.headerCell.getColumn()-1;
		if(dropedColumn == -2 || dropedColumn == thisColumn)
			return;
		if(target != null) {
			this.headerCell.getTable().getTableModel().swapColumns(thisColumn, dropedColumn);
			target.addComponent(this.headerCell.getTable());
		}
	}
	
	/**
	 * -1 means something is wrong and the table should be reloaded.
	 * @return
	 */
	private int getDropedColumnIndex(String input) {
		if(StringUtils.isEmpty(input))
			return -1;
		String tableId = headerCell.getTable().getMarkupId();
		if(input.startsWith(tableId)) {
			try {
				String columnId = input.substring(input.lastIndexOf('_')+1);
				return Integer.parseInt(columnId);
			} catch (Exception e) {
			}
		}
		return -1;
	}

	/**
	 * @return the headerCell
	 */
	public DefaultHeaderCell<E> getHeaderCell() {
		return headerCell;
	}

	/**
	 * @param headerCell the headerCell to set
	 */
	public void setHeaderCell(DefaultHeaderCell<E> headerCell) {
		this.headerCell = headerCell;
	}
}
