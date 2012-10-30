package com.netflexity.bam.business.domain.model;

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
import javax.persistence.OneToMany;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_monitor")
public class BpmMonitor implements java.io.Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @Column(name = "resource_id")
    private long resourceId;
    
    @Column(name = "criticality_type_code")
    private String criticalityTypeCode;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "occurrence_interval")
    private Integer occurrenceInterval;
    
    @Column(name = "change_date")
    private Long changeDate;
    
    @Column(name = "change_by")
    private String changeBy;
    
    @Column(name = "status", length = 1)
    private char status;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_monitor_condition", joinColumns = {@JoinColumn(name = "monitor_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "condition_id", nullable = false)})
    private Set<BpmCondition> bpmConditions = new HashSet<BpmCondition>(0);
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_monitor_schedule", joinColumns = {@JoinColumn(name = "monitor_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "schedule_id", nullable = false)})
    private Set<BpmSchedule> bpmSchedules = new HashSet<BpmSchedule>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmMonitor", cascade = {CascadeType.ALL})
    private Set<BpmEvent> bpmEvents = new HashSet<BpmEvent>(0);

    /**
     * 
     */
    public BpmMonitor() {
    }

    /**
     * @param resourceId
     * @param criticalityTypeCode
     * @param name
     * @param message
     * @param status
     */
    public BpmMonitor(long resourceId, String criticalityTypeCode, String name, String message, char status) {
        this.resourceId = resourceId;
        this.criticalityTypeCode = criticalityTypeCode;
        this.name = name;
        this.message = message;
        this.status = status;
    }
    
    /**
     * @param resourceId
     * @param criticalityTypeCode
     * @param name
     * @param message
     * @param occurrenceInterval
     * @param changeDate
     * @param changeBy
     * @param status
     * @param bpmConditions
     * @param bpmSchedules
     * @param bpmEvents
     */
    public BpmMonitor(long resourceId, String criticalityTypeCode, String name, String message, Integer occurrenceInterval, Long changeDate, String changeBy, char status, Set<BpmCondition> bpmConditions, Set<BpmSchedule> bpmSchedules, Set<BpmEvent> bpmEvents) {
       this.resourceId = resourceId;
       this.criticalityTypeCode = criticalityTypeCode;
       this.name = name;
       this.message = message;
       this.occurrenceInterval = occurrenceInterval;
       this.changeDate = changeDate;
       this.changeBy = changeBy;
       this.status = status;
       this.bpmConditions = bpmConditions;
       this.bpmSchedules = bpmSchedules;
       this.bpmEvents = bpmEvents;
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
	 * @return the resourceId
	 */
	public long getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the criticalityTypeCode
	 */
	public String getCriticalityTypeCode() {
		return criticalityTypeCode;
	}

	/**
	 * @param criticalityTypeCode the criticalityTypeCode to set
	 */
	public void setCriticalityTypeCode(String criticalityTypeCode) {
		this.criticalityTypeCode = criticalityTypeCode;
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
	 * @return the occurrenceInterval
	 */
	public Integer getOccurrenceInterval() {
		return occurrenceInterval;
	}

	/**
	 * @param occurrenceInterval the occurrenceInterval to set
	 */
	public void setOccurrenceInterval(Integer occurrenceInterval) {
		this.occurrenceInterval = occurrenceInterval;
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
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * @return the bpmConditions
	 */
	public Set<BpmCondition> getBpmConditions() {
		return bpmConditions;
	}

	/**
	 * @param bpmConditions the bpmConditions to set
	 */
	public void setBpmConditions(Set<BpmCondition> bpmConditions) {
		this.bpmConditions = bpmConditions;
	}

	/**
	 * @return the bpmSchedules
	 */
	public Set<BpmSchedule> getBpmSchedules() {
		return bpmSchedules;
	}

	/**
	 * @param bpmSchedules the bpmSchedules to set
	 */
	public void setBpmSchedules(Set<BpmSchedule> bpmSchedules) {
		this.bpmSchedules = bpmSchedules;
	}

	/**
	 * @return the bpmEvents
	 */
	public Set<BpmEvent> getBpmEvents() {
		return bpmEvents;
	}

	/**
	 * @param bpmEvents the bpmEvents to set
	 */
	public void setBpmEvents(Set<BpmEvent> bpmEvents) {
		this.bpmEvents = bpmEvents;
	}
   
}


