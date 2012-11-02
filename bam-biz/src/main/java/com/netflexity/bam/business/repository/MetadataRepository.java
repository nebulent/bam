/**
 * 
 */
package com.netflexity.bam.business.repository;

import java.util.List;

import com.netflexity.bam.business.domain.model.BpmAttribute;
import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.domain.model.BpmFlowTransaction;
import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.domain.model.BpmProcess;
import com.netflexity.bam.business.domain.model.BpmQualifier;
import com.netflexity.bam.business.domain.model.BpmStage;


/**
 * @author Alexei SCLIFOS
 *
 */
public interface MetadataRepository {

	/**
	 * @param companyId
	 * @return
	 * @throws RepositoryException
	 */
	BpmProcess getProcess(String companyId, long processId) throws RepositoryException;
	
	/**
	 * @param companyId
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmProcess> getProcesses(String companyId) throws RepositoryException;
	
	/**
	 * @param partyId
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmStage> getStages(String partyId) throws RepositoryException;

	/**
	 * @param name
	 * @return
	 * @throws RepositoryException
	 */
     List<BpmStage> getStagesByName(String name) throws RepositoryException;

	/**
	 * @param partyId
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmAttribute> getAttributes(String partyId) throws RepositoryException;
	
	/**
	 * @param ids
	 * @throws RepositoryException
	 */
	void removeProcesses(Long[] ids) throws RepositoryException;
	
	/**
	 * @param ids
	 * @throws RepositoryException
	 */
	void removeStages(Long[] ids) throws RepositoryException;
	
	/**
	 * @param ids
	 * @throws RepositoryException
	 */
	void removeAttributes(Long[] ids) throws RepositoryException;
	
	/**
	 * @param process
	 * @return
	 * @throws RepositoryException
	 */
	BpmProcess updateProcess(BpmProcess process) throws RepositoryException;
	
	/**
	 * @param stage
	 * @return
	 * @throws RepositoryException
	 */
	BpmStage updateStage(BpmStage stage) throws RepositoryException;
	
	/**
	 * @param attribute
	 * @return
	 * @throws RepositoryException
	 */
	BpmAttribute updateAttribute(BpmAttribute attribute) throws RepositoryException;
	
	/**
	 * @param process
	 * @return
	 * @throws RepositoryException
	 */
	BpmProcess createProcess(BpmProcess process) throws RepositoryException;
	
	/**
	 * @param stage
	 * @return
	 * @throws RepositoryException
	 */
	BpmStage createStage(BpmStage stage) throws RepositoryException;
	
	/**
	 * @param attribute
	 * @return
	 * @throws RepositoryException
	 */
	BpmAttribute createAttribute(BpmAttribute attribute) throws RepositoryException;
	
	/**
	 * @param monitor
	 * @return
	 * @throws RepositoryException
	 */
	BpmMonitor createMonitor(BpmMonitor monitor) throws RepositoryException;

	/**
	 * @param qualifier
	 * @return
	 * @throws RepositoryException
	 */
	BpmQualifier createQualifier(BpmQualifier qualifier) throws RepositoryException;
	
	/**
	 * @param uuid
	 * @return
	 */
	BpmFlow findProcessFlowByUuid(String uuid);
	
	/**
	 * @param transaction
	 * @return
	 */
	BpmFlowTransaction createFlowTransaction(BpmFlowTransaction transaction);

}



































