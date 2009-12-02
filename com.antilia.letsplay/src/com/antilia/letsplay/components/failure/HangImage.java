/**
 * 
 */
package com.antilia.letsplay.components.failure;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HangImage extends FailureImage {

	private static final long serialVersionUID = 1L;

	/**
	 * @param name
	 * @param level
	 */
	public HangImage(int level) {
		super("Hang", level);
	}

}
