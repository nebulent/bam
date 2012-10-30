/**
 * 
 */
package com.netflexity.bam.monitor.camel;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alexei SCLIFOS
 *
 */
public class SQSCamelRoutesBuilder implements CamelRoutesBuilder {

	/*logger*/
	private static Logger logger = LoggerFactory.getLogger(SQSCamelRoutesBuilder.class);

	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.camel.CamelRoutesBuilder#createRoutes()
	 */
	public List<RouteBuilder> createRoutes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
