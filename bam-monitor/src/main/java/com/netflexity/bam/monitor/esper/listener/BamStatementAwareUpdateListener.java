/**
 * 
 */
package com.netflexity.bam.monitor.esper.listener;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import netflexity.schema.software.bam.monitors._1.OverallTransactionDurationEventType;

import org.apache.commons.lang.StringUtils;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.repository.MonitorRepository;
import com.netflexity.bam.monitor.esper.annotation.Monitor;
import com.netflexity.bam.monitor.service.EventProcessor;
import com.netflexity.bam.monitor.service.MessageBuilder;
import com.netflexity.bam.monitor.service.impl.FreemarkerMessageBuilderInput;

/**
 * @author Alexei SCLIFOS
 * 
 */
public class BamStatementAwareUpdateListener implements StatementAwareUpdateListener {

	/*monitors' codes*/
	private static final String MONITOR_CODE_1 = "1";
	private static final String MONITOR_CODE_2 = "2";
	
	/*constants*/
	private static final String MONITOR_ID = "monitorId";
	private static final String FLOW_1_UUID = "flow1UUID";
	private static final String FLOW_2_UUID = "flow2UUID";
	private static final String DELAY_IN_SECONDS = "delayInSeconds";
	private static final String EVENT_1_NAME = "a";
	private static final String EVENT_2_NAME = "b";
	
	/*repositories*/
	private MonitorRepository monitorRepository;
	
	/*services*/
	private EventProcessor<String> eventProcessor;
	private MessageBuilder<String, FreemarkerMessageBuilderInput> messageBuilder;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.espertech.esper.client.StatementAwareUpdateListener#update(com.espertech
	 * .esper.client.EventBean[], com.espertech.esper.client.EventBean[],
	 * com.espertech.esper.client.EPStatement,
	 * com.espertech.esper.client.EPServiceProvider)
	 */
	public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement statement, EPServiceProvider serviceProvider) {
		String monitorCode = getMonitorCode(statement.getAnnotations());
		if(!StringUtils.isEmpty(monitorCode)) {
			if(MONITOR_CODE_1.equals(monitorCode)) {
				processFlowToFlowDurationMonitorEvents(newEvents);
			} else if(MONITOR_CODE_2.equals(monitorCode)) {
				processOverallTransactionDurationMonitorEvents(newEvents);
			}
		}
	}
	
	/**
	 * @param events
	 */
	private void processFlowToFlowDurationMonitorEvents(EventBean[] events) {
		if(events != null && events.length > 0) {
			BpmMonitor monitor = null;
			Map<String, Object> params;
			for (EventBean event : events) {
				if(monitor == null) {
					monitor = getMonitor(event);
				}
				params = new HashMap<String, Object>();
				params.put(FLOW_1_UUID, event.get(FLOW_1_UUID));
				params.put(FLOW_2_UUID, event.get(FLOW_2_UUID));
				params.put(DELAY_IN_SECONDS, event.get(DELAY_IN_SECONDS));
				eventProcessor.process(messageBuilder.build(new FreemarkerMessageBuilderInput(monitor.getMessage(), params, monitor.getId())));
			}
		}
	}
	
	/**
	 * @param events
	 */
	private void processOverallTransactionDurationMonitorEvents(EventBean[] events) {
		if(events != null && events.length > 0) {
			BpmMonitor monitor = null;
			Map<String, Object> params;
			for (EventBean event : events) {
				if(monitor == null) {
					monitor = getMonitor(event);
				}
				params = new HashMap<String, Object>();
				params.put(EVENT_1_NAME, (OverallTransactionDurationEventType)event.get(EVENT_1_NAME));
				params.put(EVENT_2_NAME, (OverallTransactionDurationEventType)event.get(EVENT_2_NAME));
				eventProcessor.process(messageBuilder.build(new FreemarkerMessageBuilderInput(monitor.getMessage(), params, monitor.getId())));
			}
		}
	}
	
	/**
	 * @param event
	 * @return
	 */
	private BpmMonitor getMonitor(EventBean event) {
		return monitorRepository.getMonitorById((Long)event.get(MONITOR_ID));
	}
	
	/**
	 * @param annotations
	 * @return
	 */
	private String getMonitorCode(Annotation[] annotations) {
        if (annotations != null) {
            for (Annotation a : annotations) {
                if (a instanceof Monitor) {
                    return ((Monitor) a).code();
                }
            }
        }
        return null;
	}

	/**
	 * @param monitorRepository the monitorRepository to set
	 */
	public void setMonitorRepository(MonitorRepository monitorRepository) {
		this.monitorRepository = monitorRepository;
	}

	/**
	 * @param eventProcessor the eventProcessor to set
	 */
	public void setEventProcessor(EventProcessor<String> eventProcessor) {
		this.eventProcessor = eventProcessor;
	}

	/**
	 * @param messageBuilder the messageBuilder to set
	 */
	public void setMessageBuilder(MessageBuilder<String, FreemarkerMessageBuilderInput> messageBuilder) {
		this.messageBuilder = messageBuilder;
	}

}
