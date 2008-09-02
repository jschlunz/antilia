/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import com.antilia.web.beantable.provider.IPageableProvider;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
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
	
	public ExportPdfTask(IPageableProvider<E> pageableProvider) {
		this.pageableProvider = pageableProvider;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {			
			try {						
				int total =  pageableProvider.size();				
				file = File.createTempFile("export", ".pdf");
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(file));								
				document.open();
				this.progress = 0;
				for(int i=1; i<= total; i++) {
					this.progress = (int)(((float)i/(float)total)*100);
					Thread.currentThread().sleep(100);
					Table table = new Table(1);
					table.addCell("Row "+i);
					document.add(table);
				}
				document.close();
			} catch (Exception e) {
				
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
