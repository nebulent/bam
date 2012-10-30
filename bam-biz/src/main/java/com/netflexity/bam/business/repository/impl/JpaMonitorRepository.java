/**
 * 
 */
package com.netflexity.bam.business.repository.impl;

import java.util.List;

import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.repository.MonitorRepository;

/**
 * @author Alexei SCLIFOS
 *
 */
public class JpaMonitorRepository extends JpaAbstractRepository implements MonitorRepository {

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.MonitorRepository#getAllMonitors()
	 */
	@SuppressWarnings("unchecked")
	public List<BpmMonitor> getAllMonitors() {
		return entityManager.createQuery("FROM com.netflexity.bam.business.domain.model.BpmMonitor monitor JOIN FETCH monitor.bpmConditions").getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.MonitorRepository#getMonitorById(long)
	 */
	public BpmMonitor getMonitorById(long id) {
		return entityManager.find(BpmMonitor.class, id);
	}
}
