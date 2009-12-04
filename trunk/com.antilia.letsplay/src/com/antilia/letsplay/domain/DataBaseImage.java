/**
 * 
 */
package com.antilia.letsplay.domain;

import com.antilia.letsplay.model.Image;

/**
 * @author reiern
 *
 */
public class DataBaseImage extends Image {

	private static final long serialVersionUID = 1L;
	
	private byte[] bytes;
	
	/**
	 * 
	 */
	public DataBaseImage(byte[] bytes) {
		this.bytes = bytes;
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.model.Image#getBytes()
	 */
	@Override
	public byte[] getBytes() {
		return bytes;
	}

}
