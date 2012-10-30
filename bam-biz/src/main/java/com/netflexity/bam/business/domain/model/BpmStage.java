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
@Entity(name = "bpm_stage")
public class BpmStage implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
	public static final String START = "START";
    public static final String INTERMEDIATE = "INTERMEDIATE";
    public static final String END = "END";
    public static final String ALLINONE = "ALLINONE";
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualifier_id", nullable = true)
    private BpmQualifier bpmQualifier;
    
    @Column(name = "party_id", nullable = false, length = 64)
    private String partyId;
    
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    
    @Column(name = "description", length = 512)
    private String description;
    
    @Column(name = "change_date")
    private Long changeDate;
    
    @Column(name = "change_by", length = 32)
    private String changeBy;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmStage")
    private Set<BpmFlow> bpmFlows = new HashSet<BpmFlow>(0);
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_stage_attribute", joinColumns = {@JoinColumn(name = "stage_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "attribute_id", nullable = false)})
    private Set<BpmAttribute> bpmAttributes = new HashSet<BpmAttribute>(0);

    /**
     * 
     */
    public BpmStage() {
    }

    /**
     * @param bpmQualifier
     * @param partyId
     * @param name
     */
    public BpmStage(BpmQualifier bpmQualifier, String partyId, String name) {
        this.bpmQualifier = bpmQualifier;
        this.partyId = partyId;
        this.name = name;
    }

    /**
     * @param bpmQualifier
     * @param partyId
     * @param name
     * @param description
     * @param changeDate
     * @param changeBy
     * @param bpmFlows
     * @param bpmAttributes
     */
    public BpmStage(BpmQualifier bpmQualifier, String partyId, String name, String description, Long changeDate, String changeBy, Set<BpmFlow> bpmFlows, Set<BpmAttribute> bpmAttributes) {
        this.bpmQualifier = bpmQualifier;
        this.partyId = partyId;
        this.name = name;
        this.description = description;
        this.changeDate = changeDate;
        this.changeBy = changeBy;
        this.bpmFlows = bpmFlows;
        this.bpmAttributes = bpmAttributes;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the bpmFlows
	 */
	public Set<BpmFlow> getBpmFlows() {
		return bpmFlows;
	}

	/**
	 * @param bpmFlows the bpmFlows to set
	 */
	public void setBpmFlows(Set<BpmFlow> bpmFlows) {
		this.bpmFlows = bpmFlows;
	}

	/**
	 * @return the bpmAttributes
	 */
	public Set<BpmAttribute> getBpmAttributes() {
		return bpmAttributes;
	}

	/**
	 * @param bpmAttributes the bpmAttributes to set
	 */
	public void setBpmAttributes(Set<BpmAttribute> bpmAttributes) {
		this.bpmAttributes = bpmAttributes;
	}

}


