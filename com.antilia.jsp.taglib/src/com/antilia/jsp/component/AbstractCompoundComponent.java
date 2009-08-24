/**
 * 
 */
package com.antilia.jsp.component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractCompoundComponent extends AbstractComponent implements ICompoundComponent {

	
	private Map<String, IComponent> components = new HashMap<String, IComponent>();
	/**
	 * @param id
	 */
	public AbstractCompoundComponent(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param parent
	 */
	public AbstractCompoundComponent(String id, IComponent parent) {
		super(id, parent);
	}


	/* (non-Javadoc)
	 * @see com.antilia.jsp.component.ICompoundComponent#getComponent(java.lang.String)
	 */
	public IComponent getComponent(String id) {
		return components.get(id);
	}

	public void addComponent(IComponent component) {
		components.put(component.getId(), component);
		component.setParent(this);
	}
	
	public void removeComponent(IComponent component) {
		components.remove(component.getId());
		component.setParent(null);
	}
	
	public void removeAllComponents() {
		components.clear();
	}
}
