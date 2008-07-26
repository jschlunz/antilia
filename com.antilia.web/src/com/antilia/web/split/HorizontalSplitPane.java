package com.antilia.web.split;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.Model;

import com.antilia.web.roundpane.RoundPane;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern@isencia.com)
 */
public abstract class HorizontalSplitPane extends Panel {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS_FILE = new ResourceReference(HorizontalSplitPane.class,"split.css");
	private static final ResourceReference JS_FILE = new ResourceReference(HorizontalSplitPane.class,"split.js");
	
	private static class Panes extends RefreshingView {
		
		private static final long serialVersionUID = 1L;
		
		private HorizontalSplitPane splitPane;

		public Panes(String id, HorizontalSplitPane splitPane) {
			super(id);
			this.splitPane = splitPane;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Iterator getItemModels() {
			List<Model> list = new ArrayList<Model>();
			int panes = splitPane.getPanes();
			for(int i=0; i < panes; i++) {
				list.add(new Model("test" + i));
			}
			return list.iterator();
		}

		@Override
		protected void populateItem(Item item) {
			WebMarkupContainer content = new WebMarkupContainer("content");
			if(item.getIndex() == 0)
				content.add(new AttributeModifier("class", new Model("paneCell1")));
			else 
				content.add(new AttributeModifier("class", new Model("paneCell")));
			Component contentPane = getSplitPane().newPane("cell", item.getIndex());
			if(contentPane == null) {
				contentPane = new Label("cell", item.getModel());
			}
			content.add(contentPane);
			item.add(content);
			WebMarkupContainer dragger = new WebMarkupContainer("dragger");
			String id = getSplitPane().getMarkupId()+ "DH" + item.getIndex();
			dragger.add(new AttributeModifier("id", new Model(id)));								
			item.add(dragger);
			Label script = new Label("script", new Model("new HSplitPane('"+id+"');"));
			script.setEscapeModelStrings(false);
			item.add(script);
		}

		public HorizontalSplitPane getSplitPane() {
			return splitPane;
		}

		public void setSplitPane(HorizontalSplitPane splitPane) {
			this.splitPane = splitPane;
		}
	}
	
	
	/** The number of panes **/	
	private int panes;
	
	private int with = 400;
	
	private int height = 400;
	
	/**
	 * @param id
	 */
	public HorizontalSplitPane(String id, int panes) {
		super(id);
		this.panes = panes;
		add(HeaderContributor.forCss(CSS_FILE));
		add(HeaderContributor.forJavaScript(RoundPane.RBOX_JS));
		add(HeaderContributor.forJavaScript(JS_FILE));
		setOutputMarkupId(true);
	}
	
	/**
	 * Override this method to create a new panes.
	 * 
	 * @param id
	 * @param pane
	 * @return
	 */
	protected abstract Component newPane(String id, int pane);
	
	@Override
	protected void onBeforeRender() {
		addOrReplace(new Panes("cells", this));
		super.onBeforeRender();
	}

	public int getPanes() {
		return panes;
	}

	public void setPanes(int panes) {
		this.panes = panes;
	}

	public int getWith() {
		return with;
	}

	public void setWith(int with) {
		this.with = with;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
