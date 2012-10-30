/**
 * 
 */
package com.netflexity.bam.business.repository;

import java.util.List;

import com.netflexity.bam.business.domain.model.BpmMonitor;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface MonitorRepository {

	/**
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmMonitor> getAllMonitors() throws RepositoryException;
	
	/**
	 * @param id
	 * @return
	 */
	BpmMonitor getMonitorById(long id);
}
