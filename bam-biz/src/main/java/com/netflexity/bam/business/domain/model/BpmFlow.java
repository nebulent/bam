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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_flow")
public class BpmFlow implements Serializable {

     /**/
	private static final long serialVersionUID = 1L;
	
    @Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="transaction_qualifier_id", nullable = true)
    private BpmQualifier bpmQualifier;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="stage_id", nullable=false)
    private BpmStage bpmStage;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="process_id", nullable=false)
    private BpmProcess bpmProcess;
    
    @Column(name="uuid", nullable=false, length=48)
    private String uuid;
    
    @Column(name="stage_type_code", nullable=false, length=32)
    private String stageTypeCode;
    
    @Column(name="store_message_payload", nullable=false, length=1)
    private char storeMessagePayload;
    
    @Column(name="change_date")
    private Long changeDate;
    
    @Column(name="change_by", length=32)
    private String changeBy;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="bpmFlow")
    private Set<BpmFlowTransaction> bpmFlowTransactions = new HashSet<BpmFlowTransaction>(0);
    
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_flow_binding", joinColumns = {@JoinColumn(name = "flow", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "binding", nullable = false)})	
    private Set<BpmBinding> bindings = new HashSet<BpmBinding>();


    /**
     * 
     */
    public BpmFlow() {
    }
	
    /**
     * @param bpmStage
     * @param bpmProcess
     * @param uuid
     * @param stageTypeCode
     * @param storeMessagePayload
     */
    public BpmFlow(BpmStage bpmStage, BpmProcess bpmProcess, String uuid, String stageTypeCode, char storeMessagePayload) {
    	this.bpmStage = bpmStage;
        this.bpmProcess = bpmProcess;
        this.uuid = uuid;
        this.stageTypeCode = stageTypeCode;
        this.storeMessagePayload = storeMessagePayload;
    }
    
    /**
     * @param bpmQualifier
     * @param bpmStage
     * @param bpmProcess
     * @param uuid
     * @param stageTypeCode
     * @param storeMessagePayload
     * @param changeDate
     * @param changeBy
     * @param bpmFlowTransactions
     */
    public BpmFlow(BpmQualifier bpmQualifier, BpmStage bpmStage, BpmProcess bpmProcess, String uuid, String stageTypeCode, char storeMessagePayload, Long changeDate, String changeBy, Set<BpmFlowTransaction> bpmFlowTransactions) {
       this.bpmQualifier = bpmQualifier;
       this.bpmStage = bpmStage;
       this.bpmProcess = bpmProcess;
       this.uuid = uuid;
       this.stageTypeCode = stageTypeCode;
       this.storeMessagePayload = storeMessagePayload;
       this.changeDate = changeDate;
       this.changeBy = changeBy;
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
	 * @return the bpmQualifier
	 */
	public BpmQualifier getBpmQualifier() {
		return bpmQualifier;
	}

	/**
	 * @param bpmQualifier the bpmQualifier to set
	 */
	public void setBpmQualifier(BpmQualifier bpmQualifier) {
		this.bpmQualifier = bpmQualifier;
	}

	/**
	 * @return the bpmStage
	 */
	public BpmStage getBpmStage() {
		return bpmStage;
	}

	/**
	 * @param bpmStage the bpmStage to set
	 */
	public void setBpmStage(BpmStage bpmStage) {
		this.bpmStage = bpmStage;
	}

	/**
	 * @return the bpmProcess
	 */
	public BpmProcess getBpmProcess() {
		return bpmProcess;
	}

	/**
	 * @param bpmProcess the bpmProcess to set
	 */
	public void setBpmProcess(BpmProcess bpmProcess) {
		this.bpmProcess = bpmProcess;
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
	 * @return the stageTypeCode
	 */
	public String getStageTypeCode() {
		return stageTypeCode;
	}

	/**
	 * @param stageTypeCode the stageTypeCode to set
	 */
	public void setStageTypeCode(String stageTypeCode) {
		this.stageTypeCode = stageTypeCode;
	}

	/**
	 * @return the storeMessagePayload
	 */
	public char getStoreMessagePayload() {
		return storeMessagePayload;
	}

	/**
	 * @param storeMessagePayload the storeMessagePayload to set
	 */
	public void setStoreMessagePayload(char storeMessagePayload) {
		this.storeMessagePayload = storeMessagePayload;
	}

	/**
	 * @return the changeDate
	 */
	public Long getChangeDate() {
		return changeDate;
	}

	/**
	 * @param changeDate the changeDate to set
	 */
	public void setChangeDate(Long changeDate) {
		this.changeDate = changeDate;
	}

	/**
	 * @return the changeBy
	 */
	public String getChangeBy() {
		return changeBy;
	}

	/**
	 * @param changeBy the changeBy to set
	 */
	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
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

	/**
	 * @return the bindings
	 */
	public Set<BpmBinding> getBindings() {
		return bindings;
	}

	/**
	 * @param bindings the bindings to set
	 */
	public void setBindings(Set<BpmBinding> bindings) {
		this.bindings = bindings;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BpmFlow other = (BpmFlow) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
   

}


