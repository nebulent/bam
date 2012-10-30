package com.netflexity.bam.monitor.esper.event;

public class BamEvent {

    private Long id;
    private String transactionUuid;
    private String stageTypeCode;
    private String processName;
    private String partyId;
    private Long transactionDate;

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
     * @return the transactionUuid
     */
    public String getTransactionUuid() {
        return transactionUuid;
    }

    /**
     * @param transactionUuid the transactionUuid to set
     */
    public void setTransactionUuid(String transactionUuid) {
        this.transactionUuid = transactionUuid;
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
     * @return the processName
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * @param processName the processName to set
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * @return the partyId
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * @param partyId the partyId to set
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
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

    
}
