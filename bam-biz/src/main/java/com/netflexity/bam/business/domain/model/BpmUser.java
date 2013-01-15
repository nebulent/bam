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

@Entity(name = "bpm_user")
public class BpmUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="alias", nullable=false, length=48, unique=true)
	private String alias;
	
	@Column(name="email", nullable=false, length=60, unique=true)
	private String email;
	
	@Column(name="full_name", nullable=false, length=100)
	private String fullName;
	
	@Column(name="password", nullable=false, length=30)
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="bpmUser")
	private Set<BpmUserOrganization> bpmUserOrganizations = new HashSet<BpmUserOrganization>();

	public BpmUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<BpmUserOrganization> getBpmUserOrganizations() {
		return bpmUserOrganizations;
	}

	public void setBpmUserOrganizations(Set<BpmUserOrganization> bpmUserOrganizations) {
		this.bpmUserOrganizations = bpmUserOrganizations;
	}
}
