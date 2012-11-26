/**
 * 
 */
package com.netflexity.bam.business.repository;

import java.util.List;

import netflexity.schema.software.bam.messages._1.GetTransactions;

import com.netflexity.bam.business.domain.model.BpmTransaction;
import com.netflexity.bam.business.domain.model.BpmTransactionSummary;


/**
 * @author Alexei SCLIFOS
 *
 */
public interface TransactionProcessorRepository {

	public class GetTransactionsResponse {
		
		private List<BpmTransaction> transactions;
		private int totalTransactions;
		
		public GetTransactionsResponse(List<BpmTransaction> transactions, int totalTransactions) {
			this.transactions = transactions;
			this.totalTransactions = totalTransactions;
		}
		
		public List<BpmTransaction> getTransactions() {
			return transactions;
		}
		
		public void setTransactions(List<BpmTransaction> transactions) {
			this.transactions = transactions;
		}
		
		public int getTotalTransactions() {
			return totalTransactions;
		}
		
		public void setTotalTransactions(int totalTransactions) {
			this.totalTransactions = totalTransactions;
		}
	}
	/**
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmTransaction> getTransactions() throws RepositoryException;
	
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
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmTransaction> getTransactions(int pageNumber, int pageSize) throws RepositoryException;
	
	/**
	 * @param body
	 * @return
	 * @throws RepositoryException
	 */
	GetTransactionsResponse getTransactions(GetTransactions body) throws RepositoryException;

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
