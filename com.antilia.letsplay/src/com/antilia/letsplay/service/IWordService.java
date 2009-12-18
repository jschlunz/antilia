package com.antilia.letsplay.service;

import java.io.Serializable;

import com.antilia.letsplay.model.Word;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IWordService extends Serializable {

	Word previous();
	
	Word next();
	
	Word random();
	
}
