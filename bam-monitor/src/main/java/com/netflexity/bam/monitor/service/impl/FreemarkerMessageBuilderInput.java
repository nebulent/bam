/**
 * 
 */
package com.netflexity.bam.monitor.service.impl;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Alexei SCLIFOS
 *
 */
public class FreemarkerMessageBuilderInput implements Serializable {

	/**/
	private static final long serialVersionUID = 1L;
	
	/*properties*/
	private String templateContent;
	private Map<String, Object> parameters;
	private Long monitorId;
	
	/**
	 * 
	 */
	public FreemarkerMessageBuilderInput() {
	}
	
	/**
	 * @param templateContent
	 * @param parameters
	 * @param monitorId
	 */
	public FreemarkerMessageBuilderInput(String templateContent, Map<String, Object> parameters, Long monitorId) {
		this.templateContent = templateContent;
		this.parameters = parameters;
		this.monitorId = monitorId;
	}
	
	/**
	 * @return the templateContent
	 */
	public String getTemplateContent() {
		return templateContent;
	}
	/**
	 * @param templateContent the templateContent to set
	 */
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the monitorId
	 */
	public Long getMonitorId() {
		return monitorId;
	}

	/**
	 * @param monitorId the monitorId to set
	 */
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	
}
