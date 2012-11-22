package com.netflexity.bam.connector;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import netflexity.schema.software.bam.messages._1.AcknowledgeTransactionTracking;
import netflexity.schema.software.bam.messages._1.ProcessTransactionTracking;
import netflexity.ws.software.bam.services._1_0.BAM;

import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.param.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cloud Connector
 *
 * @author Netflexity, Inc.
 */
@Connector(name="bam", schemaVersion="1.1")
public class BamConnector
{
    /**
     * BAM Service Url
     */
    @Configurable
    private String serviceUrl;
    
    /**
     * Enabled
     */
    @Configurable
    private boolean enabled = true;
    
    private BAM bam;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    public void connect() throws ConnectionException {
    	JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
        bean.setAddress(serviceUrl);
        bean.setServiceClass(BAM.class);
        
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
        bam = null;
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return bam != null;
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return serviceUrl;
    }

    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/ProcessTransactionTracking-connector.xml.sample bam:process-transaction-tracking}
     *
     * @param flowUuid String flow uuid
     * @param transactionUuid String transaction uuid
     * @param content String content
     * @return acknoledgment
     */
    @Processor
    public void processTransactionTracking(String flowUuid, String transactionUuid, @Optional String content)
    {
    	
    	if (!enabled) {
    		return;
    	}
    	
    	try {
    		//TODO: make call on separate thread. Requires a thread pull in order not to produce too much threads in case of big load 
	    	ProcessTransactionTracking body = new ProcessTransactionTracking();
	    	body.setFlowUuid(flowUuid);
	    	body.setTransactionUuid(transactionUuid);
	    	body.setTransactionContent(content.getBytes());
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTimeInMillis(System.currentTimeMillis());
	    	body.setTransactionDate(calendar);
	    	bam.processTransactionTracking(body);
    	} catch (Exception e) {
    		//Since we do not want to break the flow with our calls, we only log error, but not throwing it futher
    		logger.error("Problem occured while submitting data to BAM", e);
    	}
    }

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public BAM getBam() {
		return bam;
	}

	public void setBam(BAM bam) {
		this.bam = bam;
	}
}
