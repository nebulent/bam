package com.netflexity.bam.monitor.esper.statement;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import netflexity.schema.software.bam.monitors._1.FlowToFlowDurationMonitorType;
import netflexity.schema.software.bam.monitors._1.OverallTransactionDurationMonitorType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.util.Assert;

import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.repository.MonitorRepository;

/**
 * @author Alexei SCLIFOS
 *
 */
public class DatabaseStatementLoader implements StatementLoader {

	/*logger*/
	private static Logger logger = LoggerFactory.getLogger(DatabaseStatementLoader.class);
	
	/*repositories*/
	private MonitorRepository monitorRepository;
	
	/*marshaller*/
	private Unmarshaller unmarshaller;
	
    /* (non-Javadoc)
     * @see com.netflexity.bam.monitor.esper.statement.StatementLoader#loadStatements()
     */
    public List<StatementBean> loadStatements() {
    	List<StatementBean> response = new ArrayList<StatementBean>();
    	List<BpmMonitor> monitors = monitorRepository.getAllMonitors();
    	if(monitors != null) {
    		for (BpmMonitor monitor : monitors) {
    			try {
    				response.add(createStatement(monitor));
    			} catch (Exception exc) {
    				logger.error("Error:", exc);
    			}
    		}
    	}
        return response;
    }
    
    /**
     * @param monitor
     * @return
     */
    private StatementBean createStatement(BpmMonitor monitor) throws IOException {
    	Assert.notNull(monitor);
    	Assert.notEmpty(monitor.getBpmConditions());
    	String expression = monitor.getBpmConditions().iterator().next().getExpression();
    	Assert.notNull(expression);
    	InputStream inputStream = new ByteArrayInputStream(expression.getBytes());
    	StreamSource streamSource = new StreamSource(inputStream);
    	Object object = unmarshaller.unmarshal(streamSource);
    	Assert.notNull(object);
    	String eplExpression = null;
    	String additionalParameters;
    	if(object instanceof FlowToFlowDurationMonitorType) {
    		FlowToFlowDurationMonitorType monitorType = (FlowToFlowDurationMonitorType)object;
    		additionalParameters = monitor.getId().longValue() + " as monitorId, " +
    						       monitorType.getFlow1UUID() + " as flow1UUID, " +
    						       monitorType.getFlow2UUID() + " as flow2UUID, " +
    						       monitorType.getDelayInSeconds() + " as delayInSeconds ";
    		eplExpression = "@Monitor('1') " +
    				        "select " + additionalParameters +
    				        "from pattern [every a = FlowDurationEventType(a.flowUUID = '" +  monitorType.getFlow1UUID() + "') " +
    				        "-> timer:interval(" + monitorType.getDelayInSeconds() + " seconds) " +
    				        "and not b = FlowDurationEventType(b.flowUUID = '" + monitorType.getFlow2UUID() + "') ]";
    	} else if(object instanceof OverallTransactionDurationMonitorType) {
    		OverallTransactionDurationMonitorType monitorType = (OverallTransactionDurationMonitorType)object;
    		additionalParameters = monitor.getId().longValue() + " as monitorId";
    		eplExpression = "@Monitor('2') " +
    						"select a, b, " + additionalParameters +
    						"from pattern [every a = OverallTransactionDurationEventType(a.processId = " + monitorType.getProcessId() + " and a.flowState = 'START') " +
    						"-> " +
    						"b = OverallTransactionDurationEventType(b.processId = " + monitorType.getProcessId() + " and b.flowState = 'END' " +
    						"and a.transactionUUID = b.transactionUUID)]";
    	} else {
    		throw new RuntimeException("Unknown monitor !!!");
    	}
    	Assert.hasLength(eplExpression);
    	return new StatementBean(eplExpression);
    }

	/**
	 * @param monitorRepository the monitorRepository to set
	 */
	public void setMonitorRepository(MonitorRepository monitorRepository) {
		this.monitorRepository = monitorRepository;
	}

	/**
	 * @param unmarshaller the unmarshaller to set
	 */
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

}






























