/**
 * 
 */
package com.antilia.letsplay.model;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	public Image() {
	}

	public abstract byte[] getBytes();
}
