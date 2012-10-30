package com.netflexity.bam.plugin.sqsflow;

public class SqsFlowMapping {

	String ownerUUID;
	
	String queueName;
	
	String flowUUID;

	/**
	 * @return the ownerId
	 */
	public String getOwnerUUID() {
		return ownerUUID;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return the flowUUID
	 */
	public String getFlowUUID() {
		return flowUUID;
	}

	/**
	 * @param flowUUID the flowUUID to set
	 */
	public void setFlowUUID(String flowUUID) {
		this.flowUUID = flowUUID;
	}

}
