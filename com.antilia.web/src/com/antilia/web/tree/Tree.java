/**
 * 
 */
package com.antilia.web.tree;

import javax.swing.tree.TreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.dialog.IVeilScope;

/**
 * A version of the tree that will display the loading icon.
 * 
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class Tree extends org.apache.wicket.extensions.markup.html.tree.Tree implements IDialogFinder{

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public Tree(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param model
	 */
	public Tree(String id, IModel<TreeModel> model) {
		super(id, model);
	}

	/**
	 * @param id
	 * @param model
	 */
	public Tree(String id, TreeModel model) {
		super(id, model);
	}

	@Override
	protected MarkupContainer newLink(MarkupContainer parent, String id,
			final ILinkCallback callback)
		{
			if (getLinkType() == LinkType.REGULAR)
			{
				return new Link<Void>(id)
				{
					private static final long serialVersionUID = 1L;

					/**
					 * @see org.apache.wicket.markup.html.link.Link#onClick()
					 */
					@Override
					public void onClick()
					{
						callback.onClick(null);
					}
				};
			}
			else if (getLinkType() == LinkType.AJAX)
			{
				return new AjaxLink<Void>(id)
				{
					private static final long serialVersionUID = 1L;

					/**
					 * @see org.apache.wicket.ajax.markup.html.AjaxLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
					 */
					@Override
					public void onClick(AjaxRequestTarget target)
					{
						callback.onClick(target);
					}
					
					@Override
					protected IAjaxCallDecorator getAjaxCallDecorator() {
						return new AntiliaAjaxCallDecorator(Tree.this);
					}
				};
			}
			else
			{
				return new AjaxFallbackLink<Void>(id)
				{
					private static final long serialVersionUID = 1L;

					/**
					 * @see org.apache.wicket.ajax.markup.html.AjaxFallbackLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
					 */
					@Override
					public void onClick(AjaxRequestTarget target)
					{
						callback.onClick(target);
					}
					
					@Override
					protected IAjaxCallDecorator getAjaxCallDecorator() {
						return new AntiliaAjaxCallDecorator(Tree.this);
					}
				};
			}
		}

	
	public IDialogScope findParentDialog() {
		return findParent(IDialogScope.class);
	}
	
	public IVeilScope findVeilScope() {
		return findParent(IVeilScope.class);
	}

	public Component getDefiningComponent() {
		return this;
	}

}
