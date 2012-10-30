package com.netflexity.bam.monitor.esper.statement;

import java.util.List;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface StatementLoader {

    /**
     * @return
     */
    List<StatementBean> loadStatements();
}
