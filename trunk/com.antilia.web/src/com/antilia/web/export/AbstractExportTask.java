/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.export;

import java.io.File;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.IProgressReporter;
import com.antilia.hibernate.context.ProgressReporter;
import com.antilia.hibernate.context.RequestContext;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AbstractExportTask implements Runnable {

	private IProgressReporter progressReporter;
	
	private boolean finished = false;
	
	private File file;
		
	private IPersistenceUnit persistenceUnit;
	
	private String user;
	
	public AbstractExportTask() {
		this(RequestContext.get().getPersistenceUnit(), RequestContext.get().getUser());
	}
	
	public AbstractExportTask(IPersistenceUnit persistenceUnit, String user) {
		this.persistenceUnit = persistenceUnit;
		this.user = user;
		this.progressReporter = new ProgressReporter();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public final void  run() {								
			try {						
				// setting up the request context...
				RequestContext requestContext = RequestContext.get();
				requestContext.setPersistenceUnit(persistenceUnit);
				requestContext.setUser(user);						
				requestContext.setProgressReporter(progressReporter);
				
				this.progressReporter.setMessage(getIntialMessage());
				this.progressReporter.setTotalTasks(getTotalTasks());
				file = getExportFile();				
				doExport(progressReporter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				finished = true;
			}
	}

	protected abstract void doExport(IProgressReporter progressReporter) throws Exception;
	
	protected abstract File getExportFile() throws Exception;
	
	protected abstract long getTotalTasks();
	
	protected abstract String getIntialMessage();
		
	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}
	public File getFile() {
		return file;
	}
	
	public IProgressReporter getProgressReporter() {
		return progressReporter;
	}

	public void setProgressReporter(IProgressReporter progresReporter) {
		this.progressReporter = progresReporter;
	}
}
