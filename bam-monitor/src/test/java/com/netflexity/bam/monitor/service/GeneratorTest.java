package com.netflexity.bam.monitor.service;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflexity.bam.monitor.esper.EsperBean;
import com.netflexity.bam.monitor.esper.event.BamEvent;
import com.netflexity.bam.monitor.esper.statement.StatementBean;
import com.netflexity.bam.monitor.esper.statement.StatementLoader;
import com.netflexity.bam.monitor.executor.EsperMonitorExecutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-bam-monitor-context.xml"})
public class GeneratorTest {

//    @Autowired
//    private GeneratorCore generatorCore;
    
	@Autowired
    EsperBean esperBean;

	@Autowired
	StatementLoader statementLoader;
	
    public GeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    	Assert.assertNotNull(statementLoader);
    	Assert.assertNotNull(esperBean);
    	
    	List<StatementBean> statements = statementLoader.loadStatements();
    	for (StatementBean statementBean : statements) {
    		esperBean.addStatement(statementBean);
		}
    }
//    @Test
//    public void simulatorTest() {
//        System.out.println("Launching simulator");
//        CountDownLatch doneSignal = new CountDownLatch(1);
//        try {
//            generatorCore.launch(doneSignal);
//            doneSignal.await();
//        } catch (InterruptedException e) {
//        }
//    }

    @Test
    public void esperTest() {

        Date begin = new Date();
        Date intermediate = new Date(begin.getTime() + 10000);
        Date end = new Date(intermediate.getTime() + 7000);

        String transactionUuid = "aaaa-aaaa-aaaa";

        esperBean.sendEvent(createBamEvent(1L, "START", begin, transactionUuid));
        esperBean.sendEvent(createBamEvent(2L, "INTERMEDIATE", intermediate, transactionUuid));
        esperBean.sendEvent(createBamEvent(3L, "END", end, transactionUuid));

        Assert.assertTrue(true);

    }

    private BamEvent createBamEvent(long id, String stageTypeCode, Date transactionDate, String transactionUuid) {
        String partyId = "nebulent";
        String processName = "test process";

        BamEvent rv = new BamEvent();
        rv.setId(id);
        rv.setPartyId(partyId);
        rv.setProcessName(processName);
        rv.setStageTypeCode(stageTypeCode);
        rv.setTransactionDate(transactionDate.getTime());
        rv.setTransactionUuid(transactionUuid);
        return rv;
    }
}
