/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.excel;

import java.io.File;
import java.io.Serializable;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.antilia.common.util.ReflectionUtils;
import com.antilia.hibernate.context.IProgressReporter;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.export.AbstractExportTask;
import com.antilia.web.navigator.IPageableNavigator;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportExcelTask<E extends Serializable> extends AbstractExportTask {

	
	private IPageableNavigator<E> pageableProvider;
		
	private ITableModel<E> tableModel;
	
	
	public ExportExcelTask(IPageableNavigator<E> pageableProvider, ITableModel<E> tableModel) {
		super();
		this.pageableProvider = pageableProvider.duplicate();
		this.tableModel = tableModel;
	}
	
	@Override
	protected void doExport(IProgressReporter progressReporter) throws Exception {
		long total = getTotalTasks();
		WorkbookSettings st = new WorkbookSettings();
		WritableWorkbook document = Workbook.createWorkbook(getFile(), st);
		WritableSheet sheet = document.createSheet(tableModel.getBeanClass().getSimpleName(), 0);		
		int columns = tableModel.getColumns();
		float[] widths = new float[columns];
		int index = 0;
		java.util.Iterator<IColumnModel<E>> it = tableModel.getColumnModels();
		while(it.hasNext()) {
			IColumnModel<E> columnModel = it.next();
			widths[index] = columnModel.getWidth();
			index++;
		}
		
		it = tableModel.getColumnModels();
		int row = 0;
		int column = 0;
		while(it.hasNext()) {
			IColumnModel<E> columnModel = it.next();
			WritableCell cell = new Label(column, row, columnModel.getPropertyPath());
			sheet.addCell(cell);
			column++;
		}
						
		for(long i=1; i<= total; i++) {
			row++;
			column=0;
			if(progressReporter != null && progressReporter.isCanceled())
				break;			
			if(progressReporter != null) {
				progressReporter.setCurrentTask(i);			
				progressReporter.setMessage("Exporting record " + i + " of " + total);
			}
			Thread.sleep(10);
			E bean = pageableProvider.current();
			pageableProvider.next();
			it = tableModel.getColumnModels();
			while(it.hasNext()) {
				IColumnModel<E> columnModel = it.next();
				Object value = ReflectionUtils.getPropertyValue(bean, columnModel.getPropertyPath());
				if(value != null) {
					WritableCell cell = new Label(column, row, value.toString());
					sheet.addCell(cell);
				}else {
					WritableCell cell = new Label(column, row, "Hola");
					sheet.addCell(cell);
				}
				column++;
			}													
		}
		document.write();
		document.close();
	}
	
	@Override
	protected File getExportFile() throws Exception {
		return File.createTempFile("export", ".xls");
	}
	
	@Override
	protected long getTotalTasks() {
		return pageableProvider.size();
	}
	
	@Override
	protected String getIntialMessage() {
		return "Exporting to Excel";
	}
}
