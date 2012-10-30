package com.netflexity.bam.business.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_flow_transaction_payload")
public class BpmFlowTransactionPayload implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_transaction_id", nullable = false)
    private BpmFlowTransaction bpmFlowTransaction;
    
    @Column(name = "payload", nullable = false)
    private byte[] payload;

    /**
     * 
     */
    public BpmFlowTransactionPayload() {
    }

    /**
     * @param bpmFlowTransaction
     * @param payload
     */
    public BpmFlowTransactionPayload(BpmFlowTransaction bpmFlowTransaction, byte[] payload) {
       this.bpmFlowTransaction = bpmFlowTransaction;
       this.payload = payload;
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
	 * @return the bpmFlowTransaction
	 */
	public BpmFlowTransaction getBpmFlowTransaction() {
		return bpmFlowTransaction;
	}

	/**
	 * @param bpmFlowTransaction the bpmFlowTransaction to set
	 */
	public void setBpmFlowTransaction(BpmFlowTransaction bpmFlowTransaction) {
		this.bpmFlowTransaction = bpmFlowTransaction;
	}

	/**
	 * @return the payload
	 */
	public byte[] getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
   
}


