package com.netflexity.bam.monitor.esper.statement;

import java.util.List;

/**
 * @author Alexei SCLIFOS
 *
 */
public class DummyStatementLoaderImpl implements StatementLoader {

	/*properties*/
    private List<StatementBean> statementBeans;

    /* (non-Javadoc)
     * @see com.netflexity.bam.monitor.esper.statement.StatementLoader#loadStatements()
     */
    public List<StatementBean> loadStatements(){
        return statementBeans;
    }

    /**
     * @param statementBeans the statementBeans to set
     */
    public void setStatementBeans(List<StatementBean> statementBeans) {
        this.statementBeans = statementBeans;
    }

    

}
