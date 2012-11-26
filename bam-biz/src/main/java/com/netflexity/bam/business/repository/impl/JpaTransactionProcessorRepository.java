/**
 * 
 */
package com.netflexity.bam.business.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import netflexity.schema.software.bam.messages._1.GetTransactions;

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
    private static final String TRANSACTION_STATUS_CODE = "TRANSACTION_STATUS_CODE";
    private static final String HEALTH_CODE = "HEALTH_CODE";
	
    @SuppressWarnings("unchecked")
	public List<BpmTransaction> getTransactions() throws RepositoryException {
    	String SQL = "FROM com.netflexity.bam.business.domain.model.BpmTransaction process ORDER BY startDate DESC";
    	return entityManager.createQuery(SQL).getResultList();
    }
    
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
	
	@SuppressWarnings("unchecked")
	public List<BpmTransaction> getTransactions(int pageNumber, int pageSize) throws RepositoryException {
		String SQL = "FROM com.netflexity.bam.business.domain.model.BpmTransaction process ORDER BY startDate DESC";
		return entityManager.createQuery(SQL).setFirstResult(pageNumber * pageSize).setMaxResults(pageSize).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.TransactionProcessorRepository#getTransactions(netflexity.schema.software.bam.messages._1.GetTransactions)
	 */
	@SuppressWarnings("unchecked")
	public GetTransactionsResponse getTransactions(GetTransactions body) throws RepositoryException {
		boolean flag = false;
		List<BpmTransaction> transactions = new ArrayList<BpmTransaction>();
		Query query;
		String SQL = "FROM com.netflexity.bam.business.domain.model.BpmTransaction transaction ";
		if (StringUtils.isNotBlank(body.getQuery())) {
			SQL += "WHERE transaction.uuid LIKE :UUID ";
			flag = true;
		}
		if (StringUtils.isNotBlank(body.getTransactionStatusCode())) {
			if (flag == false) {
				SQL += "WHERE transaction.transactionStatusCode = :TRANSACTION_STATUS_CODE ";
			}
			else {
				SQL += "AND transaction.transactionStatusCode = :TRANSACTION_STATUS_CODE ";
			}
		}
		if (StringUtils.isNotBlank(body.getHealthCode())) {
			if (flag == false) {
				SQL += "WHERE transaction.healthCode = :HEALTH_CODE ";
			}
			else {
				SQL += "AND transaction.healthCode = :HEALTH_CODE ";
			}
		}
		SQL += "ORDER BY startDate DESC";
		query = entityManager.createQuery(SQL);
		if (StringUtils.isNotBlank(body.getQuery())) {
			query.setParameter(UUID, "%" + body.getQuery() + "%");
		}
		if (StringUtils.isNotBlank(body.getTransactionStatusCode())) {
			query.setParameter(TRANSACTION_STATUS_CODE, body.getTransactionStatusCode());
		}
		if (StringUtils.isNotBlank(body.getHealthCode())) {
			query.setParameter(HEALTH_CODE, body.getHealthCode());
		}
		if (body.getLimit() != null) {
			query.setMaxResults(body.getLimit().intValue());
		}
		Integer totalTransactions = (Integer)entityManager.createQuery("SELECT count(transaction) " + SQL).getSingleResult();
		if (body.getPageNumber() != null && body.getPageSize() != null) {
			query.setFirstResult(body.getPageNumber() * body.getPageSize());
			query.setMaxResults(body.getPageSize());
		}
		transactions = query.getResultList();
		return new GetTransactionsResponse(transactions, totalTransactions);
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














































