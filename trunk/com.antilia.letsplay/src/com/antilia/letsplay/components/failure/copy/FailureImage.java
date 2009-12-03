/**
 * 
 */
package com.antilia.letsplay.components.failure.copy;

import java.io.IOException;

import com.antilia.common.util.FileUtils;
import com.antilia.letsplay.model.Image;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class FailureImage extends Image {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private int level;
	
	/**
	 * 
	 */
	public FailureImage(String name, int level) {
		this.name = name;
		this.level = level;
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.model.Image#getBytes()
	 */
	@Override
	public byte[] getBytes() {
		try {
			return FileUtils.bytes(getClass().getResourceAsStream(name+level+".gif"));
		} catch (IOException e) {
			return null;
		}
	}

}
