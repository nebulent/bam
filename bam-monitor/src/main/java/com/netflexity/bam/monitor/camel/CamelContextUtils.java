/**
 * 
 */
package com.netflexity.bam.monitor.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author Alexei SCLIFOS
 *
 */
public class CamelContextUtils {

	/*camel context*/
	private CamelContext context = new DefaultCamelContext();
	
	/*properties*/
	private List<CamelRoutesBuilder> builders;
	
	/**
	 * 
	 */
	public void init() throws Exception {
		/*add routes*/
		addRoutes();
		/*start the camel context*/
		context.start();
	}
	
	/**
	 * @throws Exception
	 */
	private void addRoutes() throws Exception {
		List<RouteBuilder> routes = getRoutes();
		if(routes != null) {
			for (RouteBuilder route : routes) {
				context.addRoutes(route);
			}
		}
	}
	
	/**
	 * @return
	 */
	private List<RouteBuilder> getRoutes() {
		List<RouteBuilder> routes = new ArrayList<RouteBuilder>();
		if(builders != null) {
			for (CamelRoutesBuilder builder : builders) {
				routes.addAll(builder.createRoutes());
			}
		}
		return routes;
	}

	/**
	 * @param builders the builders to set
	 */
	public void setBuilders(List<CamelRoutesBuilder> builders) {
		this.builders = builders;
	}

}







