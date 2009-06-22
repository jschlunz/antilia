/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 *  Represents an strategy for matching strings using "like" operator.
 *  
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public enum MatchMode {
	
	EXACT {
		@Override
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	START {
		@Override
		public String toMatchString(String pattern) {
			return pattern + '%';
		}		
	},
	END {
		@Override
		public String toMatchString(String pattern) {
			return  '%' + pattern;
		}
	},
	ANYWHERE {
		@Override
		public String toMatchString(String pattern) {
			return  '%' + pattern + '%';
		}
	};
	
	public abstract String toMatchString(String pattern);

}
