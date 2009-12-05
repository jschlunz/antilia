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
		
		User user = new User();
		user.setName("Ernesto");
		user.setLastName("Reinaldo Barreiro");
		user.setEmail("erb@test.cl");
		user.setLogname("erbjulio");
		user.setPassword("erbjulio");
		user.setRole(Role.PARENT);
		
		DefaultCommander.persist(user);
		
		User child = new User();
		child.setName("Julio");
		child.setLastName("Reinaldo Barreiro");
		child.setEmail("erb@test.cl");
		child.setLogname("julior");
		child.setPassword("julior");
		child.setRole(Role.CHILD);
		child.setParent(user);
		
		DefaultCommander.persist(child);
		
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
