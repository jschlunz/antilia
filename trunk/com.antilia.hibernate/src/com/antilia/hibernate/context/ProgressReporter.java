/**
 * 
 */
package com.antilia.hibernate.context;

/**
 * @author EReinaldoB
 *
 */
public class ProgressReporter implements IProgressReporter {

	private static final long serialVersionUID = 1L;

	private String message;	

	private long total;
	
	private long current = 0;
	
	private boolean canceled = false;
	
	public ProgressReporter() {		
	}
	
	public ProgressReporter(String message, long total) {
		super();
		this.message = message;		
		if(total<=0)
			throw new IllegalArgumentException("Total cannot be a negative number!");		
		this.total = total;
	}
	
	/* (non-Javadoc)
	 * @see fcc.ima.wicket.crud.imadbs.IProgresReporter#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see fcc.ima.wicket.crud.imadbs.IProgresReporter#getProgress()
	 */
	@Override
	public float getProgress() {
		return (int)(((float)current/(float)total)*100);
	}

	/* (non-Javadoc)
	 * @see fcc.ima.wicket.crud.imadbs.IProgresReporter#setCurrentTaks(int)
	 */
	@Override
	public void setCurrentTask(long current) {
		this.current = current;
	}

	/* (non-Javadoc)
	 * @see fcc.ima.wicket.crud.imadbs.IProgresReporter#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see fcc.ima.wicket.crud.imadbs.IProgresReporter#setTotalTasks(int)
	 */
	@Override
	public void setTotalTasks(long total) {
		this.total = total;
	}
	
	@Override
	public void cancelJob() {
		this.canceled = true;
	}
	
	@Override
	public boolean isCanceled() {
		return this.canceled;
	}

}
