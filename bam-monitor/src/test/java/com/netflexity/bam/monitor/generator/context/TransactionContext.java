package com.netflexity.bam.monitor.generator.context;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.domain.model.BpmProcess;
import com.netflexity.bam.business.domain.model.BpmStage;
import com.netflexity.bam.monitor.generator.GeneratorCore;
import com.netflexity.bam.monitor.generator.command.SendMessageCommand;

public class TransactionContext implements Runnable {

    private GeneratorCore generator;
    private UUID uuid;
    private Deque<BpmFlow> flows;
    private Random rand = new Random(System.currentTimeMillis());

    public TransactionContext(GeneratorCore simulator) {
        this.generator = simulator;
    }

    public void init() {
        System.out.println("TransactionContext: init");
        BpmProcess process = generator.getRandomProcess();
        System.out.println("[" + process.getName() + "] selected");
        List<BpmFlow> flowList = generator.getFlows(process.getId());

        Collections.sort(flowList, new Comparator<BpmFlow>() {

            private List<String> typesList = Arrays.asList(null, BpmStage.START, BpmStage.INTERMEDIATE, BpmStage.ALLINONE, BpmStage.END);

            public int compare(BpmFlow o1, BpmFlow o2) {
                String code1 = o1 == null ? null : o1.getStageTypeCode();
                String code2 = o2 == null ? null : o2.getStageTypeCode();
                int index1 = typesList.indexOf(code1);
                int index2 = typesList.indexOf(code2);
                return index1 - index2;
            }
        });

        flows = new ArrayDeque<BpmFlow>(flowList);
    }

    public void run() {
        scheduleNextMessage();
    }

    public void scheduleNextMessage() {
        if (flows.isEmpty()) {
            generator.onTransactionEnds(uuid);
        } else {
            BpmFlow flow = flows.pop();
            int delay = Math.abs(rand.nextInt()) % generator.getMaxTransactionDelay();
            SendMessageCommand cmd = new SendMessageCommand(generator, flow, this);
            generator.scheduleCommand(cmd, delay);
            Date date = new Date(System.currentTimeMillis() + delay * 1000);
            System.out.println("scheduling message [" + flow.getStageTypeCode() + "] UUID: " + uuid == null ? "null" : uuid.toString() + " to " + date.toString());
        }
    }

    /**
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
