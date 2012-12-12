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

@Entity(name = "bpm_user_organization")
public class BpmUserOrganization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private BpmUser bpmUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private BpmOrganization bpmOrganization;
	
	@Column(name = "role")
	private String role;

	public BpmUserOrganization() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BpmUser getBpmUser() {
		return bpmUser;
	}

	public void setBpmUser(BpmUser bpmUser) {
		this.bpmUser = bpmUser;
	}

	public BpmOrganization getBpmOrganization() {
		return bpmOrganization;
	}

	public void setBpmOrganization(BpmOrganization bpmOrganization) {
		this.bpmOrganization = bpmOrganization;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
