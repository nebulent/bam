package com.netflexity.bam.business.domain.model;
// Generated Jun 3, 2010 4:25:54 PM by Hibernate Tools 3.2.1.GA

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
import javax.persistence.OneToMany;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_transaction")
public class BpmTransaction implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
	public static final String STARTED = "STARTED";
    public static final String STOPED = "STOPED";
    
    public static final String ERROR = "ERROR";
    public static final String HEALTHY = "HEALTHY";
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "uuid", nullable = false, length = 48)
    private String uuid;
    
    @Column(name = "start_date", nullable = false)
    private long startDate;
    
    @Column(name = "end_date")
    private Long endDate;
    
    @Column(name = "transaction_status_code", length = 32)
    private String transactionStatusCode;
    
    @Column(name = "health_code", length = 32)
    private String healthCode = "HEALTHY";
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmTransaction")
    private Set<BpmFlowTransaction> bpmFlowTransactions = new HashSet<BpmFlowTransaction>(0);

    /**
     * 
     */
    public BpmTransaction() {
    }

    /**
     * @param uuid
     * @param startDate
     */
    public BpmTransaction(String uuid, long startDate) {
        this.uuid = uuid;
        this.startDate = startDate;
    }

    /**
     * @param uuid
     * @param startDate
     * @param endDate
     * @param transactionStatusCode
     * @param bpmFlowTransactions
     */
    public BpmTransaction(String uuid, long startDate, Long endDate, String transactionStatusCode, Set<BpmFlowTransaction> bpmFlowTransactions) {
        this.uuid = uuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactionStatusCode = transactionStatusCode;
        this.bpmFlowTransactions = bpmFlowTransactions;
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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the startDate
	 */
	public long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the transactionStatusCode
	 */
	public String getTransactionStatusCode() {
		return transactionStatusCode;
	}

	/**
	 * @param transactionStatusCode the transactionStatusCode to set
	 */
	public void setTransactionStatusCode(String transactionStatusCode) {
		this.transactionStatusCode = transactionStatusCode;
	}

	/**
	 * @return
	 */
	public String getHealthCode() {
		return healthCode;
	}

	/**
	 * @param healthCode
	 */
	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	/**
	 * @return the bpmFlowTransactions
	 */
	public Set<BpmFlowTransaction> getBpmFlowTransactions() {
		return bpmFlowTransactions;
	}

	/**
	 * @param bpmFlowTransactions the bpmFlowTransactions to set
	 */
	public void setBpmFlowTransactions(Set<BpmFlowTransaction> bpmFlowTransactions) {
		this.bpmFlowTransactions = bpmFlowTransactions;
	}

}


