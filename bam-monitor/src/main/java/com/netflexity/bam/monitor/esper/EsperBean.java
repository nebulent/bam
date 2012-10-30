package com.netflexity.bam.monitor.esper;

import java.util.List;

import netflexity.schema.software.bam.monitors._1.FlowDurationEventType;
import netflexity.schema.software.bam.monitors._1.OverallTransactionDurationEventType;

import org.springframework.beans.factory.BeanNameAware;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.netflexity.bam.monitor.esper.statement.StatementBean;

/**
 * @author Alexei SCLIFOS
 *
 */
public class EsperBean implements BeanNameAware {

	/*properties*/
    //private StatementLoader statementLoader;
    private String name;
    private List<StatementAwareUpdateListener> listeners;
    
    /*ESPER services*/
    private EPServiceProvider epServiceProvider;
    private EPRuntime epRuntime;

    /**
     * @param statementBean
     */
    public void addStatement(StatementBean statementBean) {
        registerStatement(statementBean);
    }

    /**
     * @param event
     */
    public void sendEvent(Object event) {
        getEpRuntime().sendEvent(event);
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
     */
    public void setBeanName(String name) {
        this.name = name;
    }

    /**
     * @throws Exception
     */
    public void init() throws Exception {
    	/*
        List<StatementBean> beans = statementLoader.loadStatements();
        for (StatementBean statementBean : beans) {
            registerStatement(statementBean);
        }
        */
    }

    /**
     * @param provider
     * @param statementBean
     */
    private void registerStatement(StatementBean statementBean) {
    	if(listeners == null || listeners.isEmpty()) {
    		return;
    	}
        for (StatementAwareUpdateListener listener : listeners) {
            statementBean.addListener(listener);
        }
        statementBean.setEPStatement(getEpServiceProvider().getEPAdministrator().createEPL(statementBean.getEPL()));
    }

    /**
     * @return
     */
    private EPRuntime getEpRuntime() {
        if (epRuntime == null) {
            epRuntime = getEpServiceProvider().getEPRuntime();
        }
        return epRuntime;
    }

    /**
     * @return
     */
    private EPServiceProvider getEpServiceProvider() {
        if (epServiceProvider == null) {
        	Configuration configuration = new Configuration();
        	/*adding events type*/
        	configuration.addEventType("FlowDurationEventType", FlowDurationEventType.class);
        	configuration.addEventType("OverallTransactionDurationEventType", OverallTransactionDurationEventType.class);
        	/*adding annotations*/
        	configuration.addImport("com.netflexity.bam.monitor.esper.annotation.*");
            epServiceProvider = EPServiceProviderManager.getProvider(name, configuration);
        }
        return epServiceProvider;
    }

    /**
     * @throws Exception
     */
    public void destroy() throws Exception {
        getEpServiceProvider().destroy();
    }

    /**
     * @param statementLoader
     */
    /*
    public void setStatementLoader(StatementLoader statementLoader) {
        this.statementLoader = statementLoader;
    }*/

	/**
	 * @param listeners the listeners to set
	 */
	public void setListeners(List<StatementAwareUpdateListener> listeners) {
		this.listeners = listeners;
	}

}
