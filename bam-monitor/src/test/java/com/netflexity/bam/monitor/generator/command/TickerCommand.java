package com.netflexity.bam.monitor.generator.command;

import com.netflexity.bam.monitor.generator.GeneratorCore;
import com.netflexity.bam.monitor.generator.context.TransactionContext;
import java.util.Random;

public class TickerCommand implements Runnable {

    private GeneratorCore generator;
    private Random rand = new Random(System.currentTimeMillis());
    private int chanceToLaunch = 2; // e.g. 1/2

    public TickerCommand(GeneratorCore simulator){
        this.generator = simulator;
    }

    public void run(){
        if(generator.transCount.get() < generator.getMaxTransactions()){
            int random = Math.abs(rand.nextInt());
            if(random % chanceToLaunch == 0){
                generator.transCount.getAndIncrement();
                TransactionContext transactionContext = new TransactionContext(generator);
                transactionContext.init();
                generator.scheduleCommand(transactionContext, 0);
            }
        }else{
            System.out.println("Create new transaction denied: " + generator.transCount.get() + " in progress");
        }
    }
}