/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.model.IModel;

import com.antilia.common.dao.IQuerableUpdatableDao;
import com.antilia.common.iterator.EmptyIterator;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.Query;
import com.antilia.common.util.ReflectionUtils;
import com.antilia.jsp.resources.Resources;
import com.antilia.web.beantable.model.FirstColumnModel;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.navigator.INavigatorSelector;
import com.antilia.web.navigator.IPageableNavigator;
import com.antilia.web.navigator.impl.DataProviderPageableNavigator;
import com.antilia.web.provider.impl.ListQuerableUpdatebleDataProvider;
import com.antilia.web.provider.impl.SourceSelector;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 
 */
public class TableComponent<E extends Serializable> extends AbstractComponent {
	
	private Class<E> beanClass;
	
	private IPageableNavigator<E> pageableNavigator;
	
	private INavigatorSelector<E> sourceSelector;
	
	private ITableModel<E> tableModel;
	
	private FirstColumnModel firstColumnModel;

	/**
	 * This variable is needed t fix a problem with drag and drop not working for IE
	 */
	private int rendringCount = 0;
	
	private boolean dragableColumns = true;
	
	/**
	 * Flag to set all columns re-sizable or not.
	 */
	private boolean columnsResizable = true;
	
	/**
	 * Constructor accepting a List.
	 * 
	 * @param id
	 * @param tableModel
	 * @param elements
	 */
	public TableComponent(String id, ITableModel<E> tableModel, Collection<E> elements) {
		this(id, tableModel, new DataProviderPageableNavigator<E>(new ListQuerableUpdatebleDataProvider<E>(elements),new Query<E>(tableModel.getBeanClass())));
	}
	
	/**
	 * 
	 * @param id
	 * @param tableModel
	 * @param dao
	 * @param query
	 */
	public TableComponent(String id, ITableModel<E> tableModel, IQuerableUpdatableDao<E> dao, IQuery<E> query) {
		this(id, tableModel, new DataProviderPageableNavigator<E>(dao, query));
	}
	
