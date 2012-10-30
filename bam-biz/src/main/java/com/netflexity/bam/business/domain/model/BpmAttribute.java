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
import javax.persistence.OneToMany;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_attribute")
public class BpmAttribute implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @Column(name = "party_id", nullable = false, length = 64)
    private String partyId;
    
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    
    @Column(name = "expression", length = 65535)
    private String expression;
    
    @Column(name = "start_position")
    private Integer startPosition;
    
    @Column(name = "end_position")
    private Integer endPosition;
    
    @Column(name = "delimiter", length = 32)
    private String delimiter;
    
    @Column(name = "position")
    private Integer position;
    
    @Column(name = "required", nullable = false, length = 1)
    private char required;
    
    @Column(name = "datatype")
    private Integer datatype;
    
    @Column(name = "change_date")
    private Long changeDate;
    
    @Column(name = "change_by", length = 32)
    private String changeBy;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bpm_stage_attribute", joinColumns = {@JoinColumn(name = "attribute_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "stage_id", nullable = false)})
    private Set<BpmStage> bpmStages = new HashSet<BpmStage>(0);
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bpmAttribute")
    private Set<BpmQualifierAttribute> bpmQualifierAttributes = new HashSet<BpmQualifierAttribute>(0);

    /**
     * 
     */
    public BpmAttribute() {
    }

    /**
     * @param partyId
     * @param name
     * @param required
     */
    public BpmAttribute(String partyId, String name, char required) {
        this.partyId = partyId;
        this.name = name;
        this.required = required;
    }

    /**
     * @param partyId
     * @param name
     * @param expression
     * @param startPosition
     * @param endPosition
     * @param delimiter
     * @param position
     * @param required
     * @param datatype
     * @param changeDate
     * @param changeBy
     * @param bpmStages
     * @param bpmQualifierAttributes
     */
    public BpmAttribute(String partyId, String name, String expression, Integer startPosition, Integer endPosition, String delimiter, Integer position, char required, Integer datatype, Long changeDate, String changeBy, Set<BpmStage> bpmStages, Set<BpmQualifierAttribute> bpmQualifierAttributes) {
        this.partyId = partyId;
        this.name = name;
        this.expression = expression;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.delimiter = delimiter;
        this.position = position;
        this.required = required;
        this.datatype = datatype;
        this.changeDate = changeDate;
        this.changeBy = changeBy;
        this.bpmStages = bpmStages;
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
	 * @return the startPosition
	 */
	public Integer getStartPosition() {
		return startPosition;
	}

	/**
	 * @param startPosition the startPosition to set
	 */
	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}

	/**
	 * @return the endPosition
	 */
	public Integer getEndPosition() {
		return endPosition;
	}

	/**
	 * @param endPosition the endPosition to set
	 */
	public void setEndPosition(Integer endPosition) {
		this.endPosition = endPosition;
	}

	/**
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * @return the required
	 */
	public char getRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(char required) {
		this.required = required;
	}

	/**
	 * @return the datatype
	 */
	public Integer getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
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


