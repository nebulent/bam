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
import javax.persistence.OneToMany;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_qualifier")
public class BpmQualifier implements Serializable {

	/**/
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "party_id", nullable = false, length = 32)
	private String partyId;
	
	@Column(name = "name", nullable = false, length = 96)
	private String name;
	
	@Column(name = "static_value", length = 128)
	private String staticValue;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmQualifier")
	private Set<BpmStage> bpmStages = new HashSet<BpmStage>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmQualifier")
	private Set<BpmFlow> bpmFlows = new HashSet<BpmFlow>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmQualifier")
	private Set<BpmQualifierAttribute> bpmQualifierAttributes = new HashSet<BpmQualifierAttribute>(0);

	/**
	 * 
	 */
	public BpmQualifier() {
	}


	/**
	 * @param partyId
	 * @param name
	 */
	public BpmQualifier(String partyId, String name) {
		this.partyId = partyId;
		this.name = name;
	}
	
	/**
	 * @param partyId
	 * @param name
	 * @param staticValue
	 * @param bpmStages
	 * @param bpmFlows
	 * @param bpmQualifierAttributes
	 */
	public BpmQualifier(String partyId, String name, String staticValue, Set<BpmStage> bpmStages, Set<BpmFlow> bpmFlows, Set<BpmQualifierAttribute> bpmQualifierAttributes) {
		this.partyId = partyId;
		this.name = name;
		this.staticValue = staticValue;
		this.bpmStages = bpmStages;
		this.bpmFlows = bpmFlows;
		this.bpmQualifierAttributes = bpmQualifierAttributes;
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
	 * @return the staticValue
	 */
	public String getStaticValue() {
		return staticValue;
	}


	/**
	 * @param staticValue the staticValue to set
	 */
	public void setStaticValue(String staticValue) {
		this.staticValue = staticValue;
	}


	/**
	 * @return the bpmStages
	 */
	public Set<BpmStage> getBpmStages() {
		return bpmStages;
	}


	/**
	 * @param bpmStages the bpmStages to set
	 */
	public void setBpmStages(Set<BpmStage> bpmStages) {
		this.bpmStages = bpmStages;
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
	 * @return the bpmQualifierAttributes
	 */
	public Set<BpmQualifierAttribute> getBpmQualifierAttributes() {
		return bpmQualifierAttributes;
	}


	/**
	 * @param bpmQualifierAttributes the bpmQualifierAttributes to set
	 */
	public void setBpmQualifierAttributes(Set<BpmQualifierAttribute> bpmQualifierAttributes) {
		this.bpmQualifierAttributes = bpmQualifierAttributes;
	}

}


