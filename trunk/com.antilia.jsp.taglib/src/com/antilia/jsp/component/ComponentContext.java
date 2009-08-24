/**
 * 
 */
package com.antilia.jsp.component;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ComponentContext {
	
	private Map<String, IComponent> components = new HashMap<String, IComponent>();
	
	private static final String ANT_ID = "_ANT_COMPO_CONTEXT";
	
	private  ComponentContext() {		
	}
	
	
	
	public void clear() {
		components.clear();
	}
	
	
	public IComponent find(String fullPath) {
		int index = fullPath.indexOf(":");
		if(index<-1) {
			return components.get(fullPath);
		}
		StringTokenizer st = new StringTokenizer(fullPath, ":");
		String id = st.nextToken();
		IComponent component = components.get(id);
		return navigatePath(component, st);
	}
	
	
	public void add(IComponent component) {
		if(components.get(component.getId())== null)		
			components.put(component.getId(), component);
	}
	
	
	private IComponent navigatePath(IComponent component, StringTokenizer st) {
		if(st.hasMoreTokens()) {
			if(component instanceof ICompoundComponent) {
				ICompoundComponent compoundComponent = (ICompoundComponent)component;
				IComponent component2 = compoundComponent.getComponent(st.nextToken());
				return navigatePath(component2, st);
			}
			return component;
		} else {
			return component;
		}
		
	}
	
	public static ComponentContext bind(HttpSession session) {
		if(session.getAttribute(ANT_ID) != null)
			return (ComponentContext)session.getAttribute(ANT_ID);
		ComponentContext context = new ComponentContext();
		session.setAttribute(ANT_ID, context);
		return context;
	}
	
	public static void unbind(HttpSession session) {
		if(session.getAttribute(ANT_ID) != null)
			session.removeAttribute(ANT_ID);
	}
}
