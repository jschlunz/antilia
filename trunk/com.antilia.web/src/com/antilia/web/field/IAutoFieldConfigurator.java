/**
 * 
 */
package com.antilia.web.field;

import java.io.Serializable;

/**
 * 
 * @author EReinaldoB
 *
 */
public interface IAutoFieldConfigurator<B extends Serializable> {
	
  void configureFieldModel(IFieldModel<B> model);

}
