package com.antilia.web.progress;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;

public class HtmlProgressBar extends Panel {

	private static final long serialVersionUID = 1L;

	private int width = 200;
	
	private int progress = 0;
	
	private boolean browserIExplorer;	
	
	public static final ResourceReference PROGRESS_CSS = new ResourceReference(HtmlProgressBar.class, "progress.css");
	
	private WebMarkupContainer progressBar;
	
	public HtmlProgressBar(String id) {
		this(id, 0);
	}
	
	public HtmlProgressBar(String id, int progress) {
		super(id);
		setOutputMarkupId(true);
		this.progress = progress;
		ClientProperties properties = ((WebClientInfo)getRequestCycle().getClientInfo()).getProperties();
		setBrowserIExplorer(properties.isBrowserInternetExplorer());
		
		add(HeaderContributor.forCss(newProgressCss()));		
		progressBar = new WebMarkupContainer("pBar");
		
		progressBar.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("position: relative;");
				sb.append("width:  ");
				sb.append(getWidth());
				sb.append("px;");
				return sb.toString();
			}
		}));
		
		add(progressBar);		
	}
	
	@Override
	protected void onBeforeRender() {
		WebMarkupContainer progressBarFill = new WebMarkupContainer("pBarFill");		
		progressBarFill.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("position: absolute;");
				sb.append("width:");
				sb.append(getProgress());
				sb.append("%;");
				if(!isBrowserIExplorer()) {
					sb.append("margin: 1px;");
				}
				return sb.toString();
			}
		}));
		progressBar.addOrReplace(progressBarFill);
		
		Label progressBarText = new Label("pBarText", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();				
				sb.append(getProgress());
				sb.append(" %");
				return sb.toString();
			}
		});
		progressBar.addOrReplace(progressBarText);
		
		super.onBeforeRender();
	}
	
	protected ResourceReference newProgressCss() {
		return PROGRESS_CSS;
	}

	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public boolean isBrowserIExplorer() {
		return browserIExplorer;
	}

	public void setBrowserIExplorer(boolean browserIExplorer) {
		this.browserIExplorer = browserIExplorer;
	}
}
