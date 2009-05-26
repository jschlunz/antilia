/**
 * 
 */
package com.antilia.web.tabs;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.crud.IFeedBackAware;
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
		ITab tab = getTabs().get(index);
		boolean submit = (tab instanceof IAntiliaTab && ((IAntiliaTab)tab).isSubmit());
		if(submit) {
			return new AjaxSubmitLink(linkId)
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
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
				protected void onError(AjaxRequestTarget target, Form<?> form) {
					AntiliaAjaxTabbedPanel.this.onError(target, form);
				}
				
				@Override
				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return new AntiliaAjaxCallDecorator(AntiliaAjaxTabbedPanel.this);
				}

			};
		}
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
	
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		IFeedBackAware feedBackAware = findParent(IFeedBackAware.class);
		if(feedBackAware != null) {
			target.addComponent((Component)(feedBackAware.getFeedback()));
		}
	}

	public IDialogScope findParentDialog() {
		return findParent(IDialogScope.class);
	}
	
	public Component getDefiningComponent() {
		return this;
	}
}
