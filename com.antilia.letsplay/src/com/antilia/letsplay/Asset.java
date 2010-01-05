package com.antilia.letsplay;

import java.io.Serializable;

public class Asset implements Serializable {

	private String audit_train;

	/**
	 * @return the audit_train
	 */
	public String getAudit_train() {
		return audit_train;
	}

	/**
	 * @param audit_train the audit_train to set
	 */
	public void setAudit_train(String audit_train) {
		this.audit_train = audit_train;
	}
}
