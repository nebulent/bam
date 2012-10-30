/**
 * 
 */
package com.netflexity.bam.monitor.executor;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface MonitorExecutor<INPUT> {

	/**
	 * @param input
	 */
	void execute(INPUT input);
}
