/**
 * 
 */
package com.netflexity.bam.monitor.executor;

import java.io.Serializable;

import com.netflexity.bam.business.domain.model.BpmMonitor;

/**
 * @author Alexei SCLIFOS
 *
 */
public class MonitorExecutorInput implements Serializable {

	/**/
	private static final long serialVersionUID = 1L;
	
	/*properties*/
	private BpmMonitor monitor;
	private Object monitorType;

	/**
	 * 
	 */
	public MonitorExecutorInput() {
	}
	
	/**
	 * @param monitor
	 * @param monitorType
	 */
	public MonitorExecutorInput(BpmMonitor monitor, Object monitorType) {
		this.monitor = monitor;
		this.monitorType = monitorType;
	}

	/**
	 * @return the monitor
	 */
	public BpmMonitor getMonitor() {
		return monitor;
	}
	/**
	 * @param monitor the monitor to set
	 */
	public void setMonitor(BpmMonitor monitor) {
		this.monitor = monitor;
	}
	/**
	 * @return the monitorType
	 */
	public Object getMonitorType() {
		return monitorType;
	}
	/**
	 * @param monitorType the monitorType to set
	 */
	public void setMonitorType(Object monitorType) {
		this.monitorType = monitorType;
	}

}
