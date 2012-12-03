package com.netflexity.bam.monitor.generator;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.transform.stream.StreamResult;

import netflexity.schema.software.bam.messages._1.AcknowledgeTransactionTracking;
import netflexity.schema.software.bam.messages._1.ProcessTransactionTracking;
import netflexity.ws.software.bam.services._1_0.BAM;

import org.springframework.oxm.Marshaller;

import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.domain.model.BpmProcess;
import com.netflexity.bam.monitor.generator.command.TickerCommand;
import com.netflexity.bam.monitor.generator.jms.MessageSender;

public class GeneratorCore {

    private BAM client;
    private ScheduledThreadPoolExecutor commandExecutor;
    public AtomicInteger transCount;
    private TickerCommand ticker;
    private int maxTransactions = 5;
    private int maxTransactionDelay = 20; // max 20 seconds between messages
    private CountDownLatch doneSignal;
    private Random rand = new Random(System.currentTimeMillis());
    //private ProcessFlowDaoImpl processFlowDao;
    //private ProcessDaoImpl processDao;

    private MessageSender messageSender;
    private Marshaller marshaller;

    public GeneratorCore() {
    }

    public void init(){
        commandExecutor = new ScheduledThreadPoolExecutor(10);
    }

    public void launch(CountDownLatch doneSignal) {
        System.out.println("*** Launching core");
        this.doneSignal = doneSignal;
        transCount = new AtomicInteger(0);
        ticker = new TickerCommand(this);
        commandExecutor.scheduleAtFixedRate(ticker, 5, 20, TimeUnit.SECONDS);
    }

    public void destroy() {
        System.out.println("*** Terminating core");
        commandExecutor.shutdownNow();
        transCount = new AtomicInteger(0);
        doneSignal.countDown();
    }

    public void scheduleCommand(Runnable command, long delay) {
        commandExecutor.schedule(command, delay, TimeUnit.SECONDS);
    }

    public void onTransactionEnds(UUID uuid) {
        System.out.println("ending transaction " + uuid.toString());
        transCount.decrementAndGet();
    }

    public AcknowledgeTransactionTracking sendMessage(BpmFlow flow, UUID uuid) {
        ProcessTransactionTracking ptt = new ProcessTransactionTracking();
        ptt.setFlowUuid(flow.getUuid());
        ptt.setTransactionDate(Calendar.getInstance());
        if(uuid != null){
            ptt.setTransactionUuid(uuid.toString());
        }
        AcknowledgeTransactionTracking response = client.processTransactionTracking(ptt);
        if(messageSender != null && marshaller !=null){
            try{
            StringWriter sw = new StringWriter();
            marshaller.marshal(ptt, new StreamResult(sw));
            messageSender.sendMessage(sw.getBuffer().toString());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return response;
    }

    public List<BpmFlow> getFlows(Long processId) {
        //return processFlowDao.findProcessFlowByProcessId(processId);
    	return null;
    }

    public BpmProcess getRandomProcess() {
    	/*
        List<BpmProcess> processes = processDao.findAll();
        return selectRandomFromList(processes);
        */
    	return null;
    }

    public <T> T selectRandomFromList(List<T> list) {
        int listSize = list.size();
        int index = listSize > 1 ? Math.abs(rand.nextInt()) % (listSize - 1) : 0;
        return list.get(index);
    }

    public void setMaxTransactions(int maxTransactions) {
        this.maxTransactions = maxTransactions;
    }

    public int getMaxTransactions() {
        return maxTransactions;
    }

    public void setClient(BAM client) {
        this.client = client;
    }

    /*
    public void setProcessFlowDao(ProcessFlowDaoImpl processFlowDao) {
        this.processFlowDao = processFlowDao;
    }

    public void setProcessDao(ProcessDaoImpl processDao) {
        this.processDao = processDao;
    }
    */

    public int getMaxTransactionDelay() {
        return maxTransactionDelay;
    }

    public void setMaxTransactionDelay(int maxTransactionDelay) {
        this.maxTransactionDelay = maxTransactionDelay;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
}
