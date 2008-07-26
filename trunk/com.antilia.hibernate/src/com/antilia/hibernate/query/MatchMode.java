/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;


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
		
		public org.hibernate.criterion.MatchMode toMatchMode() {
			return org.hibernate.criterion.MatchMode.EXACT;
		}
	},
	START {
		@Override
		public String toMatchString(String pattern) {
			return pattern + '%';
		}
		
		public org.hibernate.criterion.MatchMode toMatchMode() {
			return org.hibernate.criterion.MatchMode.START;
		}
		
	},
	END {
		@Override
		public String toMatchString(String pattern) {
			return  '%' + pattern;
		}
		
		public org.hibernate.criterion.MatchMode toMatchMode() {
			return org.hibernate.criterion.MatchMode.END;
		}
	},
	ANYWHERE {
		@Override
		public String toMatchString(String pattern) {
			return  '%' + pattern + '%';
		}
		
		public org.hibernate.criterion.MatchMode toMatchMode() {
			return org.hibernate.criterion.MatchMode.ANYWHERE;
		}
	};
	
	public abstract String toMatchString(String pattern);
	
	public abstract org.hibernate.criterion.MatchMode toMatchMode();
}
