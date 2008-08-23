/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import com.antilia.demo.manager.entities.Address;
import com.antilia.web.crud.CrudStyler;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AddressCRUDPanel extends ManagerCRUD<Address> {

	private static final long serialVersionUID = 1L;

	public AddressCRUDPanel(String id) {
		super(id, 
				new CrudStyler<Address>(Address.class).
				addEditFields("city", "address1", "address2", "address3", "zipcode").
				addTableColumns("address1", "zipcode","city").
				addSearchFields( "address1", "address2", "address3", "zipcode","city")
		);
	}
	
}
