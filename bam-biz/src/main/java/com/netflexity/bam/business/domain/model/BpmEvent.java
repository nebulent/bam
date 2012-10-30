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
@Entity(name = "bpm_event")
public class BpmEvent implements Serializable {

     /**/
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monitor", nullable = false)
    private BpmMonitor bpmMonitor;
    
    @Column(name = "event_status_code")
    private String eventStatusCode;
    
    @Column(name = "start_time")
    private long startTime;
    
    @Column(name = "end_time")
    private Long endTime;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "changeDate")
    private Long changeDate;
    
    @Column(name = "changeBy")
    private String changeBy;

    /**
     * 
     */
    public BpmEvent() {
    }
	
    /**
     * @param bpmMonitor
     * @param eventStatusCode
     * @param startTime
     */
    public BpmEvent(BpmMonitor bpmMonitor, String eventStatusCode, long startTime) {
        this.bpmMonitor = bpmMonitor;
        this.eventStatusCode = eventStatusCode;
        this.startTime = startTime;
    }
    
    /**
     * @param bpmMonitor
     * @param eventStatusCode
     * @param startTime
     * @param endTime
     * @param message
     * @param changeDate
     * @param changeBy
     */
    public BpmEvent(BpmMonitor bpmMonitor, String eventStatusCode, long startTime, Long endTime, String message, Long changeDate, String changeBy) {
       this.bpmMonitor = bpmMonitor;
       this.eventStatusCode = eventStatusCode;
       this.startTime = startTime;
       this.endTime = endTime;
       this.message = message;
       this.changeDate = changeDate;
       this.changeBy = changeBy;
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
	 * @return the bpmMonitor
	 */
	public BpmMonitor getBpmMonitor() {
		return bpmMonitor;
	}

	/**
	 * @param bpmMonitor the bpmMonitor to set
	 */
	public void setBpmMonitor(BpmMonitor bpmMonitor) {
		this.bpmMonitor = bpmMonitor;
	}

	/**
	 * @return the eventStatusCode
	 */
	public String getEventStatusCode() {
		return eventStatusCode;
	}

	/**
	 * @param eventStatusCode the eventStatusCode to set
	 */
	public void setEventStatusCode(String eventStatusCode) {
		this.eventStatusCode = eventStatusCode;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
   
}


