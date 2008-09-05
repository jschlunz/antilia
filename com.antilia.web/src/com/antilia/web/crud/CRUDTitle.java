/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDTitle  extends Panel {

	private static final long serialVersionUID = 1L;
	
	public static class CRUDInfo {
		
		private Class<?> beanClass;
		
		private CRUDMode crudMode;

		/**
		 * @param beanClass
		 * @param crudMode
		 */
		public CRUDInfo(Class<?> beanClass, CRUDMode crudMode) {
			super();
			this.beanClass = beanClass;
			this.crudMode = crudMode;
		}
		

		public CRUDMode getCrudMode() {
			return crudMode;
		}

		public void setCrudMode(CRUDMode crudMode) {
			this.crudMode = crudMode;
		}

		public Class<?> getBeanClass() {
			return beanClass;
		}

		public void setBeanClass(Class<?> beanClass) {
			this.beanClass = beanClass;
		}
		
		
	}

	private static class MyArrayList <B>extends ArrayList<B> {
		
		private static final long serialVersionUID = 1L;

		public MyArrayList() {
		}
		
		public MyArrayList<B> addClass(B object) {
				add(object);
				return this;
		}
	}
	
	private static class ChainedReourceModel extends Model<String> {
		
		private static final long serialVersionUID = 1L;

		private List<StringResourceModel> models;
		
		private String separator;
		
		public ChainedReourceModel(CRUDTitle title, String separator, List<CRUDInfo> infos) {
			this.separator = separator;
			this.models = new ArrayList<StringResourceModel>();
			if(infos != null) {
				for(CRUDInfo info: infos) {
					String key = ResourceUtils.getPropertyResourceKey(info.getBeanClass(), null);					
					this.models.add(new StringResourceModel(key, title, null, info.getBeanClass().getSimpleName()));
					
					key = ResourceUtils.getPropertyResourceKey(CRUDMode.class,  info.getCrudMode().name());					
					this.models.add(new StringResourceModel(key, title, null, info.getCrudMode().name()));
				}
			}			
		}
		
		@Override
		public String getObject() {
			if(models != null) {
				StringBuffer sb = new StringBuffer();
				Iterator<StringResourceModel>  it =models.iterator();
				while(it.hasNext()) {
					StringResourceModel model = it.next();
					sb.append(model.getString());
					if(it.hasNext())
						sb.append(separator);
				}
				return sb.toString();
			}
			return "";
		}
	}

	public CRUDTitle(String id, CRUDInfo info) {
		this(id, new MyArrayList <CRUDInfo>().addClass(info));
	}
	
	/**
	 * @param id
	 */
	public CRUDTitle(String id, List<CRUDInfo> infos) {
		super(id);
		setRenderBodyOnly(true);
		add(new Label("title",  new ChainedReourceModel(this, " > ", infos)));
	}
}
