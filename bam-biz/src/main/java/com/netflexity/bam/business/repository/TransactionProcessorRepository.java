/**
 * 
 */
package com.netflexity.bam.business.repository;

import java.util.List;

import com.netflexity.bam.business.domain.model.BpmTransaction;
import com.netflexity.bam.business.domain.model.BpmTransactionSummary;


/**
 * @author Alexei SCLIFOS
 *
 */
public interface TransactionProcessorRepository {

	/**
	 * @param partyId
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmTransactionSummary> getTransactionSummary(String partyId) throws RepositoryException;
	
	/**
	 * @param limit
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmTransaction> getTransactions(int limit) throws RepositoryException;

	/**
	 * @param uuid
	 * @return
	 */
	BpmTransaction findProcessTransactionByUuid(String uuid);
	
	/**
	 * @param transaction
	 * @return
	 */
	BpmTransaction createTransaction(BpmTransaction transaction);
}
