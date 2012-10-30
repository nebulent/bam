/**
 * 
 */
package com.netflexity.bam.monitor.dispatcher;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface MonitorDispatcher<INPUT> {

	/**
	 * @param input
	 */
	void dispatch(INPUT input);
}
