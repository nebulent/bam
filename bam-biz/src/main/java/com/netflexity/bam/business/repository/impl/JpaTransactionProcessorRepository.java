/**
 * 
 */
package com.netflexity.bam.business.repository.impl;

import java.util.List;

import com.netflexity.bam.business.domain.model.BpmTransaction;
import com.netflexity.bam.business.domain.model.BpmTransactionSummary;
import com.netflexity.bam.business.repository.RepositoryException;
import com.netflexity.bam.business.repository.TransactionProcessorRepository;

/**
 * @author Alexei SCLIFOS
 *
 */
public class JpaTransactionProcessorRepository extends JpaAbstractRepository implements TransactionProcessorRepository {

    /*constants*/
    private static final String UUID = "UUID";
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.TransactionProcessorRepository#getTransactions(int)
	 */
	@SuppressWarnings("unchecked")
	public List<BpmTransaction> getTransactions(int limit) throws RepositoryException {
		String SQL = "FROM com.netflexity.bam.business.domain.model.BpmTransaction process ORDER BY startDate DESC";
		if(limit > 0) {
			return entityManager.createQuery(SQL).setMaxResults(limit).getResultList();
		}
		return entityManager.createQuery(SQL).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.TransactionProcessorRepository#getTransactionSummary(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<BpmTransactionSummary> getTransactionSummary(String partyId) throws RepositoryException {
		try {
			String SQL = "SELECT  new com.netflexity.bam.business.domain.model.BpmTransactionSummary(process.id, process.name, COUNT(flowTransaction.bpmTransaction.id)) " +
					     "FROM com.netflexity.bam.business.domain.model.BpmProcess process " +
					     "LEFT JOIN FETCH process.bpmFlows flow " +
					     "LEFT JOIN FETCH flow.bpmFlowTransactions flowTransaction";
			return entityManager.createQuery(SQL).getResultList();
		} catch (RuntimeException exc) {
			throw new RepositoryException(exc);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.TransactionProcessorRepository#findProcessTransactionByUuid(java.lang.String)
	 */
	public BpmTransaction findProcessTransactionByUuid(String uuid) {
		
		List result = entityManager.createQuery("FROM com.netflexity.bam.business.domain.model.BpmTransaction WHERE uuid = :UUID").setParameter(UUID, uuid).getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return (BpmTransaction)result.get(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.TransactionProcessorRepository#insertProcessTransaction(com.netflexity.bam.business.domain.model.BpmTransaction)
	 */
	public BpmTransaction createTransaction(BpmTransaction transaction) {
		return (BpmTransaction)save(transaction);
	}
	
}














































