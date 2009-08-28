/**
 * 
 */
package com.antilia.jsp.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractCompoundComponent extends AbstractComponent implements ICompoundComponent {

	
	private List<IComponent> components = new ArrayList<IComponent>();
	
	/**
	 * @param id
	 */
	public AbstractCompoundComponent(String id) {
		super(id);
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
		for(IComponent component: components) {
			if(component.getId().equals(id))
				return component;
		}
		return null;
	}
	
	public Iterable<IComponent> getChildrem() {
		return components;
	}

	public void addComponent(IComponent component) {
		components.add(component);
		component.setParent(this);
	}
	
	public void removeComponent(IComponent component) {
		components.remove(component);
		component.setParent(null);
	}
	
	public void removeAllComponents() {
		components.clear();
	}
}
