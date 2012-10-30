package com.netflexity.bam.monitor.generator.command;

import java.util.Date;
import java.util.UUID;

import netflexity.schema.software.bam.messages._1.AcknowledgeTransactionTracking;

import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.monitor.generator.GeneratorCore;
import com.netflexity.bam.monitor.generator.context.TransactionContext;

public class SendMessageCommand implements Runnable {

    private GeneratorCore generator;
    private BpmFlow flow;
    private TransactionContext transactionContext;


    public SendMessageCommand(GeneratorCore simulator, BpmFlow flow, TransactionContext transactionContext) {
        this.generator = simulator;
        this.transactionContext = transactionContext;
        this.flow = flow;
    }

    public void run() {
        try {
            Date date = new Date();
            System.out.println("-> " + date.toString() + " Flow UUID [" + flow.getUuid() + "] UUID: " + (transactionContext.getUuid() == null ? "" : transactionContext.getUuid().toString()));
            AcknowledgeTransactionTracking response = generator.sendMessage(flow, transactionContext.getUuid());
            transactionContext.setUuid(UUID.fromString(response.getTransactionUuid()));
            System.out.println("<- " + date.toString() + " Flow UUID [" + response.getFlowUuid() + "] UUID: " + response.getTransactionUuid());
            transactionContext.scheduleNextMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
