package com.netflexitysolutions.software.bam.ui

import java.util.List;

class Transaction {

	static constraints = {
	}

	Long id;
	String uuid;

	Long processId;
	String processName;
	Long startDate;
	Long endDate;
	String transactionStatusCode;

	List<FlowTransaction> bpmFlowTransactions;
}
