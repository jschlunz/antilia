/**
 * 
 */
package com.antilia.letsplay.model;

import java.io.IOException;

import com.antilia.common.util.FileUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ResourceImage extends Image {

	private static final long serialVersionUID = 1L;

	private String name;
	
	/**
	 * 
	 */
	public ResourceImage(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.model.Image#getBytes()
	 */
	@Override
	public byte[] getBytes() {
		try {
			return FileUtils.bytes(getClass().getResourceAsStream(name));
		} catch (IOException e) {
			return null;
		}
	}

}
