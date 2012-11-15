/**
 * This file was automatically generated by the Mule Development Kit
 */
package com.netflexity.bam.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netflexity.schema.software.bam.messages._1.ProcessTransactionTracking;
import netflexity.ws.software.bam.services._1_0.BAM;

import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ValidateConnection;

/**
 * Cloud Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="bam", schemaVersion="1.1")
public class BamConnector
{
    /**
     * Configurable
     */
    @Configurable
    private String serviceUrl;
    
    private BAM bam;

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    public void connect()
        throws ConnectionException {
        /*
         * CODE FOR ESTABLISHING A CONNECTION GOES IN HERE
         */
    	ClientProxyFactoryBean bean = new ClientProxyFactoryBean();
        bean.setAddress(serviceUrl);
        bean.setServiceClass(BAM.class);
        
        //Adding logging(not necessary)
        List<AbstractFeature> features = new ArrayList<AbstractFeature>();
        features.add(new LoggingFeature());
        bean.setFeatures(features);
        
        bam = (BAM) bean.create();
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        /*
         * CODE FOR CLOSING A CONNECTION GOES IN HERE
         */
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return true;
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "001";
    }

    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/ProcessTransactionTracking-connector.xml.sample bamconnector:process-transaction-tracking}
     *
     * @param flowUuid String flowUuid
     * @param transactionUuid String transactionUuid
     * @return Some string
     */
    @Processor
    public String processTransactionTracking(String flowUuid, String transactionUuid)
    {
        /*
         * MESSAGE PROCESSOR CODE GOES HERE
         */
    	
    	JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
        bean.setAddress(serviceUrl);
        bean.setServiceClass(BAM.class);
        
        //Adding logging(not necessary)
        List<AbstractFeature> features = new ArrayList<AbstractFeature>();
        features.add(new LoggingFeature());
        bean.setFeatures(features);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("wrapped", false);
        params.put("wrappedStyle", true);
        bean.setProperties(params);
        		
        
        bam = (BAM) bean.create();
    	
    	ProcessTransactionTracking body = new ProcessTransactionTracking();
    	body.setFlowUuid(flowUuid);
    	body.setTransactionUuid(transactionUuid);
    	
    	System.out.println("serviceUrl: " + serviceUrl);
    	System.out.println("bam: " + bam);
        return bam.processTransactionTracking(body).toString();
    }

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public BAM getBam() {
		return bam;
	}

	public void setBam(BAM bam) {
		this.bam = bam;
	}
}
