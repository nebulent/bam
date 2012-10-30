/**
 * 
 */
package com.netflexity.bam.monitor.executor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import netflexity.schema.software.bam.monitors._1.SQSMonitor;

import org.apache.camel.CamelContext;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.util.Assert;


/**
 * @author Alexei SCLIFOS
 *
 */
public class ManualMonitorExecutor implements MonitorExecutor<MonitorExecutorInput> {

	/*logger*/
	private Logger logger = LoggerFactory.getLogger(ManualMonitorExecutor.class);
	
	/*alert levels*/
	private static final String ERROR_ALERT_LEVEL = "ERROR";
	private static final String WARN_ALERT_LEVEL = "WARN";
	private static final String INFO_ALERT_LEVEL = "INFO";
	private static final String DEBUG_ALERT_LEVEL = "DEBUG";
	
	/*camel context*/
	private CamelContext camelContext;
	
//	/*services*/
//	private CgpManagement cgpManagementClient;
//	private SlaService slaServiceClient;
	
	/*unmarshaller*/
	private Unmarshaller xStreamUnmarshaller;
	
	/*properties*/
	private int sqsMonitorLevel;
	
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.executor.MonitorExecutor#execute(java.lang.Object)
	 */
	public void execute(MonitorExecutorInput input) {
		try {
			if(input.getMonitorType() instanceof SQSMonitor) {
//				execute((SQSMonitor)input.getMonitorType());
			}
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	}
	
//	/**
//	 * @param clientIdentifier
//	 * @return
//	 * @throws Exception
//	 */
//	private EC2AccountType getEc2Account(String clientIdentifier) throws Exception {
//		ListExternalAccounts request = new ListExternalAccounts();
//		request.setClientIdentifier(clientIdentifier);
//		ExternalAccounts accounts = cgpManagementClient.listExternalAccounts(request);
//		if(accounts != null && accounts.getEc2Accounts() != null && !accounts.getEc2Accounts().isEmpty()) {
//			return accounts.getEc2Accounts().get(0);
//		}
//		return null;
//	}
	
//	/**
//	 * @param monitor
//	 * @throws Exception
//	 */
//	private void execute(final SQSMonitor monitor) throws Exception {
//		Assert.notNull(monitor);
//		logger.debug("Executing SQSMonitor: SQS Queue '{}' ... ", monitor.getSqsQueueName());
//		RouteBuilder builder = new RouteBuilder() {
//			public void configure() throws Exception {
//				from(new SQSComponent(camelContext).createEndpoint(createSQSConsumerURI(monitor))).process(new Processor() {
//					public void process(Exchange exchange) throws Exception {
//						SQSObject sqsObject = exchange.getIn().getBody(SQSObject.class);
//						LogEvent logEvent = (LogEvent)unmarshall(sqsObject.getMessageBody());
//						Level level = Level.toLevel(Integer.parseInt(logEvent.getLevel()));
//						if(level == Level.toLevel(sqsMonitorLevel)) {
//							/*creating alert*/
//							AlertType alert = new AlertType(); 
//						    alert.setMessage(logEvent.getMessage());
//						    alert.setLevel(getAlertLevel(level));
//						    alert.setTimestamp(logEvent.getTimestamp());
//						    alert.setServiceIdentifier(monitor.getServiceIdentifier());
//						    alert.setClientIdentifier(monitor.getPartyId());
//							/*creating send alert request*/
//						    SendAlert sendAlertRequest = new SendAlert();
//							sendAlertRequest.getAlerts().add(alert);
//							slaServiceClient.sendAlert(sendAlertRequest);
//						}
//					}
//				});
//			}
//		};
//		camelContext.addRoutes(builder);
//	}
	
	/**
	 * @param level
	 * @return
	 */
	private String getAlertLevel(Level level) {
		if(level == null) {
			return null;
		}
		if(level == Level.INFO) {
			return INFO_ALERT_LEVEL;
		} else if(level == Level.ERROR) {
			return ERROR_ALERT_LEVEL;
		} else if(level == Level.DEBUG) {
			return DEBUG_ALERT_LEVEL;
		} else if(level == Level.WARN) {
			return WARN_ALERT_LEVEL;
		}
		return null;
	}
	
	/**
	 * @param content
	 * @return
	 * @throws IOException
	 */
	private Object unmarshall(String content) throws IOException {
		Assert.hasLength(content);
    	InputStream inputStream = null;
    	StreamSource streamSource = null;
    	try {
	    	inputStream = new ByteArrayInputStream(content.getBytes());
	    	streamSource = new StreamSource(inputStream);
	    	return xStreamUnmarshaller.unmarshal(streamSource);
    	} finally {
    		if(inputStream != null) {
    			inputStream.close();
    		}
    	}
	}
	
//	/**
//	 * @param monitor
//	 * @return
//	 */
//	private String createSQSConsumerURI(SQSMonitor monitor) throws Exception {
//		EC2AccountType account = getEc2Account(monitor.getPartyId());
//		Assert.notNull(account, "EC2 account cannot be NULL !!!");
//		StringBuilder builder = new StringBuilder();;
//		builder.append("sqs:sqsConsumer?accessId=").append(account.getAccessKeyId()).append("&secretKey=").append(account.getAccessKey());
//		builder.append("&queueName=").append(monitor.getSqsQueueName()).append("&consumer.initialDelay=1000&consumer.delay=1000");
//		return builder.toString();
//	}

	/**
	 * @param camelContext the camelContext to set
	 */
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}

//	/**
//	 * @param cgpManagementClient the cgpManagementClient to set
//	 */
//	public void setCgpManagementClient(CgpManagement cgpManagementClient) {
//		this.cgpManagementClient = cgpManagementClient;
//	}
//
//	/**
//	 * @param slaServiceClient the slaServiceClient to set
//	 */
//	public void setSlaServiceClient(SlaService slaServiceClient) {
//		this.slaServiceClient = slaServiceClient;
//	}

	/**
	 * @param xStreamUnmarshaller the xStreamUnmarshaller to set
	 */
	public void setxStreamUnmarshaller(Unmarshaller xStreamUnmarshaller) {
		this.xStreamUnmarshaller = xStreamUnmarshaller;
	}

	/**
	 * @param sqsMonitorLevel the sqsMonitorLevel to set
	 */
	public void setSqsMonitorLevel(int sqsMonitorLevel) {
		this.sqsMonitorLevel = sqsMonitorLevel;
	}

}
