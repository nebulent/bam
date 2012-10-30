/**
 * 
 */
package com.netflexity.bam.monitor.engine;

import com.netflexity.bam.business.domain.model.BpmMonitor;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface MonitorEngine {

	/**
	 * 
	 */
	void execute();
	
	/**
	 * @param monitor
	 */
	void execute(BpmMonitor monitor);
}
