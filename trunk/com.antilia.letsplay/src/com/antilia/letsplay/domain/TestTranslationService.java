/**
 * 
 */
package com.antilia.letsplay.domain;


/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestTranslationService {


	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		GoogleTranslationService translationService = new GoogleTranslationService();
		DWord word = new DWord();
		word.setText("Hello World!");
		System.out.println(translationService.translate(word));
	}

}
