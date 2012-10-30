/**
 * 
 */
package com.netflexity.bam.monitor.service;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface EventProcessor<REQUEST> {

	/**
	 * @param request
	 */
	void process(REQUEST request);
}
