/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export;

import com.antilia.export.excel.ExportExcelButton;
import com.antilia.export.pdf.ExportPdfButton;
import com.antilia.web.button.IMenuFactoryPopulator;
import com.antilia.web.button.MenuItemsFactory;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPopulator implements IMenuFactoryPopulator {

	/* (non-Javadoc)
	 * @see com.antilia.web.button.IMenuFactoryPopulator#populateMenuFactory(com.antilia.web.button.MenuItemsFactory)
	 */
	@Override
	public void populateMenuFactory(MenuItemsFactory factory) {
		factory.addItem(new ExportPdfButton("exportPDF"));
		factory.addItem(new ExportExcelButton("exportExcel"));
	}

}
