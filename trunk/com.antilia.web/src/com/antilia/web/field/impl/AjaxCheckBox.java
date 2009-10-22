/** 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.antilia.web.field.impl;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.dialog.IVeilScope;

/**
 * A CheckBox which is updated via ajax when the user changes its value
 * 
 * @since 1.2
 * 
 * @author Igor Vaynberg (ivaynberg)
 */
public abstract class AjaxCheckBox extends CheckBox implements IDialogFinder 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param id
	 */
	public AjaxCheckBox(final String id)
	{
		this(id, null);
	}

	/**
	 * Construct.
	 * 
	 * @param id
	 * @param model
	 */
	public AjaxCheckBox(final String id, final IModel<Boolean> model)
	{
		super(id, model);

		setOutputMarkupId(true);

		add(new AjaxFormComponentUpdatingBehavior("onclick")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				AjaxCheckBox.this.onUpdate(target);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AntiliaAjaxCallDecorator(AjaxCheckBox.this);
			}
		});
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
	
	/**
	 * Listener method invoked on an ajax update call
	 * 
	 * @param target
	 */
	protected abstract void onUpdate(AjaxRequestTarget target);
}
