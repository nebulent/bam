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

@Entity(name = "bpm_organization")
public class BpmOrganization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", nullable=false, length=60)
	private String name;
	
	@Column(name="alias", nullable=false, length=48, unique=true)
	private String alias;
	
	@Column(name="description", length=200)
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="bpmOrganization")
	private Set<BpmUserOrganization> bpmUserOrganizations = new HashSet<BpmUserOrganization>();

	public BpmOrganization() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<BpmUserOrganization> getBpmUserOrganizations() {
		return bpmUserOrganizations;
	}

	public void setBpmUserOrganizations(Set<BpmUserOrganization> bpmUserOrganizations) {
		this.bpmUserOrganizations = bpmUserOrganizations;
	}
}
