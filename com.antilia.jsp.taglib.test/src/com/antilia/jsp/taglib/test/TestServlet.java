/**
 * 
 */
package com.antilia.jsp.taglib.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.antilia.common.query.Query;
import com.antilia.common.util.FileUtils;
import com.antilia.common.util.StringUtils;
import com.antilia.jsp.component.HeaderContributor;
import com.antilia.jsp.component.IActionListener;
import com.antilia.jsp.component.IComponent;
import com.antilia.jsp.component.ILinkListener;
import com.antilia.jsp.component.IUrlGenerator;
import com.antilia.jsp.component.RequestContext;
import com.antilia.jsp.component.table.TableComponent;
import com.antilia.jsp.taglib.beans.Persona;
import com.antilia.jsp.taglib.dao.MockClientesDao;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.navigator.impl.DataProviderPageableNavigator;
import com.antilia.web.provider.impl.ListQuerableUpdatebleDataProvider;
import com.antilia.web.resources.images.ImgDummy;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static class TestUrlGenerator implements IUrlGenerator {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String generateUrlFor(IActionListener listener) {
			if(listener instanceof IComponent) {
				StringBuffer sb = new StringBuffer();
				sb.append(RequestContext.get().getRequest().getServletPath());
				sb.append("/");
				sb.append(generateComponentUrl((IComponent)listener));
				return sb.toString();
			}
			return null;
		}
		
		private String generateComponentUrl(IComponent component) {
			StringBuffer sb = new StringBuffer();
			IComponent temp = component;
			while(temp != null) {				
				sb.insert(0,temp.getId());
				sb.insert(0, ":");
				temp = temp.getParent();
										
			}
			sb.insert(0, IActionListener.IDENTIFIER);
			return sb.toString();
		}
	}
	
	/**
	 * 
	 */
	public TestServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {					
		
		try {
			RequestContext.get().setRequest(request);
			RequestContext.get().setResponse(response);
			RequestContext.get().setUrlGenerator(new TestUrlGenerator());
			RequestContext.get().setAjax(false);
			
			if(serviceReosurces(request, response))
				return;
			
			if(serviceEvents(request, response))
				return;
			
			TableModel<Persona> tableModel = new TableModel<Persona>(Persona.class, "nombre", "apellidos");
			Query<Persona> query = new Query<Persona>(Persona.class);
			DataProviderPageableNavigator<Persona> navigator = new DataProviderPageableNavigator<Persona>(new ListQuerableUpdatebleDataProvider<Persona>(MockClientesDao.getInstance()), query);
			navigator.setPageSize(5);
			TableComponent<Persona> tableComponent = new TableComponent<Persona>("personas", tableModel, navigator);
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			out.println("<html>");
			out.println("<head>");
			for(HeaderContributor headerContributor :tableComponent.getHeaderContributors()) {
				headerContributor.renderHead(out);
			}
			out.println("</head>");
			out.println("<body>");		
			out.flush();
			tableComponent.render(request, out);
			out.println("<div>And another table!</div>");			
			out.flush();
			TableComponent<Persona> tableComponent2 = new TableComponent<Persona>("personas2", tableModel, navigator);
			tableComponent2.render(request, out);
			out.println("</body>");
			out.println("</html>");
			out.flush();
			out.close();
		} finally {
			RequestContext.unget();
		}
	}
	
	private boolean serviceReosurces(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String pathInfo = request.getRequestURI();
		if(pathInfo.contains("/"+HeaderContributor.RESOURCES_URI)) {			
			StringTokenizer st = new StringTokenizer(pathInfo, "/");
			String className =  null;			
			while(st.hasMoreTokens()) {
				className = st.nextToken();
			}
			if(!StringUtils.isEmpty(className)) {				
				try {
					if(pathInfo.contains("/images/")) { 
						response.getOutputStream().write(FileUtils.bytes(ImgDummy.class.getResourceAsStream(className)));
						response.getOutputStream().flush();
						response.getOutputStream().close();
						return true;
					} 
					Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
					String name = request.getParameter("res");
					//System.out.println(name);
					response.getOutputStream().write(FileUtils.bytes(clazz.getResourceAsStream(name)));
					response.getOutputStream().flush();
					response.getOutputStream().close();
					return true;
				} catch (Exception e) {
					throw new ServletException(e);
				}				
			}			
		}
		
		return false;
	}
	
	private boolean serviceEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String pathInfo = request.getRequestURI();		
		if(pathInfo.contains("/"+IActionListener.IDENTIFIER)) {			
			StringTokenizer st = new StringTokenizer(pathInfo, "/");
			String listenerName =  null;			
			String test = request.getHeader("X-Requested-With"); 
			if(test != null) {
				RequestContext.get().setAjax(true);
			}
			while(st.hasMoreTokens()) {
				listenerName = st.nextToken();
			}
			
			if(!StringUtils.isEmpty(listenerName)) {				
				try {
					IComponent component = RequestContext.get().findComponent(listenerName);
					if(component != null && component instanceof ILinkListener) {
						ILinkListener linkListener = (ILinkListener)component;						
						linkListener.onLinkClicked(request);					
					}				
					if(component != null && RequestContext.get().isAjax()) {
						IComponent ajax = RequestContext.get().getAjaxTarget();
						PrintWriter writer = response.getWriter();
						if(ajax != null) {
							response.setContentType("text/xml");
							writer.println("<?xml version=\"1.0\"?>");
							writer.print("<ajax id=\"");
							writer.print(ajax.getMarkupId());
							writer.print("\"");
							writer.println(">");
							writer.println("<![CDATA[");
							ajax.render(request, writer);
							//writer.println("<p>Hi!</p>");
							writer.println("]]>");
							writer.append("</ajax>");							
							writer.flush();
							writer.close();
							return true;
						} else {
							response.setContentType("text/xml");
							writer.println("<?xml version=\"1.0\"?>");
							writer.print("<ajax id=\"");
							writer.print("_NOTHING_TO_TO");
							writer.print("\"");
							writer.println(">");
							writer.append("</ajax>");
							writer.flush();
							writer.close();
							return true;
						}
					}
				} catch (Exception e) {
					throw new ServletException(e);
				}				
			}			
		}
		return false;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
}
