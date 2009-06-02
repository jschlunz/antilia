/**
 * 
 */
package com.antilia.demo.manager.components.validation;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.feedback.AntiliaFeedBackPanel;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.validation.AnnotationsFormValidator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class TestValidatorsPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private AntiliaFeedBackPanel feedback;
	
	private TestBean testBean;
	/**
	 * 
	 */
	public TestValidatorsPanel(String id) {
		super(id);	
		testBean = new TestBean();
		
		Form<TestBean> form = new Form<TestBean>("form", new CompoundPropertyModel<TestBean>(testBean));
		add(form);
		form.add(new AnnotationsFormValidator<TestBean>(TestBean.class));
		
		TextField<String> name = new TextField<String>("name");
		//name.add(new AnnotationBasedValidator<TestBean, String>(TestBean.class, "name"));
		name.setLabel(new ResourceModel("name"));
		form.add(name);
		
		TextField<String> lastName = new TextField<String>("lastName");
		//lastName.add(new AnnotationBasedValidator<TestBean, String>(TestBean.class, "lastName"));
		lastName.setLabel(new ResourceModel("lastName"));
		form.add(lastName);
		
		TextField<Long> skillLevel = new TextField<Long>("skillLevel");
		//skillLevel.add(new AnnotationBasedValidator<TestBean, Long>(TestBean.class, "skillLevel"));
		skillLevel.setLabel(new ResourceModel("skillLevel"));
		form.add(skillLevel);
		
		AbstractButton submit = new AbstractButton("submit", true) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImage() {
				return DefaultStyle.IMG_OK;
			}
			
			@Override
			protected String getLabel() {
				return "Submit";
			}
			
			@Override
			protected String getLabelKey() {
				return null;
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				if(target != null) {
					target.addComponent(feedback);
				}
			}
		};
				
		form.add(submit);
		
		feedback = new AntiliaFeedBackPanel("feedback");
		add(feedback);
	}

}
