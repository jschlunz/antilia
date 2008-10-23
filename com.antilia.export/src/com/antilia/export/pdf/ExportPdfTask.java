/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import com.antilia.common.util.ReflectionUtils;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPdfTask<E extends Serializable> implements Runnable {

	private int progress = 0;
	
	private IPageableProvider<E> pageableProvider;
		
	private boolean finished = false;
	
	private File file;
	
	private ITableModel<E> tableModel;
	
	private IPersistenceUnit persistenceUnit;
	
	private String user;
	
	public ExportPdfTask(IPageableProvider<E> pageableProvider, IPersistenceUnit persistenceUnit, String user, ITableModel<E> tableModel) {
		this.pageableProvider = pageableProvider.duplicate();
		this.persistenceUnit = persistenceUnit;
		this.user = user;
		this.tableModel = tableModel;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {						
			try {
				// setting up the request context...
				RequestContext requestContext = RequestContext.get();
				requestContext.setPersistenceUnit(persistenceUnit);
				requestContext.setUser(user);				
				int total =  pageableProvider.size();
				file = File.createTempFile("export", ".pdf");
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(file));								
				document.open();
				this.progress = 0;
				int columns = tableModel.getColumns();
				float[] widths = new float[columns];
				int index = 0;
				java.util.Iterator<IColumnModel<E>> it = tableModel.getColumnModels();
				while(it.hasNext()) {
					IColumnModel<E> columnModel = it.next();
					widths[index] = columnModel.getWidth();
					index++;
				}
				
				PdfPTable table = new PdfPTable(widths);
				it = tableModel.getColumnModels();
				while(it.hasNext()) {
					IColumnModel<E> columnModel = it.next();
					table.addCell(columnModel.getPropertyPath());
				}
				document.add(table);
				
				for(int i=1; i<= total; i++) {
					this.progress = (int)(((float)i/(float)total)*100);
					Thread.currentThread().sleep(10);
					table = new PdfPTable(widths);
					
					E bean = pageableProvider.current();
					pageableProvider.next();
					it = tableModel.getColumnModels();
					while(it.hasNext()) {
						IColumnModel<E> columnModel = it.next();
						Object value = ReflectionUtils.getPropertyValue(bean, columnModel.getPropertyPath());
						if(value != null)
							table.addCell(value.toString());
						else 
							table.addCell("");
					}										
					document.add(table);
				}
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				finished = true;
			}
	}

	/**
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}
	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}
	public File getFile() {
		return file;
	}
}
