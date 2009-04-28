/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class BooleanDropDownChoice extends DropDownChoice<Boolean> {

	private static final long serialVersionUID = 1L;
	
	private Boolean useAsYesNo = Boolean.FALSE;

	/**
	 * 
	 * @param id
	 * @param enumClass
	 * @param model
	 */
	public BooleanDropDownChoice(String id, IModel<Boolean> model) {
		super(id);
		
		setModel(model);
		setNullValid(true);
		setChoiceRenderer(new ChoiceRenderer<Boolean>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Boolean object) {
				if(object== null)
					super.getDisplayValue(object);
				String key= "BooleanDropDownChoice." + object.toString();
				if(useAsYesNo) {
					key="BooleanDropDownChoice.yesNo."+object.toString();;
				}
				return getString(key);
			}
		});		
		List<Boolean> choices = new ArrayList<Boolean>();
		choices.add(Boolean.TRUE); 
		choices.add(Boolean.FALSE);
		setChoices(choices);
	}

	/**
	 * @return the useAsYesNo
	 */
	public Boolean getUseAsYesNo() {
		return useAsYesNo;
	}

	/**
	 * @param useAsYesNo the useAsYesNo to set
	 */
	public void setUseAsYesNo(Boolean useAsYesNo) {
		this.useAsYesNo = useAsYesNo;
	}
}
