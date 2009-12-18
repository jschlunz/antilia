/**
 * 
 */
package com.antilia.letsplay.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.letsplay.PlaySession;
import com.antilia.letsplay.components.failure.ImageFailureReporter;
import com.antilia.letsplay.model.Letter;
import com.antilia.letsplay.model.Word;
import com.antilia.letsplay.service.IWordService;
import com.antilia.letsplay.service.PersistentWordService;
import com.antilia.web.dragdrop.YuiDraggableTarget;
import com.antilia.web.resources.DefaultStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ScrambledWordPanel extends Panel {
	
	private static final long serialVersionUID = 1L;
	
	private Word word;
	
	public static final ResourceReference JS = new ResourceReference(ScrambledWordPanel.class, "scrambled.js");
	
	private static class DDTarget implements Serializable {		
		
		private static final long serialVersionUID = 1L;

		private String source;
				
		private String url;
		
		
		public DDTarget(String source,  String url) {
			super();
			this.source = source;
			this.url = url;
		}

		public String getSource() {
			return source;
		}


		public String getUrl() {
			return url;
		}
		
	}
	
	private List<DDTarget> targets;
	
	private List<IModel<Letter>> answer;
	
	private List<IModel<Letter>> source;
	
	//TODO: injection.
	private IFailureReporter failureReporter = ImageFailureReporter.getInstance();
	
	private int failures = 0;
	
	private WebMarkupContainer solutionBox;
	
	private WebMarkupContainer failureBox;
	
	private int renderingCount = 0;
	
	//TODO: injection.
	private IWordService wordService = new PersistentWordService();
	
	public ScrambledWordPanel(String id) {
		super(id);		
		setOutputMarkupId(true);
		this.word = wordService.random(PlaySession.getSession().getLanguage());
		init();
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_MIN));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_ANIMATION));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DRAG_DROP));
		
		add(JavascriptPackageResource.getHeaderContribution(JS));
		
		add(new PreviousWordLink("previous"));		
		add(new RandomWordLink("random"));		
		add(new NextWordLink("next"));
		
		solutionBox = new WebMarkupContainer("solutionBox");
		solutionBox.setOutputMarkupId(true);
		add(solutionBox);
		RefreshingView<Letter> cell =  new RefreshingView<Letter>("cell") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<Letter>> getItemModels() {	
				return ScrambledWordPanel.this.source.iterator();
			}
			
			@Override
			public boolean isVisible() {
				return failures < 4;
			}
			
			@Override
			protected void populateItem(final Item<Letter> item) {
				WebMarkupContainer source =  new WebMarkupContainer("source") {
					 
					private static final long serialVersionUID = 1L;

					public String getMarkupId() {
						return "letter_s_" + item.getIndex() + "_" + renderingCount;
					}					
				};
				source.setOutputMarkupId(true);
				item.add(source);
				Label letter = new Label("letter", item.getModel().getObject().getText()+"");
				letter.setRenderBodyOnly(true);
				source.add(letter);
			}
		};
		
		solutionBox.add(cell);
		
		WebMarkupContainer exito  = new WebMarkupContainer("exito") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return ScrambledWordPanel.this.source.size() == 0;
			}
		};
		
		
		solutionBox.add(exito);
		
		RefreshingView<Letter> targets =  new RefreshingView<Letter>("targets") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<Letter>> getItemModels() {	
				return ScrambledWordPanel.this.answer.iterator();
			}
			
			@Override
			public boolean isVisible() {
				return failures < 4;
			}
			
			@Override
			protected void populateItem(final Item<Letter> item) {
				YuiDraggableTarget target =  new YuiDraggableTarget("target") {
					 
					private static final long serialVersionUID = 1L;

					public String getMarkupId() {
						return "letter_t_" + item.getIndex() + "_" + renderingCount;
					}
					
					@Override
					public void onDrop(String sourceId, String targetId, AjaxRequestTarget target) {												
						if(target != null) {
							renderingCount++;
							if(targetId.indexOf("_s")>0) {
								target.addComponent(ScrambledWordPanel.this.solutionBox);
								return;
							}	
							if(targetId.indexOf("s")>0)
								target.addComponent(ScrambledWordPanel.this);
							int nsource = Integer.parseInt(sourceId.substring(9, sourceId.lastIndexOf('_')));
							int ntarget = Integer.parseInt(targetId.substring(9, sourceId.lastIndexOf('_')));
							Letter letter = ScrambledWordPanel.this.source.get(nsource).getObject();
							Letter tLater = ScrambledWordPanel.this.answer.get(ntarget).getObject();
							if(tLater.getText() == '?') {
								Letter allowed = ScrambledWordPanel.this.word.getLetters().get(ntarget);
								if(allowed.getText()==letter.getText()) {
									ScrambledWordPanel.this.answer.set(ntarget, new Model<Letter>(letter));
									ScrambledWordPanel.this.source.remove(nsource);
								} else {
									ScrambledWordPanel.this.failures++;
									target.addComponent(ScrambledWordPanel.this.failureBox);
								}
							}
							target.addComponent(ScrambledWordPanel.this.solutionBox);							
						}
					}
					
					
					@Override
					protected void renderOnDrag(MarkupStream markupStream) {
						getTargets().add(new DDTarget("letter_s_" + item.getIndex() + "_" + renderingCount, onDropBehavior.getCallbackUrl().toString()));
					}
				};
				item.add(target);
				Label letter = new Label("letter", item.getModel().getObject().getText()+"");
				letter.setRenderBodyOnly(true);
				target.add(letter);
			}
		};
		
		solutionBox.add(targets);
		
		WebMarkupContainer solu = new WebMarkupContainer("solu") {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return failures >= 4;
			}
		};		
		
		solutionBox.add(solu);
		
		RefreshingView<Letter> solution = new RefreshingView<Letter>("solution") {
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<Letter>> getItemModels() {	
				return ScrambledWordPanel.this.word.getLetterModels().iterator();
			}
			
			@Override
			protected void populateItem(final Item<Letter> item) {				
				Label letter = new Label("letter", item.getModel().getObject().getText()+"");
				letter.setRenderBodyOnly(true);
				item.add(letter);
			}
			
			@Override
			public boolean isVisible() {
				return failures >= 4;
			}
		};
		
		solu.add(solution);
		
		Label script = new Label("script", new Model<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				StringBuffer sb = new StringBuffer();
				for(DDTarget target: ScrambledWordPanel.this.targets){
					sb.append("new Letter('"+target.getSource()+"','"+target.getUrl()+"');");					
				}								
				for(int i = 0; i < ScrambledWordPanel.this.answer.size();i++){
					sb.append("new Target('letter_t_" + i + "_" + renderingCount+"');");
				}				
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		solutionBox.addOrReplace(script);
				
		NonCachingImage image = new NonCachingImage("image", new DynamicImageResource() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData() {
				return ScrambledWordPanel.this.word.getImage().getBytes();
			}
		});

		add(image);
		 
		failureBox = new WebMarkupContainer("failureBox") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				addOrReplace(failureReporter.createErrorReporter("failure", failures));				
				super.onBeforeRender();
			}
		};
		Label vidas = new Label("vidas", new AbstractReadOnlyModel<String>() {
				
			private static final long serialVersionUID = 1L;

			@Override
				public String getObject() {
					return Integer.toString(4 - ScrambledWordPanel.this.failures);
				}
		});			
		failureBox.add(vidas);

		failureBox.setOutputMarkupId(true);
		add(failureBox);
	}
	
	public void init() {
		failures = 0;
		targets = new ArrayList<DDTarget>();
		this.answer = this.word.getDummyLetters();
		this.source = this.word.getScrambledLetterModels();
	}
	
	public void next(AjaxRequestTarget target) {
		this.word = wordService.next(PlaySession.getSession().getLanguage());
		init();
		if(target != null) {
			target.addComponent(this);
		}
	}
	
	public void previous(AjaxRequestTarget target) {
		this.word = wordService.previous(PlaySession.getSession().getLanguage());
		init();
		if(target != null) {
			target.addComponent(this);
		}
	}
	
	public void random(AjaxRequestTarget target) {
		this.word = wordService.random(PlaySession.getSession().getLanguage());
		init();
		if(target != null) {
			target.addComponent(this);
		}
	}
	
	@Override
	protected void onBeforeRender() {
		
		super.onBeforeRender();
	}

	public List<DDTarget> getTargets() {
		return targets;
	}

	public void setTargets(List<DDTarget> targets) {
		this.targets = targets;
	}

	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

}
