/**
 * 
 */
package com.antilia.letsplay.crud;

import org.apache.wicket.Component;

import com.antilia.letsplay.domain.DImage;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.IColumnRenderer;
import com.antilia.web.crud.AutoCrudStyler;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ImagesPage extends CRUDPage<DImage> implements IProtectedPage  {

	public ImagesPage() {
		super(DImage.class);
		AutoCrudStyler<DImage> crudStyler = new AutoCrudStyler<DImage>(DImage.class);
		crudStyler.addRenderer(new IColumnRenderer<DImage>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getPropertyPath() {
				return "image";
			}
			
			@Override
			public Component newTableCell(String id, 
					IColumnModel<DImage> columnModel, DImage object) {
				return new ImagePanel(id, object);
			}
		});
		setCrudStyler(crudStyler);
	}
}
