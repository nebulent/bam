package com.netflexity.bam.monitor.esper.statement;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.StatementAwareUpdateListener;
import java.util.LinkedHashSet;
import java.util.Set;

public class StatementBean {

	/*properties*/
    private String epl;
    private Set<StatementAwareUpdateListener> listeners = new LinkedHashSet<StatementAwareUpdateListener>();
    
    /*ESPER properties*/
    private EPStatement epStatement;

    /**
     * @param epl
     */
    public StatementBean(String epl) {
        this.epl = epl;
    }

    /**
     * @return
     */
    public String getEPL() {
        return epl;
    }

    /**
     * @param listeners
     */
    public void setListeners(StatementAwareUpdateListener... listeners) {
        for (StatementAwareUpdateListener listener : listeners) {
            addListener(listener);
        }
    }

    /**
     * @param listener
     */
    public void addListener(StatementAwareUpdateListener listener) {
        listeners.add(listener);
        if (epStatement != null) {
            epStatement.addListener(listener);
        }
    }

    /**
     * @param epStatement
     */
    public void setEPStatement(EPStatement epStatement) {
        this.epStatement = epStatement;
        for (StatementAwareUpdateListener listener : listeners) {
        	this.epStatement.addListener(listener);
        }
    }
}
