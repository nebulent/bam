/**
 * 
 */
package com.netflexity.bam.monitor.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import netflexity.schema.software.bam.messages._1.StartMonitor;
import netflexity.schema.software.bam.messages._1.StartMonitorResponse;
import netflexity.ws.software.bam.services._1_0.BamMonitorService;

import com.netflexity.bam.business.utils.DomainUtil;
import com.netflexity.bam.monitor.engine.MonitorEngine;

/**
 * @author Alexei SCLIFOS
 *
 */
@Transactional
public class BamMonitorServiceImpl implements BamMonitorService {

	/*services*/
	private MonitorEngine monitorEngine;
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BamMonitorService#startMonitor(netflexity.schema.software.bam.messages._1.StartMonitor)
	 */
	public StartMonitorResponse startMonitor(StartMonitor body) {
		Assert.notNull(body);
		Assert.notNull(body.getMonitor());
		StartMonitorResponse response = new StartMonitorResponse();
		monitorEngine.execute(DomainUtil.toDomainType(body.getMonitor()));
		response.setResponse(true);
		return response;
	}

	/**
	 * @param monitorEngine the monitorEngine to set
	 */
	public void setMonitorEngine(MonitorEngine monitorEngine) {
		this.monitorEngine = monitorEngine;
	}
}
