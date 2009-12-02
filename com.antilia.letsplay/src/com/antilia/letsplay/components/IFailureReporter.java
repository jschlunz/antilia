package com.antilia.letsplay.components;

import org.apache.wicket.Component;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IFailureReporter {
	
	Component createErrorReporter(int level);

}
