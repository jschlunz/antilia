/**
 * 
 */
package com.antilia.letsplay.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.antilia.letsplay.model.mock.CabezaImage;
import com.antilia.letsplay.model.mock.GormitiImage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Words {

	private static List<Word> words = new ArrayList<Word>();
	
	private static final Words instance = new Words();
	
	private Words() {
		Word word = new Word("Cabeza");
		word.setImage(new CabezaImage());
		words.add(word);
		
		word = new Word("Gormiti");
		word.setImage(new GormitiImage());
		words.add(word);
	}
	
	public Word getRandom() {
		int word = new Random().nextInt(words.size());
		return words.get(word);
	}

	public static Words getInstance() {
		return instance;
	}
}
