package com.netflexity.bam.business.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alexei SCLIFOS
 *
 */
@Entity(name = "bpm_qualifier_attribute")
public class BpmQualifierAttribute implements Serializable {

    /**/
	private static final long serialVersionUID = 1L;
	
    @Id 
    @Column(name="id")
	private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualifier_id", nullable = false)
    private BpmQualifier bpmQualifier;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private BpmAttribute bpmAttribute;
    
    @Column(name = "sequence", nullable = false)
    private short sequence;

    /**
     * 
     */
    public BpmQualifierAttribute() {
    }

    /**
     * @param id
     * @param bpmQualifier
     * @param bpmAttribute
     * @param sequence
     */
    public BpmQualifierAttribute(long id, BpmQualifier bpmQualifier, BpmAttribute bpmAttribute, short sequence) {
       this.id = id;
       this.bpmQualifier = bpmQualifier;
       this.bpmAttribute = bpmAttribute;
       this.sequence = sequence;
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
	 * @return the bpmAttribute
	 */
	public BpmAttribute getBpmAttribute() {
		return bpmAttribute;
	}

	/**
	 * @param bpmAttribute the bpmAttribute to set
	 */
	public void setBpmAttribute(BpmAttribute bpmAttribute) {
		this.bpmAttribute = bpmAttribute;
	}

	/**
	 * @return the sequence
	 */
	public short getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(short sequence) {
		this.sequence = sequence;
	}
   

}


