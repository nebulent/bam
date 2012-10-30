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

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_schedule")
public class BpmSchedule implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "party_id", nullable = false, length = 32)
    private String partyId;
	
	@Column(name = "name", nullable = false, length = 96)
    private String name;
	
	@Column(name = "minutes")
    private String minutes;
	
	@Column(name = "hours")
    private String hours;
	
	@Column(name = "days_of_week")
    private String daysOfWeek;
	
	@Column(name = "days_of_month")
    private String daysOfMonth;
	
	@Column(name = "months")
    private String months;
	
	@Column(name = "seconds")
    private String seconds;
	
	@Column(name = "change_date")
    private Long changeDate;
	
	@Column(name = "change_by")
    private String changeBy;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_monitor_schedule", joinColumns = {@JoinColumn(name = "schedule_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "monitor_id", nullable = false)})
    private Set<BpmMonitor> bpmMonitors = new HashSet<BpmMonitor>(0);

    /**
     * 
     */
    public BpmSchedule() {
    }

	
    /**
     * @param id
     * @param name
     * @param minutes
     * @param hours
     * @param daysOfWeek
     * @param daysOfMonth
     * @param months
     * @param seconds
     */
    public BpmSchedule(long id, String name, String minutes, String hours, String daysOfWeek, String daysOfMonth, String months, String seconds) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.hours = hours;
        this.daysOfWeek = daysOfWeek;
        this.daysOfMonth = daysOfMonth;
        this.months = months;
        this.seconds = seconds;
    }
    
    /**
     * @param id
     * @param partyId
     * @param name
     * @param minutes
     * @param hours
     * @param daysOfWeek
     * @param daysOfMonth
     * @param months
     * @param seconds
     * @param changeDate
     * @param changeBy
     * @param bpmMonitors
     */
    public BpmSchedule(long id, String partyId, String name, String minutes, String hours, String daysOfWeek, String daysOfMonth, String months, String seconds, Long changeDate, String changeBy, Set<BpmMonitor> bpmMonitors) {
       this.id = id;
       this.partyId = partyId;
       this.name = name;
       this.minutes = minutes;
       this.hours = hours;
       this.daysOfWeek = daysOfWeek;
       this.daysOfMonth = daysOfMonth;
       this.months = months;
       this.seconds = seconds;
       this.changeDate = changeDate;
       this.changeBy = changeBy;
       this.bpmMonitors = bpmMonitors;
    }


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the minutes
	 */
	public String getMinutes() {
		return minutes;
	}


	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}


	/**
	 * @return the hours
	 */
	public String getHours() {
		return hours;
	}


	/**
	 * @param hours the hours to set
	 */
	public void setHours(String hours) {
		this.hours = hours;
	}


	/**
	 * @return the daysOfWeek
	 */
	public String getDaysOfWeek() {
		return daysOfWeek;
	}


	/**
	 * @param daysOfWeek the daysOfWeek to set
	 */
	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}


	/**
	 * @return the daysOfMonth
	 */
	public String getDaysOfMonth() {
		return daysOfMonth;
	}


	/**
	 * @param daysOfMonth the daysOfMonth to set
	 */
	public void setDaysOfMonth(String daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}


	/**
	 * @return the months
	 */
	public String getMonths() {
		return months;
	}


	/**
	 * @param months the months to set
	 */
	public void setMonths(String months) {
		this.months = months;
	}


	/**
	 * @return the seconds
	 */
	public String getSeconds() {
		return seconds;
	}


	/**
	 * @param seconds the seconds to set
	 */
	public void setSeconds(String seconds) {
		this.seconds = seconds;
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
	 * @return the bpmMonitors
	 */
	public Set<BpmMonitor> getBpmMonitors() {
		return bpmMonitors;
	}


	/**
	 * @param bpmMonitors the bpmMonitors to set
	 */
	public void setBpmMonitors(Set<BpmMonitor> bpmMonitors) {
		this.bpmMonitors = bpmMonitors;
	}
   
}


