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
@Entity(name = "bpm_condition")
public class BpmCondition  implements Serializable {

     /**/
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    @Column(name = "party_id", nullable = false, length = 64)
    private String partyId;
    
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    
    @Column(name = "expression", length = 65535)
    private String expression;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_monitor_condition", joinColumns = {@JoinColumn(name = "condition_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "monitor_id", nullable = false)})
    private Set<BpmMonitor> bpmMonitors = new HashSet<BpmMonitor>(0);

    /**
     * 
     */
    public BpmCondition() {
    }
	
    /**
     * @param id
     * @param name
     * @param expression
     */
    public BpmCondition(long id, String name, String expression) {
        this.id = id;
        this.name = name;
        this.expression = expression;
    }
    
    /**
     * @param id
     * @param partyId
     * @param name
     * @param expression
     * @param bpmMonitors
     */
    public BpmCondition(long id, String partyId, String name, String expression, Set<BpmMonitor> bpmMonitors) {
       this.id = id;
       this.partyId = partyId;
       this.name = name;
       this.expression = expression;
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
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
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


