package com.netflexity.bam.business.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_flow_transaction")
public class BpmFlowTransaction implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id", nullable = false)
    private BpmFlow bpmFlow;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private BpmTransaction bpmTransaction;
    
    @Column(name = "transaction_date")
    private Long transactionDate;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmFlowTransaction")    
    private Set<BpmFlowTransactionPayload> bpmFlowTransactionPayloads = new HashSet<BpmFlowTransactionPayload>(0);

    /**
     * 
     */
    public BpmFlowTransaction() {
    }
	
    /**
     * @param bpmFlow
     * @param bpmTransaction
     */
    public BpmFlowTransaction(BpmFlow bpmFlow, BpmTransaction bpmTransaction) {
        this.bpmFlow = bpmFlow;
        this.bpmTransaction = bpmTransaction;
    }
    
    /**
     * @param bpmFlow
     * @param bpmTransaction
     * @param transactionDate
     * @param bpmFlowTransactionPayloads
     */
    public BpmFlowTransaction(BpmFlow bpmFlow, BpmTransaction bpmTransaction, Long transactionDate, Set<BpmFlowTransactionPayload> bpmFlowTransactionPayloads) {
       this.bpmFlow = bpmFlow;
       this.bpmTransaction = bpmTransaction;
       this.transactionDate = transactionDate;
       this.bpmFlowTransactionPayloads = bpmFlowTransactionPayloads;
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the bpmFlow
	 */
	public BpmFlow getBpmFlow() {
		return bpmFlow;
	}

	/**
	 * @param bpmFlow the bpmFlow to set
	 */
	public void setBpmFlow(BpmFlow bpmFlow) {
		this.bpmFlow = bpmFlow;
	}

	/**
	 * @return the bpmTransaction
	 */
	public BpmTransaction getBpmTransaction() {
		return bpmTransaction;
	}

	/**
	 * @param bpmTransaction the bpmTransaction to set
	 */
	public void setBpmTransaction(BpmTransaction bpmTransaction) {
		this.bpmTransaction = bpmTransaction;
	}

	/**
	 * @return the transactionDate
	 */
	public Long getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Long transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the bpmFlowTransactionPayloads
	 */
	public Set<BpmFlowTransactionPayload> getBpmFlowTransactionPayloads() {
		return bpmFlowTransactionPayloads;
	}

	/**
	 * @param bpmFlowTransactionPayloads the bpmFlowTransactionPayloads to set
	 */
	public void setBpmFlowTransactionPayloads(Set<BpmFlowTransactionPayload> bpmFlowTransactionPayloads) {
		this.bpmFlowTransactionPayloads = bpmFlowTransactionPayloads;
	}
   
}