	public TableComponent(String id, ITableModel<E> tableModel, IPageableNavigator<E> pageableProvider)  {
		super(id);
		setOutputMarkupId(true);		
		this.pageableNavigator = pageableProvider;
		this.sourceSelector = new SourceSelector<E>(this.pageableNavigator ,tableModel.getSelectionMode());
		this.tableModel = tableModel;
		this.firstColumnModel = new FirstColumnModel(65);
		
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_PROTOTYPE));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_EFFECT));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_DRAGDROP));
		addHeaderContributor(HeaderContributor.forCss(Resources.CSS_MAIN));
		addHeaderContributor(HeaderContributor.forCss(getTableCSS()));
		
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_YUI_DOM_EVENT));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_YUI_DOM_MIN));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_YUI_EVENT));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_YUI_ANIMATION));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_YUI_DRAG_DROP));
		
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_COMMON));
		addHeaderContributor(HeaderContributor.forJavaScript(Resources.JS_TABLE));
	}
	
	protected ResourceReference getTableCSS() {
		
		return Resources.CSS_TABLE;
	}
	
	@Override
	protected void onRender(PrintWriter writer, HttpServletRequest request) throws Exception {		
		writer.println("<div id=\"");
		writer.println(getId());
		writer.println("\">");
		writer.println("<table cellpadding=\"0\" cellspacing=\"0\" class=\"tbody\">");
		writer.println("<tbody>");
		writer.println("<tr class=\"theader\">");
		writer.println("<td class=\"theader\" nowrap=\"nowrap\"></td>");
		writer.println("</tr>");
		writer.println("<tr>");			
		writer.println("<td>");
		writer.println("	<table  cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\">");					
		writer.println("		<tbody>");
		renderHeaderCells(writer, request, firstColumnModel, tableModel);
		renderBodyCells(writer, request, firstColumnModel, tableModel);
		
		writer.println("</tbody>");
		writer.println("</table>");
		
		writer.println("</tbody>");
		writer.println("</table>");
		

		writer.println("<script wicket:id=\"script\" type=\"text/javascript\">");
		String tableId = getId();
		StringBuffer sb = new StringBuffer();
		sb.append("var ");
		sb.append(tableId);
		sb.append(" = new Table('" + tableId + "','");
		sb.append(/*getFirstColumnUrl()+*/ "',");
		sb.append("new Array(");
		IPageableNavigator<E> source = TableComponent.this.getPageableNavigator();
		INavigatorSelector<E> selector = TableComponent.this.getSourceSelector();
		Iterator<IModel<E>> it = source.getCurrentPage();
		int i=0;
		while(it.hasNext()) {
			boolean selected = selector.isSelected(i);					
			it.next();
			sb.append("new Row('");
			sb.append(tableId);
			sb.append("',");
			sb.append(i);
			sb.append(",");
			sb.append(selected);
			sb.append(")");
			if(it.hasNext())
				sb.append(",");
			i++;					
		}		
		sb.append(")");
		sb.append("," + (TableComponent.this.getTableModel().getColumns()+1));
		sb.append("," + (TableComponent.this.getRendringCount()));
		sb.append("," + TableComponent.this.getDraggerUrlAsArray());
		sb.append("," + /*Table.this.isIe6()*/ false);
		sb.append("," + TableComponent.this.isDragableColumns());
		sb.append(");");
		sb.append(tableId+".");
		sb.append("createDraggables();");
		writer.println(sb.toString());
		writer.println("</script>");
		writer.println("</div>");
		writer.flush();
	}
	
	public String getDraggerUrlAsArray() {
		StringBuffer sb = new StringBuffer();
		sb .append("new Array(");
		Iterator<String> it = new EmptyIterator<String>();
		while(it.hasNext()) {
			String url = it.next();			
			sb .append("'");	
			sb .append(url);
			sb .append("'");
			if(it.hasNext()) {
				sb .append(",");	
			}
		}
		sb .append(")");
		return sb.toString();		
	}
	
	private void renderHeaderCells(PrintWriter writer, HttpServletRequest request, FirstColumnModel firstColumnModel, ITableModel<E> tableModel) throws Exception {
		writer.println("<tr class=\"theaderrow\">");
		
		writer.println("<td width=\"100px\" class=\"resizeCell\" nowrap=\"nowrap\"><div wicket:id=\"hcell\">");
		renderDefaultFirstHeaderCell(writer, request, firstColumnModel);
		writer.println("</div></td>");
		Iterator<IColumnModel<E>> it = tableModel.getColumnModels();		
		int column = 1;
		while(it.hasNext()) {
			IColumnModel<E> model = it.next();			
			writer.println("<td width=\"100px\" class=\"resizeCell\" nowrap=\"nowrap\">");
			renderDefaultHeaderCell(writer, request, model, column);	
			writer.println("</td>");
			column ++;
		}		
		writer.println("<td class=\"addCol\">");		
		writer.println("<div wicket:id=\"lastHeader\" style=\"\">");
		writer.println("&nbsp;&nbsp;&nbsp;");
		writer.println("</div>");		
		writer.println("</td>");
		writer.println("</tr>");
	}
	
	private void renderDefaultHeaderCell(PrintWriter writer, HttpServletRequest request, IColumnModel<E> model, int column) throws Exception {
		writer.println("<input name=\"colWidth\" type=\"hidden\"/>");	
		writer.println("<table height=\"100%\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
		writer.println("<tr>");
		writer.println("<td nowrap=\"nowrap\">");
		String id = getId()+"_dragger_"+ getRendringCount() + "_" +column;		
		writer.println("<div id=\""+id+"\" class=\""+getId()+"\" ondblclick=\"\" style=\"border: 1px solid transparent;\">");
		writer.println("	<table  height=\"100%\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
		writer.println("	<td nowrap=\"nowrap\"><div  wicket:id=\"title\" class=\"headerTitle\">");
		writer.println(model.getPropertyPath());
		writer.println("</div></td>");
		writer.println("	<td nowrap=\"nowrap\" style=\"vertical-align: top;\"><div  wicket:id=\"menu\"></div></td>");
		writer.println("	</table>");
		writer.println("	</div>");
		writer.println("	</td>");
		
		String resizeId = null;
		if(!isColumnsResizable())
			resizeId = getId()+"_cND_"+column;
		else if(model.isResizable() )
			resizeId = getId()+"_c_"+column;				
		// this naming does the trick of making the column non re-sizable
		else 
			resizeId =getId()+"_c_"+column;
		
		String dragableClass = null;		
		if(!isColumnsResizable())
			dragableClass = "noResCol";
		else if(model.isResizable() )
			dragableClass = "resCol";				
		else 
			dragableClass = "resCol";
		
		writer.write("	<td id=\""+resizeId+"\" class=\""+dragableClass+"\" nowrap=\"nowrap\" style=\"min-width: 10px;\">&nbsp;</td>");
		writer.println("	</tr>");
		writer.println("	</table>");
	}
	
	private void renderDefaultFirstHeaderCell(PrintWriter writer, HttpServletRequest request, FirstColumnModel firstColumnModel) throws Exception {
		writer.println("<input name=\"colWidth\" type=\"hidden\"/>");	
		writer.println("<table height=\"100%\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
		writer.println("<tr>");
		writer.println("<td nowrap=\"nowrap\">");
		writer.println("<div wicket:id=\"dragger\" class=\"\" ondblclick=\"\" style=\"border: 1px solid transparent;\">");
		writer.println("	<table  height=\"100%\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
		writer.println("	<td nowrap=\"nowrap\"><div  wicket:id=\"title\" class=\"headerTitle\">");
		writer.println("&nbsp;");
		writer.println("</div></td>");
		writer.println("	<td nowrap=\"nowrap\" style=\"vertical-align: top;\"><div  wicket:id=\"menu\"></div></td>");
		writer.println("	</table>");
		writer.println("	</div>");
		writer.println("	</td>");
		String id = getId()+"_dragger_"+ getRendringCount() + "_" +0;
		writer.write("	<td id=\"");
		writer.write(id);
		writer.println("\" class=\"resCol\" nowrap=\"nowrap\" style=\"min-width: 10px;\">&nbsp;</td>");
		writer.println("	</tr>");
		writer.println("	</table>");
	}
	
	private void renderBodyCells(PrintWriter writer, HttpServletRequest request, FirstColumnModel firstColumnModel, ITableModel<E> tableModel) throws Exception {				
		pageableNavigator.setPageSize(10);
		Iterator<IModel<E>> it = pageableNavigator.getCurrentPage();				
		int row = 0;
		while(it.hasNext()) {
			IModel<E> modele = it.next();			
			E bean = modele.getObject();
		
			String rowClassName = "tbodyrow" + (row%2);
			// rows
			writer.println("<tr id=\"\" class=\""+rowClassName+"\" onmouseover=\"\" onmouseout=\"\" onclick=\"\">");
			
			writer.println("<td class=\"tbodycol\"></td>");
			
			
			Iterator<IColumnModel<E>> it1 = tableModel.getColumnModels();		
			while(it1.hasNext()) {
				IColumnModel<E> model = it1.next();				
				// cols
				writer.println("<td class=\"tbodycol\">");
				writer.print(ReflectionUtils.getPropertyValue(bean, model.getPropertyPath()));
				writer.println("</td>");
			}
			
			// extra cols
			writer.println("<td class=\"addCol\" nowrap=\"nowrap\" width=\"30px;\">&nbsp;&nbsp;&nbsp;</td>");
			writer.println("</tr>");
			row++;
		}		
	}

	/**
	 * @return the beanClass
	 */
	public Class<E> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<E> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @return the pageableProvider
	 */
	public IPageableNavigator<E> getPageableNavigator() {
		return pageableNavigator;
	}

	/**
	 * @return the sourceSelector
	 */
	public INavigatorSelector<E> getSourceSelector() {
		return sourceSelector;
	}

	/**
	 * @return the tableModel
	 */
	public ITableModel<E> getTableModel() {
		return tableModel;
	}

	/**
	 * @return the firstColumnModel
	 */
	public FirstColumnModel getFirstColumnModel() {
		return firstColumnModel;
	}

	/**
	 * @return the rendringCount
	 */
	public int getRendringCount() {
		return rendringCount;
	}

	/**
	 * @param rendringCount the rendringCount to set
	 */
	public void setRendringCount(int rendringCount) {
		this.rendringCount = rendringCount;
	}

	/**
	 * @return the dragableColumns
	 */
	public boolean isDragableColumns() {
		return dragableColumns;
	}

	/**
	 * @param dragableColumns the dragableColumns to set
	 */
	public void setDragableColumns(boolean dragableColumns) {
		this.dragableColumns = dragableColumns;
	}

	/**
	 * @return the columnsResizable
	 */
	public boolean isColumnsResizable() {
		return columnsResizable;
	}

	/**
	 * @param columnsResizable the columnsResizable to set
	 */
	public void setColumnsResizable(boolean columnsResizable) {
		this.columnsResizable = columnsResizable;
	}

}
