package com.netflexity.bam.plugin.sqsflow;

import java.math.BigInteger;
import java.util.List;

import netflexity.schema.software.bam.messages._1.ProcessTransactionTracking;
import netflexity.ws.software.bam.services._1_0.BAM;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.netflexity.software.cgp.messages._1.ExternalAccounts;
import com.netflexity.software.cgp.messages._1.ListExternalAccounts;
import com.netflexity.software.cgp.types._1.EC2AccountType;
import com.netflexity.software.cgp.webservice._1_0.CgpManagement;
import com.netflexitysolutions.camel.sqs.SQSComponent;
import com.netflexitysolutions.camel.sqs.SQSObject;

public class SqsFlowProcessor implements InitializingBean {

	SqsFlowMappingProvider mappingProvider;
	
	CgpManagement cgpManagement;
	
	BAM bam;
	
	CamelContext camelContext;

	public void afterPropertiesSet() throws Exception {
		List<SqsFlowMapping> mappings = mappingProvider.getSqsFlowMappings();
		for (final SqsFlowMapping mapping : mappings) {
			RouteBuilder builder = new RouteBuilder() {
				public void configure() throws Exception {
					from(new SQSComponent(camelContext).createEndpoint(createSQSConsumerURI(mapping))).process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							SQSObject sqsObject = exchange.getIn().getBody(SQSObject.class);
							ProcessTransactionTracking request = new ProcessTransactionTracking();
							request.setFlowUuid(mapping.getFlowUUID());
							request.setUserName(mapping.getOwnerUUID());
							request.setTransactionContent(sqsObject.getMessageBody().getBytes());
							request.setTransactionDate(new BigInteger(Long.toString(System.currentTimeMillis())));//TODO we are not getting exact time
							bam.processTransactionTracking(request);
						}
					});
				}
			};
			camelContext.addRoutes(builder);
		}
	}
	
	/**
	 * @param monitor
	 * @return
	 */
	private String createSQSConsumerURI(SqsFlowMapping mapping) throws Exception {
		EC2AccountType account = getEc2Account(mapping.getOwnerUUID());
		Assert.notNull(account, "EC2 account cannot be NULL !!!");
		StringBuilder builder = new StringBuilder();;
		builder.append("sqs:sqsConsumer?accessId=").append(account.getAccessKeyId()).append("&secretKey=").append(account.getAccessKey());
		builder.append("&queueName=").append(mapping.getQueueName()).append("&sourceId=").append(mapping.getFlowUUID()).append("&consumer.initialDelay=1000&consumer.delay=1000");
		return builder.toString();
	}
	
	/**
	 * @param clientIdentifier
	 * @return
	 * @throws Exception
	 */
	private EC2AccountType getEc2Account(String clientIdentifier) throws Exception {
		//TODO add account caching
		ListExternalAccounts request = new ListExternalAccounts();
		request.setClientIdentifier(clientIdentifier);
		ExternalAccounts accounts = cgpManagement.listExternalAccounts(request);
		if(accounts != null && accounts.getEc2Accounts() != null && !accounts.getEc2Accounts().isEmpty()) {
			return accounts.getEc2Accounts().get(0);
		}
		return null;
	}

	/**
	 * @param mappingProvider the mappingProvider to set
	 */
	public void setMappingProvider(SqsFlowMappingProvider mappingProvider) {
		this.mappingProvider = mappingProvider;
	}

	/**
	 * @param cgpManagement the cgpManagement to set
	 */
	public void setCgpManagement(CgpManagement cgpManagement) {
		this.cgpManagement = cgpManagement;
	}

	/**
	 * @param bam the bam to set
	 */
	public void setBam(BAM bam) {
		this.bam = bam;
	}

	/**
	 * @param camelContext the camelContext to set
	 */
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}
	
}
