/**
 * 
 */
package com.netflexity.bam.monitor.camel;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface CamelRoutesBuilder {

	/**
	 * @return
	 */
	List<RouteBuilder> createRoutes();
}
