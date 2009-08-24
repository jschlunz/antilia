/**
 * 
 */
package com.antilia.jsp.component;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IUrlGenerator extends Serializable {
	
	String generateUrlFor(IActionListener listener);

}
