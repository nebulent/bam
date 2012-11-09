/**
 * 
 */
package com.netflexity.bam.business.repository.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.netflexity.bam.business.domain.model.BpmAttribute;
import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.domain.model.BpmFlowTransaction;
import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.domain.model.BpmProcess;
import com.netflexity.bam.business.domain.model.BpmQualifier;
import com.netflexity.bam.business.domain.model.BpmStage;
import com.netflexity.bam.business.repository.MetadataRepository;
import com.netflexity.bam.business.repository.RepositoryException;

/**
 * @author Alexei SCLIFOS
 *
 */
public class JpaMetadataRepository extends JpaAbstractRepository implements MetadataRepository {

    /*constants*/
    private static final String PARTY_ID = "PARTY_ID";
    private static final String NAME = "NAME";
    private static final String IDS = "IDS";
    private static final String UUID = "UUID";
    private static final String ID = "ID";

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#createAttribute(com.netflexity.bam.business.domain.model.BpmAttribute)
     */
    public BpmAttribute createAttribute(BpmAttribute attribute) throws RepositoryException {
        return (BpmAttribute) save(attribute);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#createMonitor(com.netflexity.bam.business.domain.model.BpmMonitor)
     */
    public BpmMonitor createMonitor(BpmMonitor monitor) throws RepositoryException {
        return (BpmMonitor)save(monitor);
    }
    
    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#createFlow(com.netflexity.bam.business.domain.model.BpmFlow)
     */
    public BpmFlow createFlow(BpmFlow flow) throws RepositoryException {
        if (flow.getId() != null) {
            flow.setId(null);
        }
        return (BpmFlow)save(flow);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#createProcess(com.netflexity.bam.business.domain.model.BpmProcess)
     */
    public BpmProcess createProcess(BpmProcess process) throws RepositoryException {
        if (process.getId() != null) {
            process.setId(null);
        }
        Set<BpmFlow> flows = process.getBpmFlows();
        if(flows != null) {
        	for (BpmFlow flow : flows) {
        		flow.setBpmProcess(process);
        		flow.setUuid(createUUID());
        	}
        }
        return (BpmProcess)save(process);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#createStage(com.netflexity.bam.business.domain.model.BpmStage)
     */
    public BpmStage createStage(BpmStage stage) throws RepositoryException {
    	return (BpmStage)save(stage);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#createQualifier(com.netflexity.bam.business.domain.model.BpmQualifier)
     */
    public BpmQualifier createQualifier(BpmQualifier qualifier) throws RepositoryException {
        return (BpmQualifier) save(qualifier);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getAttributes(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<BpmAttribute> getAttributes(String partyId) throws RepositoryException {
        Query query;
        if (partyId == null) {
            query = entityManager.createQuery("select attribute from com.netflexity.bam.business.domain.model.BpmAttribute attribute order by attribute.name");
        } else {
            query = entityManager.createQuery("select attribute from com.netflexity.bam.business.domain.model.BpmAttribute attribute where attribute.partyId = :PARTY_ID  order by attribute.name");
            query.setParameter(PARTY_ID, partyId);
        }
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getFlow(java.lang.String, long)
     */
    public BpmFlow getFlow(long flowId)
    		throws RepositoryException {
    	Query query;
    	query = entityManager.createQuery("select flow from com.netflexity.bam.business.domain.model.BpmFlow flow " +
        		"where flow.id = :ID");
        query.setParameter(ID, flowId);
        return (BpmFlow) query.getSingleResult();
    }
    
    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getFlows()
     */
    @SuppressWarnings("unchecked")
	public List<BpmFlow> getFlows()
    		throws RepositoryException {
    	Query query;
    	query = entityManager.createQuery("select flow from com.netflexity.bam.business.domain.model.BpmFlow flow");
    	return query.getResultList();
    }

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.MetadataRepository#getProcess(java.lang.String, long)
	 */
	@Override
	public BpmProcess getProcess(String companyId, long processId)
			throws RepositoryException {
        Query query;
        query = entityManager.createQuery("select process from com.netflexity.bam.business.domain.model.BpmProcess process " +
        		"where process.partyId = :PARTY_ID and process.id = :ID");
        query.setParameter(PARTY_ID, companyId);
        query.setParameter(ID, processId);
        return (BpmProcess) query.getSingleResult();
	}
	
    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getProcesses(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<BpmProcess> getProcesses(String partyId) throws RepositoryException {
        Query query;
        if (partyId == null) {
            query = entityManager.createQuery("select process from com.netflexity.bam.business.domain.model.BpmProcess process order by process.name");
        } else {
            query = entityManager.createQuery("select process from com.netflexity.bam.business.domain.model.BpmProcess process where process.partyId = :PARTY_ID order by process.name");
            query.setParameter(PARTY_ID, partyId);
        }
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getStage(java.lang.String, long)
     */
    public BpmStage getStage(String partyId, long stageId)
    		throws RepositoryException {
    	Query query;
        query = entityManager.createQuery("select stage from com.netflexity.bam.business.domain.model.BpmStage stage " +
        		"where stage.partyId = :PARTY_ID and stage.id = :ID");
        query.setParameter(PARTY_ID, partyId);
        query.setParameter(ID, stageId);
        return (BpmStage) query.getSingleResult();
    }
    
    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getStages(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<BpmStage> getStages(String partyId) throws RepositoryException {
        Query query;
        if (partyId == null) {
            query = entityManager.createQuery("select stage from com.netflexity.bam.business.domain.model.BpmStage stage order by stage.name");
        } else {
            query = entityManager.createQuery("select stage from com.netflexity.bam.business.domain.model.BpmStage stage where stage.partyId = :PARTY_ID order by stage.name");
            query.setParameter(PARTY_ID, partyId);
        }
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#getStagesByName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<BpmStage> getStagesByName(String name) throws RepositoryException {
        Query query = entityManager.createQuery("select stage from com.netflexity.bam.business.domain.model.BpmStage stage where stage.name like :NAME order by stage.name");
        StringBuilder likeName = new StringBuilder("%").append(name).append("%");
        query.setParameter(NAME, likeName.toString());
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#removeAttributes(long[])
     */
    @SuppressWarnings("unchecked")
	public void removeAttributes(Long[] ids) throws RepositoryException {
    	if(ids == null || ids.length == 0) {
    		return;
    	}
        Query query = entityManager.createQuery("FROM com.netflexity.bam.business.domain.model.BpmAttribute attribute WHERE attribute.id IN (:IDS)");
        query.setParameter(IDS, Arrays.asList(ids));
        List<BpmAttribute> attrs = query.getResultList();
        if(attrs != null) {
        	for (BpmAttribute attr : attrs) {
        		entityManager.remove(attr);
        	}
        }
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#removeProcesses(long[])
     */
    public void removeProcesses(Long[] ids) throws RepositoryException {
    	if(ids == null || ids.length == 0) {
    		return;
    	}
    	Query query = entityManager.createQuery("DELETE FROM com.netflexity.bam.business.domain.model.BpmFlow flow WHERE flow.bpmProcess.id IN (:IDS)");
        query.setParameter(IDS, Arrays.asList(ids));
        query.executeUpdate();
        query = entityManager.createQuery("DELETE FROM com.netflexity.bam.business.domain.model.BpmProcess process WHERE process.id IN (:IDS)");
        query.setParameter(IDS, Arrays.asList(ids));
        query.executeUpdate();
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#removeStages(long[])
     */
    @SuppressWarnings("unchecked")
	public void removeStages(Long[] ids) throws RepositoryException {
    	if(ids == null || ids.length == 0) {
    		return;
    	}
    	Query query = entityManager.createQuery("FROM com.netflexity.bam.business.domain.model.BpmStage stage WHERE stage.id IN (:IDS)");
        query.setParameter(IDS, Arrays.asList(ids));
        List<BpmStage> stages = query.getResultList();
        if(stages != null) {
        	for (BpmStage stage : stages) {
        		entityManager.remove(stage);
        	}
        }
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#updateAttribute(com.netflexity.bam.business.domain.model.BpmAttribute)
     */
    public BpmAttribute updateAttribute(BpmAttribute attribute) throws RepositoryException {
        return (BpmAttribute) merge(attribute);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#updateProcess(com.netflexity.bam.business.domain.model.BpmProcess)
     */
    @SuppressWarnings("unchecked")
	public BpmProcess updateProcess(BpmProcess processRequest) throws RepositoryException {
    	/*remove old flows*/
    	Query query = entityManager.createQuery("FROM com.netflexity.bam.business.domain.model.BpmFlow flow WHERE flow.bpmProcess.id = :ID");
    	query.setParameter(ID, processRequest.getId());
    	List<BpmFlow> flowList = query.getResultList();
    	if(flowList != null) {
    		for (BpmFlow flow : flowList) {
    			entityManager.remove(flow);
    		}
    	}
    	/*get process*/
    	BpmProcess process = entityManager.find(BpmProcess.class, processRequest.getId());
    	/*
    	Set<BpmFlow> flows = process.getBpmFlows();
    	if(flows != null) {
    		Collection<BpmFlow> tmp = new ArrayList<BpmFlow>();
    		for (BpmFlow flow : flows) {
    			tmp.add(flow);
    		}
    		flows.removeAll(tmp);
    	} else {
    		process.setBpmFlows(new HashSet<BpmFlow>());
    	}
    	*/
    	process.setBpmFlows(new HashSet<BpmFlow>());
    	Set<BpmFlow>flows = processRequest.getBpmFlows();
        if(flows != null) {
        	for (BpmFlow flow : flows) {
        		flow.setId(null);
        		flow.setBpmProcess(process);
        		if(StringUtils.isEmpty(flow.getUuid())) {
        			flow.setUuid(createUUID());
        		}
        		process.getBpmFlows().add(flow);
        	}
        }
        return (BpmProcess)update(process);
    }

    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#updateStage(com.netflexity.bam.business.domain.model.BpmStage)
     */
    public BpmStage updateStage(BpmStage stage) throws RepositoryException {
        return (BpmStage) merge(stage);
    }
    
    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#findProcessFlowByUuid(java.lang.String)
     */
    public BpmFlow findProcessFlowByUuid(String uuid) {
    	return (BpmFlow)entityManager.createQuery("FROM com.netflexity.bam.business.domain.model.BpmFlow WHERE uuid = :UUID").setParameter(UUID, uuid).getSingleResult();
    }
    
    /* (non-Javadoc)
     * @see com.netflexity.bam.business.repository.MetadataRepository#insertProcessFlowTransaction(com.netflexity.bam.business.domain.model.BpmFlowTransaction)
     */
    public BpmFlowTransaction createFlowTransaction(BpmFlowTransaction transaction) {
    	return (BpmFlowTransaction)save(transaction);
    }
}
