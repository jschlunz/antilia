/**
 * 
 */
package com.antilia.letsplay.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.common.util.StringUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Word implements Serializable {

	private static final long serialVersionUID = 1L;

	public String text;
	
	private List<Letter> scrambledLetters;
	
	private List<Letter> letters;
	

	private List<IModel<Letter>> dummyLetters;
	
	public Word(String text) {
		if(StringUtils.isEmpty(text))
			throw new IllegalArgumentException("Text cannot be null!");
		this.text = text.trim().toUpperCase();
	}

	public Word() {
	}

	
	public List<Letter> getScrambledLetters() {
		
		if(scrambledLetters != null)
			return scrambledLetters;
		Random random = new Random();
		this.scrambledLetters = new ArrayList<Letter>(text.length());
		List<Integer> positions = new ArrayList<Integer>();
		for(int i=0;i < text.length(); i++) {
			char c = text.charAt(i);
			int pos = random.nextInt(text.length());
			while(positions.contains(pos)) {
				pos = random.nextInt(text.length());
			}
			positions.add(pos);
			Letter letter = new Letter(c, i, pos);
			scrambledLetters.add(letter);
		}
		Collections.sort(scrambledLetters, new Comparator<Letter>() {
			public int compare(Letter o1, Letter o2) {
				return o1.getScrambledPosition().compareTo(o2.getScrambledPosition());
			};
		});
		return scrambledLetters;
	}
	
	public List<IModel<Letter>> getScrambledLetterModels() {
		List<Letter> letters = getScrambledLetters();
		List<IModel<Letter>> models = new ArrayList<IModel<Letter>>();
		for(Letter letter: letters) {
			models.add(new Model<Letter>(letter));
		}
		return models;
	}
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Letter> getLetters() {
		if(letters == null) {
			letters = new ArrayList<Letter>();
			for(int i=0;i < text.length(); i++) {
				letters.add(new Letter(text.charAt(i), i, i));
			}
		}
		return letters;
	}

	public List<IModel<Letter>> getDummyLetters() {
		if(dummyLetters == null) {
			dummyLetters = new ArrayList<IModel<Letter>>();
			for(int i=0;i < text.length(); i++) {
				dummyLetters.add(new Model<Letter>(new Letter('?', i, i)));
			}
		}
		return dummyLetters;
	}
}
