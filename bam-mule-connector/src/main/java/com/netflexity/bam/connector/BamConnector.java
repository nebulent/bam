package com.netflexity.bam.connector;

import java.math.BigInteger;
import java.util.ArrayList;
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

/**
 * Cloud Connector
 *
 * @author Netflexity, Inc.
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
    	ProcessTransactionTracking body = new ProcessTransactionTracking();
    	body.setFlowUuid(flowUuid);
    	body.setTransactionUuid(transactionUuid);
    	body.setTransactionContent(content.getBytes());
    	body.setTransactionDate(new BigInteger(new Long(System.currentTimeMillis()).toString()));
    	bam.processTransactionTracking(body);
        
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
