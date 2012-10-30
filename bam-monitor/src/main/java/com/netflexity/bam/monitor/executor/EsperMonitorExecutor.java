/**
 * 
 */
package com.netflexity.bam.monitor.executor;

import netflexity.schema.software.bam.monitors._1.FlowToFlowDurationMonitorType;
import netflexity.schema.software.bam.monitors._1.OverallTransactionDurationMonitorType;

import com.netflexity.bam.monitor.esper.EsperBean;
import com.netflexity.bam.monitor.esper.statement.StatementBean;

/**
 * @author Alexei SCLIFOS
 *
 */
public class EsperMonitorExecutor implements MonitorExecutor<MonitorExecutorInput> {

	/*services*/
	private EsperBean esperBean;
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.monitor.executor.MonitorExecutor#execute(java.lang.Object)
	 */
	public void execute(MonitorExecutorInput input) {
    	String eplExpression = null;
    	String additionalParameters = null;
    	boolean isMonitorFound = false;
    	if(input.getMonitorType() instanceof FlowToFlowDurationMonitorType) {
    		FlowToFlowDurationMonitorType monitorType = (FlowToFlowDurationMonitorType)input.getMonitorType();
    		additionalParameters = input.getMonitor().getId().longValue() + " as monitorId, " +
    						       monitorType.getFlow1UUID() + " as flow1UUID, " +
    						       monitorType.getFlow2UUID() + " as flow2UUID, " +
    						       monitorType.getDelayInSeconds() + " as delayInSeconds ";
    		eplExpression = "@Monitor('1') " +
    				        "select " + additionalParameters +
    				        "from pattern [every a = FlowDurationEventType(a.flowUUID = '" +  monitorType.getFlow1UUID() + "') " +
    				        "-> timer:interval(" + monitorType.getDelayInSeconds() + " seconds) " +
    				        "and not b = FlowDurationEventType(b.flowUUID = '" + monitorType.getFlow2UUID() + "') ]";
    		isMonitorFound = true;
    	} else if(input.getMonitorType() instanceof OverallTransactionDurationMonitorType) {
    		OverallTransactionDurationMonitorType monitorType = (OverallTransactionDurationMonitorType)input.getMonitorType();
    		additionalParameters = input.getMonitor().getId().longValue() + " as monitorId";
    		eplExpression = "@Monitor('2') " +
    						"select a, b, " + additionalParameters +
    						"from pattern [every a = OverallTransactionDurationEventType(a.processId = " + monitorType.getProcessId() + " and a.flowState = 'START') " +
    						"-> " +
    						"b = OverallTransactionDurationEventType(b.processId = " + monitorType.getProcessId() + " and b.flowState = 'END' " +
    						"and a.transactionUUID = b.transactionUUID)]";
    		isMonitorFound = true;
    	}
    	if(isMonitorFound) {
    		esperBean.addStatement(new StatementBean(eplExpression));
    	}
	}

	/**
	 * @param esperBean the esperBean to set
	 */
	public void setEsperBean(EsperBean esperBean) {
		this.esperBean = esperBean;
	}
}
