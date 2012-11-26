package com.netflexitysolutions.software.bam.ui

import java.util.List;

class Transaction {

	static constraints = {
		uuid()
		processName()
		startDate()
		endDate()
		transactionStatusCode()
		healthCode()
	}

	String uuid;
	String processName;
	Calendar startDate;
	Calendar endDate;
	String transactionStatusCode;
	String healthCode

	List<FlowTransaction> flowTransactions;
}
