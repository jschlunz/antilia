/**
 * 
 */
package com.antilia.web.tabs;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AntiliaAjaxTabbedPanel extends AjaxTabbedPanel implements IDialogFinder {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param tabs
	 */
	public AntiliaAjaxTabbedPanel(String id, List<ITab> tabs) {
		super(id, tabs);
	}
	
	@Override
	protected WebMarkupContainer newLink(String linkId, final int index)
	{
		return new AjaxFallbackLink<Void>(linkId)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				if(getSelectedTab() == index)
					return;
				setSelectedTab(index);
				if (target != null)
				{
					target.addComponent(AntiliaAjaxTabbedPanel.this);
				}
				onAjaxUpdate(target);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AntiliaAjaxCallDecorator(AntiliaAjaxTabbedPanel.this);
			}

		};
	}

	public IDialogScope findParentDialog() {
		return findParent(IDialogScope.class);
	}
	
	public Component getDefiningComponent() {
		return this;
	}
}
