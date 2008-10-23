package com.antilia.hibernate.context;

import java.io.Serializable;

/**
 * @author reiern70@gmain.com
 *
 */
public interface IProgressReporter extends Serializable {

	void setTotalTasks(long total);

	void setCurrentTask(long current);
	
	float getProgress();
	
	void setMessage(String message);
	
	String getMessage();	
}
