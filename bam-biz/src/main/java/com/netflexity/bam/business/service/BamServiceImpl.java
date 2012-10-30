/**
 * 
 */
package com.netflexity.bam.business.service;

import netflexity.schema.software.bam.messages._1.AcknowledgeTransactionTracking;
import netflexity.schema.software.bam.messages._1.ProcessTransactionTracking;
import netflexity.ws.software.bam.services._1_0.BAM;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

/**
 * @author Alexei SCLIFOS
 *
 */
public class BamServiceImpl implements BAM {

	/*properties*/
	private ProducerTemplate producer;
	private String qosDestination;
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAM#processTransactionTracking(netflexity.schema.software.bam.messages._1.ProcessTransactionTracking)
	 */
	public AcknowledgeTransactionTracking processTransactionTracking(ProcessTransactionTracking body) {
		producer.sendBody(qosDestination, body);
		AcknowledgeTransactionTracking response = new AcknowledgeTransactionTracking();
		response.setFlowUuid(body.getFlowUuid());
		response.setTransactionUuid(body.getTransactionUuid());
		return response;
	}

	/**
	 * @param qosDestination the qosDestination to set
	 */
	public void setQosDestination(String qosDestination) {
		this.qosDestination = qosDestination;
	}
	
	/**
	 * @param camelContext
	 */
	public void setCamelContext(CamelContext camelContext) {
		this.producer = camelContext.createProducerTemplate();
	}
	
}
