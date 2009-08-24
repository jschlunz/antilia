/**
 * 
 */
package com.antilia.jsp.taglib.test;

import java.awt.event.ActionListener;
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
import com.antilia.jsp.component.IUrlGenerator;
import com.antilia.jsp.component.RequestContext;
import com.antilia.jsp.component.TableComponent;
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
				sb.insert(0, ":");
				sb.insert(0,temp.getId());
				temp = temp.getParent();
										
			}
			sb.insert(0, IActionListener.IDENTIFIER+":");
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
			
			if(serviceReosurces(request, response))
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
			out.println("<html>");
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
}
