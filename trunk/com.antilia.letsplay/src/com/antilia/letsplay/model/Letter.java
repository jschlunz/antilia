/**
 * 
 */
package com.antilia.letsplay.model;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Letter implements Serializable {

	private static final long serialVersionUID = 1L;

	private int position;
	
	private int scrambledPosition;
	
	private char text;
	
	public Letter(char text, int position, int scrambledPosition) {
		this.text = text;
		this.position = position;
		this.scrambledPosition = scrambledPosition;
	}

	public char getText() {
		return text;
	}

	public void setText(char text) {
		this.text = text;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Integer getScrambledPosition() {
		return scrambledPosition;
	}

	public void setScrambledPosition(Integer scrambledPosition) {
		this.scrambledPosition = scrambledPosition;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
