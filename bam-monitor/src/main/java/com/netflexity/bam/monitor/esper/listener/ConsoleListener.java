package com.netflexity.bam.monitor.esper.listener;

import java.lang.annotation.Annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.espertech.esper.client.annotation.Description;

public class ConsoleListener implements StatementAwareUpdateListener {

    private static final Log logger = LogFactory.getLog(ConsoleListener.class);

    public void update(EventBean[] newEvents, EventBean[] oldevents, EPStatement statement, EPServiceProvider epServiceProvider) {
        for (EventBean eventBean : newEvents) {
            String description = getDescriptionValue(statement.getAnnotations());
            String processName = (String)eventBean.get("a.processName");
            String transactionUuid = (String)eventBean.get("a.transactionUuid");
            String stageTypeCode = (String)eventBean.get("a.stageTypeCode");
            logger.info("*** Monitor message: " + description + " [" + processName + "] " + transactionUuid + " " + stageTypeCode);
        }
    }

    protected String getDescriptionValue(Annotation[] annotations) {
        if (annotations != null) {
            for (Annotation a : annotations) {
                if (a instanceof Description) {
                    return ((Description) a).value();
                }
            }
        }
        return null;
    }
}
