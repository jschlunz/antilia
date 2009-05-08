/**
 * 
 */
package com.antilia.web.feedback;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.web.resources.DefaultStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AntiliaFeedBackPanel extends Panel implements IFeedback {

	private static final long serialVersionUID = 1L;
	
	private IFeedbackMessageFilter filter;
	
	private WebMarkupContainer feedBackContainer;
	
	private static final ResourceReference CSS = new ResourceReference(AntiliaFeedBackPanel.class, "feedBack.css");
	
	/**
	 * List for messages.
	 */
	private final class MessageListView extends ListView<FeedbackMessage>
	{
		private static final long serialVersionUID = 1L;

		/**
		 * @see org.apache.wicket.Component#Component(String)
		 */
		public MessageListView(final String id)
		{
			super(id);
			setDefaultModel(newFeedbackMessagesModel());
		}

		/**
		 * @see org.apache.wicket.markup.html.list.ListView#populateItem(org.apache.wicket.markup.html
		 *      .list.ListItem)
		 */
		@Override
		protected void populateItem(final ListItem<FeedbackMessage> listItem)
		{
			final FeedbackMessage message = listItem.getModelObject();
			message.markRendered();
			final IModel<String> replacementModel = new Model<String>()
			{
				private static final long serialVersionUID = 1L;

				
				@Override
				public String getObject()
				{
					return getCSSClass(message);
				}
			};
			
			Image errorIcon = new Image("errorIcon", DefaultStyle.IMG_ERROR_WARNING);		
			listItem.add(errorIcon);
						
			final Component label = newMessageDisplayComponent("errorMessage", message);
			final AttributeModifier levelModifier = new AttributeModifier("class", replacementModel);
			label.add(levelModifier);
			listItem.add(levelModifier);
			listItem.add(label);
		}
	}
	
	private MessageListView messageListView;
	
	/**
	 * @param id
	 */
	public AntiliaFeedBackPanel(String id) {
		this(id, null);
	}

	/**
	 * @param id
	 * @param model
	 */
	public AntiliaFeedBackPanel(String id, IFeedbackMessageFilter filter) {
		super(id);
		add(HeaderContributor.forCss(CSS));								
		setOutputMarkupId(true);		
		this.filter = filter;		
		feedBackContainer = new WebMarkupContainer("feedBackContainer") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return anyMessage();
			}
		};	
		
		add(feedBackContainer);
		
		AjaxLink<Void> closeDialog = newCloseDialogLink("closeDialog");
		
		closeDialog.add(newClosePanelIcon("icon"));		
		feedBackContainer.add(closeDialog);
		
		messageListView = new MessageListView("messages");
		
		feedBackContainer.add(messageListView);
	}
	
	protected AjaxLink<Void> newCloseDialogLink(String id) {
		return new AjaxLink<Void>(id) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				AntiliaFeedBackPanel.this.onClick(target);
			}
		};
	}
	
	protected Image newClosePanelIcon(String id) {		
		return new Image(id, DefaultStyle.IMG_CLOSE);
	}
	
	public void onClick(AjaxRequestTarget target) {
		if(target != null) {
			target.addComponent(this);
		}
	}

	/**
	 * @return the filter
	 */
	public IFeedbackMessageFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(IFeedbackMessageFilter filter) {
		this.filter = filter;
	}
	
	/**
	 * Search messages that this panel will render, and see if there is any message of level ERROR
	 * or up. This is a convenience method; same as calling 'anyMessage(FeedbackMessage.ERROR)'.
	 * 
	 * @return whether there is any message for this panel of level ERROR or up
	 */
	public final boolean anyErrorMessage()
	{
		return anyMessage(FeedbackMessage.ERROR);
	}

	/**
	 * Search messages that this panel will render, and see if there is any message.
	 * 
	 * @return whether there is any message for this panel
	 */
	public final boolean anyMessage()
	{
		return anyMessage(FeedbackMessage.UNDEFINED);
	}

	/**
	 * Search messages that this panel will render, and see if there is any message of the given
	 * level.
	 * 
	 * @param level
	 *            the level, see FeedbackMessage
	 * @return whether there is any message for this panel of the given level
	 */
	public final boolean anyMessage(int level)
	{
		List<FeedbackMessage> msgs = getCurrentMessages();

		for (FeedbackMessage msg : msgs)
		{
			if (msg.isLevel(level))
			{
				return true;
			}
		}

		return false;
	}
	
	private List<FeedbackMessage> getCurrentMessages() {
		final List<FeedbackMessage> messages = messageListView.getModelObject();
		return Collections.unmodifiableList(messages);
	}
	

	/**
	 * Gets a new instance of FeedbackMessagesModel to use.
	 * 
	 * @return Instance of FeedbackMessagesModel to use
	 */
	protected FeedbackMessagesModel newFeedbackMessagesModel()
	{
		return new FeedbackMessagesModel(this);
	}
	
	protected Component newMessageDisplayComponent(String id, FeedbackMessage message)
	{
		Serializable serializable = message.getMessage();
		Label label = new Label(id, (serializable == null) ? "" : serializable.toString());
		label.setEscapeModelStrings(AntiliaFeedBackPanel.this.getEscapeModelStrings());
		return label;
	}

	/**
	 * Gets the css class for the given message.
	 * 
	 * @param message
	 *            the message
	 * @return the css class; by default, this returns feedbackPanel + the message level, eg
	 *         'feedbackPanelERROR', but you can override this method to provide your own
	 */
	protected String getCSSClass(final FeedbackMessage message)
	{
		return "feedbackPanel" + message.getLevelAsString();
	}
}
