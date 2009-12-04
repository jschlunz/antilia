package com.antilia.letsplay.model;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IWordService extends Serializable {

	Word previous();
	
	Word next();
	
	Word random();
	
}
