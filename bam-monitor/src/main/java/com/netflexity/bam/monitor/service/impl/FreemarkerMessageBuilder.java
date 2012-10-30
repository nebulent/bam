/**
 * 
 */
package com.netflexity.bam.monitor.service.impl;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import com.netflexity.bam.monitor.service.MessageBuilder;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

/**
 * @author Alexei SCLIFOS
 *
 */
public class FreemarkerMessageBuilder implements MessageBuilder<String, FreemarkerMessageBuilderInput> {

	/*properties*/
	private Configuration configuration;
	private StringTemplateLoader stringTemplateLoader;
	
	/**
	 * 
	 */
	public FreemarkerMessageBuilder() {
		configuration = new Configuration();
		stringTemplateLoader = new StringTemplateLoader();
		configuration.setTemplateLoader(stringTemplateLoader);
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.service.MessageBuilder#build(java.lang.Object)
	 */
	public String build(FreemarkerMessageBuilderInput input) {
		Assert.notNull(input);
		Assert.notEmpty(input.getParameters());
		Assert.hasLength(input.getTemplateContent());
		try {
			String templateName = String.valueOf(input.getMonitorId());
			if(stringTemplateLoader.findTemplateSource(templateName) == null) {
				stringTemplateLoader.putTemplate(templateName, input.getTemplateContent());
			}
			return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName), input.getParameters());
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	}
}
