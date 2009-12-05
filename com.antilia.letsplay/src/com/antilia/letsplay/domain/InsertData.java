package com.antilia.letsplay.domain;

import java.io.Serializable;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.AbstractPersistentCommand;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.letsplay.model.Words;

public class InsertData extends AbstractPersistentCommand<DWord, Serializable> {

	public InsertData() {
		super(DWord.class);
	}
	
	@Override
	protected Serializable doExecute() throws Throwable {
		for(com.antilia.letsplay.model.Word w:  Words.getInstance().getAll()) {
			DWord dWord = new DWord();
			dWord.setText(w.getText());
			DImage dImage = new DImage();
			dImage.setDescription(w.getText());
			dImage.setMimeType("img/jpeg");
			dImage.setBytes(w.getImage().getBytes());
			DefaultCommander.persist(dImage);
			dWord.setImage(dImage);
			DefaultCommander.persist(dWord);
		}		
		return true;
	}
	
	public static void main(String[] args) {
		IPersistenceUnit persistenceUnit = HSQLDBPersistenceUnit.getInstance();
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);
		requestContext.setUser("test");
		DefaultCommander.execute(new InsertData());
	}
	
}
