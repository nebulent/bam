/**
 * 
 */
package com.netflexity.bam.monitor.dispatcher;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import netflexity.schema.software.bam.monitors._1.SQSMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.util.Assert;

import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.monitor.esper.statement.DatabaseStatementLoader;
import com.netflexity.bam.monitor.executor.MonitorExecutor;
import com.netflexity.bam.monitor.executor.MonitorExecutorInput;

/**
 * @author Alexei SCLIFOS
 *
 */
public class BamMonitorDispatcher implements MonitorDispatcher<BpmMonitor> {

	/*logger*/
	private static Logger logger = LoggerFactory.getLogger(DatabaseStatementLoader.class);
	
	/*marshaller*/
	private Unmarshaller unmarshaller;
	
	/*executors*/
	private MonitorExecutor<MonitorExecutorInput> esperMonitorExecutor;
	private MonitorExecutor<MonitorExecutorInput> manualMonitorExecutor;
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.dispatcher.MonitorDispatcher#dispatch(java.lang.Object)
	 */
	public void dispatch(BpmMonitor monitor) {
		try {
			Object object = unmarshall(monitor);
			MonitorExecutorInput input = new MonitorExecutorInput(monitor, object);
			if(object instanceof SQSMonitor) {
				manualMonitorExecutor.execute(input);
			} else {
				esperMonitorExecutor.execute(input);
			}
		} catch (Exception exc) {
			logger.error("Error:", exc);
		}
	}
	
	/**
	 * @param monitor
	 * @return
	 * @throws IOException
	 */
	private Object unmarshall(BpmMonitor monitor) throws IOException {
    	Assert.notNull(monitor);
    	Assert.notEmpty(monitor.getBpmConditions());
    	String expression = monitor.getBpmConditions().iterator().next().getExpression();
    	Assert.notNull(expression);
    	InputStream inputStream = null;
    	StreamSource streamSource = null;
    	try {
	    	inputStream = new ByteArrayInputStream(expression.getBytes());
	    	streamSource = new StreamSource(inputStream);
	    	return unmarshaller.unmarshal(streamSource);
    	} finally {
    		if(inputStream != null) {
    			inputStream.close();
    		}
    	}
	}
	

	/**
	 * @param unmarshaller the unmarshaller to set
	 */
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	/**
	 * @param esperMonitorExecutor the esperMonitorExecutor to set
	 */
	public void setEsperMonitorExecutor(MonitorExecutor<MonitorExecutorInput> esperMonitorExecutor) {
		this.esperMonitorExecutor = esperMonitorExecutor;
	}

	/**
	 * @param manualMonitorExecutor the manualMonitorExecutor to set
	 */
	public void setManualMonitorExecutor(MonitorExecutor<MonitorExecutorInput> manualMonitorExecutor) {
		this.manualMonitorExecutor = manualMonitorExecutor;
	}

}
