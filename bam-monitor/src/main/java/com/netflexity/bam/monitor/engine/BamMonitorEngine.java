/**
 * 
 */
package com.netflexity.bam.monitor.engine;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.repository.MonitorRepository;
import com.netflexity.bam.monitor.dispatcher.MonitorDispatcher;

/**
 * @author Alexei SCLIFOS
 *
 */
public class BamMonitorEngine implements MonitorEngine {

	/*logger*/
	private static Logger logger = LoggerFactory.getLogger(BamMonitorEngine.class);
	
	/*repositories*/
	private MonitorRepository monitorRepository;
	
	/*dispatcher*/
	private MonitorDispatcher<BpmMonitor> monitorDispatcher;
	
	/**
	 * 
	 */
	public void init() {
		execute();
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.engine.MonitorEngine#execute()
	 */
	@Transactional
	public void execute() {
		logger.debug("Starting all monitors ...");
    	List<BpmMonitor> monitors = monitorRepository.getAllMonitors();
    	if(monitors != null) {
    		for (BpmMonitor monitor : monitors) {
    			monitorDispatcher.dispatch(monitor);
    		}
    	}
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.engine.MonitorEngine#execute(com.netflexity.bam.business.domain.model.BpmMonitor)
	 */
	public void execute(BpmMonitor monitor) {
		logger.debug("Starting monitor ...");
		Assert.notNull(monitor, "Monitor is required !!!");
		monitorDispatcher.dispatch(monitor);
	}
	
	/**
	 * @param monitorRepository the monitorRepository to set
	 */
	public void setMonitorRepository(MonitorRepository monitorRepository) {
		this.monitorRepository = monitorRepository;
	}

	/**
	 * @param monitorDispatcher the monitorDispatcher to set
	 */
	public void setMonitorDispatcher(MonitorDispatcher<BpmMonitor> monitorDispatcher) {
		this.monitorDispatcher = monitorDispatcher;
	}
}
