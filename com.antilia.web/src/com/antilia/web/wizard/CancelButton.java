/*
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
package com.antilia.web.wizard;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.markup.html.form.Form;

/**
 * Models a cancel button in the wizard. When pressed, it calls {@link Wizard#onCancel()} which
 * should do the real work.
 * 
 * @author Eelco Hillenius
 */
public class CancelButton extends AjaxWizardButton
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param id
	 *            The component id
	 * @param wizard
	 *            The wizard
	 */
	public CancelButton(String id, IAjaxWizard wizard)
	{
		super(id, wizard, "org.apache.wicket.extensions.wizard.cancel");
		setDefaultFormProcessing(false);
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	@Override
	public final boolean isEnabled()
	{
		return true;
	}

	/**
	 * @see org.apache.wicket.Component#isVisible()
	 */
	@Override
	public final boolean isVisible()
	{
		return getWizardModel().isCancelVisible();
	}

	
	@Override
	protected void onClick(AjaxRequestTarget target, Form<?> form) {
		getWizardModel().cancel();	
	}
}