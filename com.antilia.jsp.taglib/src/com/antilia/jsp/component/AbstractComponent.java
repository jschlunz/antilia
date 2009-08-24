/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractComponent implements IComponent {
	
	private String id;
	
	
	private IComponent parent;
	
	
	private boolean outputMarkupId;
	
	private List<HeaderContributor> headerContributors = new ArrayList<HeaderContributor>();
	
	public AbstractComponent(String id) {
		this(id, null);
	}
	
	public AbstractComponent(String id, IComponent parent) {
		this.parent = null;
		this.id = id;
		RequestContext.get().bindComponent(this);
	}

	/* (non-Javadoc)
	 * @see com.antilia.jsp.component.IComponent#render(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void render(HttpServletRequest request, PrintWriter printWriter) {
		try {
			onRender(printWriter, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param writer
	 * @param request
	 * @throws Exception
	 */
	protected abstract void onRender(PrintWriter writer, HttpServletRequest request) throws Exception;

	protected void addHeaderContributor(HeaderContributor contributor) {
		headerContributors.add(contributor);
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the outputMarkupId
	 */
	public boolean isOutputMarkupId() {
		return outputMarkupId;
	}

	/**
	 * @param outputMarkupId the outputMarkupId to set
	 */
	public void setOutputMarkupId(boolean outputMarkupId) {
		this.outputMarkupId = outputMarkupId;
	}

	/**
	 * @return the headerContributors
	 */
	public List<HeaderContributor> getHeaderContributors() {
		return headerContributors;
	}

	/**
	 * @return the parent
	 */
	public IComponent getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(IComponent parent) {
		this.parent = parent;
	}


	

}
