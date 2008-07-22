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
public class VerticalSplitPane extends Panel {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS_FILE = new ResourceReference(VerticalSplitPane.class,"split.css");
	private static final ResourceReference JS_FILE = new ResourceReference(VerticalSplitPane.class,"split.js");
	
	private static class Panes extends RefreshingView {
		
		private static final long serialVersionUID = 1L;
		
		private VerticalSplitPane splitPane;

		public Panes(String id, VerticalSplitPane splitPane) {
			super(id);
			this.splitPane = splitPane;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Iterator getItemModels() {
			List<Model> list = new ArrayList<Model>();
			list.add(new Model("test1"));
			list.add(new Model("test2"));
			list.add(new Model("test3"));
			return list.iterator();
		}

		@Override
		protected void populateItem(Item item) {
			WebMarkupContainer content = new WebMarkupContainer("content");
			if(item.getIndex() == 0)
				content.add(new AttributeModifier("class", new Model("vpaneCell1")));
			else 
				content.add(new AttributeModifier("class", new Model("vpaneCell")));
			//content.add(new Label("cell", item.getModel()));
			content.add(new HorizontalSplitPane("cell", item.getIndex()+1) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected Component newPane(String id, int pane) {
					return null;
				}
			});
			item.add(content);
			WebMarkupContainer dragger = new WebMarkupContainer("dragger");
			String id = getSplitPane().getMarkupId()+ "DV" + item.getIndex();
			dragger.add(new AttributeModifier("id", new Model(id)));								
			item.add(dragger);
			Label script = new Label("script", new Model("new VSplitPane('"+id+"');"));
			script.setEscapeModelStrings(false);
			item.add(script);
		}

		public VerticalSplitPane getSplitPane() {
			return splitPane;
		}

		public void setSplitPane(VerticalSplitPane splitPane) {
			this.splitPane = splitPane;
		}
	}
	
	
	/**
	 * @param id
	 */
	public VerticalSplitPane(String id) {
		super(id);
		add(HeaderContributor.forCss(CSS_FILE));
		add(HeaderContributor.forJavaScript(RoundPane.RBOX_JS));
		add(HeaderContributor.forJavaScript(JS_FILE));
		setOutputMarkupId(true);
	}
	
	@Override
	protected void onBeforeRender() {
		addOrReplace(new Panes("cells", this));
		super.onBeforeRender();
	}
}
