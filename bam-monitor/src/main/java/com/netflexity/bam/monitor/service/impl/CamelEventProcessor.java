/**
 * 
 */
package com.netflexity.bam.monitor.service.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import com.netflexity.bam.monitor.service.EventProcessor;

/**
 * @author Alexei SCLIFOS
 *
 */
public class CamelEventProcessor implements EventProcessor<String> {

	/*properties*/
	private ProducerTemplate producer;
	private String destination;
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.service.EventProcessor#process(java.lang.Object)
	 */
	public void process(String request) {
		producer.sendBody(destination, request);
	}

	/**
	 * @param camelContext
	 */
	public void setCamelContext(CamelContext camelContext) {
		this.producer = camelContext.createProducerTemplate();
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
