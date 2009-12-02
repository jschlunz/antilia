package com.antilia.letsplay.components;

import java.io.Serializable;

import org.apache.wicket.Component;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IFailureReporter extends Serializable {
	
	Component createErrorReporter(String id, final int level);

}
