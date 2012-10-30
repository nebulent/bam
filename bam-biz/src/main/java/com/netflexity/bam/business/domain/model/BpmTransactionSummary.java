package com.netflexity.bam.business.domain.model;

import java.io.Serializable;

public class BpmTransactionSummary implements Serializable {

	/**/
	private static final long serialVersionUID = 1L;
	
	/*properties*/
    private long processId;
    private String name;
    private int total;
    
    /**
     * 
     */
    public BpmTransactionSummary() {
	}
    
    /**
     * @param processId
     * @param name
     * @param total
     */
    public BpmTransactionSummary(long processId, String name, int total) {
    	this.processId = processId;
    	this.name = name;
    	this.total = total;
	}

    /**
     * @return the processId
     */
    public long getProcessId() {
        return processId;
    }

    /**
     * @param processId the processId to set
     */
    public void setProcessId(long processId) {
        this.processId = processId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }


}
