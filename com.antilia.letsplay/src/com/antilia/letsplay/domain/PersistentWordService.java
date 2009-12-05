/**
 * 
 */
package com.antilia.letsplay.domain;

import java.util.Random;

import com.antilia.common.query.IQuery;
import com.antilia.common.query.Query;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.letsplay.model.IWordService;
import com.antilia.letsplay.model.Word;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersistentWordService implements IWordService {

	private static final long serialVersionUID = 1L;

	private Long size;
	
	private Long current = -1L;
	
	/**
	 * 
	 */
	public PersistentWordService() {
		size = DefaultCommander.count(DWord.class);
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.model.IWordService#next()
	 */
	@Override
	public Word next() {
		current++;
		if(current >= size)
			current = 0L;
		IQuery<DWord> query = new Query<DWord>(DWord.class);
		query.setFirstResult(current.intValue());
		query.setMaxResults(1);
		DWord dWord =  DefaultCommander.loadList(query).get(0);
		Word w = new Word();
		w.setText(dWord.getText());
		w.setImage(new DataBaseImage(dWord.getImage().getBytes()));
		return w;
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.model.IWordService#previous()
	 */
	@Override
	public Word previous() {
		current--;
		if(current < 0)
			current = size-1;
		IQuery<DWord> query = new Query<DWord>(DWord.class);
		query.setFirstResult(current.intValue());
		query.setMaxResults(1);
		DWord dWord =  DefaultCommander.loadList(query).get(0);
		Word w = new Word();
		w.setText(dWord.getText());
		w.setImage(new DataBaseImage(dWord.getImage().getBytes()));
		return w;
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.model.IWordService#random()
	 */
	@Override
	public Word random() {
		long word = new Random().nextInt(size.intValue());
		DWord dWord = DefaultCommander.findById(DWord.class, new Long(word));
		Word w = new Word();
		while(dWord == null) {
			word = new Random().nextInt(size.intValue());
			dWord = DefaultCommander.findById(DWord.class, new Long(word));
		}
		w.setText(dWord.getText());
		w.setImage(new DataBaseImage(dWord.getImage().getBytes()));
		return w;
	}

}
