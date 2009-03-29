/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.utils;

import java.sql.SQLException;
import java.util.Iterator;

import org.apache.wicket.Session;
import org.apache.wicket.feedback.FeedbackMessage;
import org.hibernate.HibernateException;

import com.antilia.hibernate.PersistenceException;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExceptionUtils extends com.antilia.common.util.ExceptionUtils {

	public static String getFeedBackMessage(Throwable e) {
		if(e instanceof HibernateException) {
			HibernateException hibernateException = (HibernateException) e;
			StringBuffer sb = new StringBuffer();
			for(Throwable message: hibernateException.getThrowables()) {
				sb.append(message.getMessage());
				//sb.append(getFeedBackMessage(message));
				sb.append("\n");
			}
			return sb.toString();
		} else if(e instanceof PersistenceException) {
			PersistenceException pe = (PersistenceException) e;
			return getFeedBackMessage(pe.getCause());
		} else if(e instanceof SQLException) {
			SQLException sException = (SQLException)e;
			StringBuffer sb = new StringBuffer();			
			while (sException != null) {
				sb.append(sException.getMessage());
				sb.append("\n");
				sException = sException.getNextException();				
			}
		}				
		return getExceptionMessage(e);
	}
	
	public static String getChangeStyleScript(String style) {
		StringBuffer script = new StringBuffer();
		Iterator<FeedbackMessage> messages = Session.get().getFeedbackMessages().iterator();
		script.append(";Antilia.setStyle( new Array(");	
		int count = 0;
		while(messages.hasNext()) {
			FeedbackMessage message = messages.next();
			if(message.getReporter() != null) {
				if(count>0) {
					script.append(",");
				}
				script.append("'");
				script.append(message.getReporter().getMarkupId());
				script.append("'");				
				count++;
			}			
		}
		script.append("),'"+style+"');");
		return script.toString();
	}

}
