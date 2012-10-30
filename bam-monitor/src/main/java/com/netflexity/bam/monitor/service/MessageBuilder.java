/**
 * 
 */
package com.netflexity.bam.monitor.service;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface MessageBuilder<RESPONSE, REQUEST> {

	/**
	 * @param request
	 * @return
	 */
	RESPONSE build(REQUEST request);
}
