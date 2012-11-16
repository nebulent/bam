package com.netflexity.bam.business.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import netflexity.schema.software.bam.types._1.AttributeArrayType;
import netflexity.schema.software.bam.types._1.AttributeType;
import netflexity.schema.software.bam.types._1.ConditionType;
import netflexity.schema.software.bam.types._1.FlowArrayType;
import netflexity.schema.software.bam.types._1.FlowTransactionDetailsType;
import netflexity.schema.software.bam.types._1.FlowTransactionPayloadDetailsType;
import netflexity.schema.software.bam.types._1.FlowTransactionType;
import netflexity.schema.software.bam.types._1.FlowType;
import netflexity.schema.software.bam.types._1.MonitorType;
import netflexity.schema.software.bam.types._1.ObjectFactory;
import netflexity.schema.software.bam.types._1.ProcessArrayType;
import netflexity.schema.software.bam.types._1.ProcessType;
import netflexity.schema.software.bam.types._1.QualifierType;
import netflexity.schema.software.bam.types._1.StageArrayType;
import netflexity.schema.software.bam.types._1.StageType;
import netflexity.schema.software.bam.types._1.TransactionDetailsType;
import netflexity.schema.software.bam.types._1.TransactionSummaryType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.bam.business.domain.model.BpmAttribute;
import com.netflexity.bam.business.domain.model.BpmCondition;
import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.domain.model.BpmFlowTransaction;
import com.netflexity.bam.business.domain.model.BpmFlowTransactionPayload;
import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.domain.model.BpmProcess;
import com.netflexity.bam.business.domain.model.BpmQualifier;
import com.netflexity.bam.business.domain.model.BpmStage;
import com.netflexity.bam.business.domain.model.BpmTransaction;
import com.netflexity.bam.business.domain.model.BpmTransactionSummary;

/**
 * @author
 *
 */
public class DomainUtil {

	private static final Log logger = LogFactory.getLog(DomainUtil.class);
	
    public static final Character NO = 'N';
    public static final Character YES = 'Y';
    public static ObjectFactory bpmTypesFactory = new ObjectFactory();
    public static netflexity.schema.software.common.types._1.ObjectFactory commonObjectTypeFactory = new netflexity.schema.software.common.types._1.ObjectFactory();

    /**
     * @param dateTime
     * @return
     */
    public static Calendar toCalendar(Long dateTime) {
        if (dateTime != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(dateTime));
        }
        return null;
    }

    /**
     * @param Calendar
     * @return
     */
    public static Long toLong(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return cal.getTime().getTime();
    }

    /**
     * @param integer
     * @return
     */
    public static Long toLong(BigInteger integer) {
        if (integer == null) {
            return null;
        }
        return integer.longValue();
    }

    /**
     * @param str
     * @return
     */
    public static long toLong(String str) {
        if (str == null) {
            return 0;
        }
        return Long.parseLong(str);
    }

    /**
     * @param array
     * @return
     */
    public static List<Byte> toList(byte[] array) {
    	if(array != null && array.length > 0) {
    		List<Byte> rsp = new ArrayList<Byte>();
    		for (byte value : array) {
    			rsp.add(value);
    		}
    		return rsp;
    	}
    	return null;
    }
    
    /**
     * @param processes
     * @return
     */
    public static ProcessArrayType toXmlType(List<BpmProcess> processes) {
        ProcessArrayType processArrayType = bpmTypesFactory.createProcessArrayType();
        for (BpmProcess process : processes) {
            processArrayType.getProcess().add(toXmlType(process));
        }
        return processArrayType;
    }

    /**
     * @param processStages
     * @return
     */
    public static StageArrayType toXmlType(List<BpmStage> stages) {
        StageArrayType processStageArrayType = bpmTypesFactory.createStageArrayType();
        for (BpmStage stage : stages) {
            processStageArrayType.getStages().add(toXmlType(stage, true));
        }
        return processStageArrayType;
    }

    /**
     * @param processStages
     * @return
     */
    public static StageArrayType toXmlType(List<BpmStage> stages, boolean loadAttributes) {
        StageArrayType processStageArrayType = bpmTypesFactory.createStageArrayType();
        for (BpmStage stage : stages) {
            processStageArrayType.getStages().add(toXmlType(stage, loadAttributes));
        }
        return processStageArrayType;
    }

    /**
     * @param processAttributes
     * @return
     */
    public static AttributeArrayType toXmlType(List<BpmAttribute> attributes) {
        AttributeArrayType processAttributeArrayType = bpmTypesFactory.createAttributeArrayType();
        for (BpmAttribute attribute : attributes) {
            processAttributeArrayType.getAttributes().add(toXmlType(attribute));
        }
        return processAttributeArrayType;
    }

    /**
     * @param processes
     * @return
     */
    public static List<BpmProcess> toDomainType(ProcessArrayType processArrayType) {
        List<BpmProcess> processes = new ArrayList<BpmProcess>();
        for (ProcessType processType : processArrayType.getProcess()) {
            processes.add(toDomainType(processType));
        }
        return processes;
    }

    /**
     * @param processStages
     * @return
     */
    public static List<BpmStage> toDomainType(StageArrayType processStageArrayType) {
        List<BpmStage> processStages = new ArrayList<BpmStage>();
        for (StageType processStageType : processStageArrayType.getStages()) {
            processStages.add(toDomainType(processStageType));
        }
        return processStages;
    }

    /**
     * @param processAttributes
     * @return
     */
    public static List<BpmAttribute> toDomainType(AttributeArrayType processAttributeArrayType) {
        List<BpmAttribute> processAttributes = new ArrayList<BpmAttribute>();
        for (AttributeType processAttributeType : processAttributeArrayType.getAttributes()) {
            processAttributes.add(toDomainType(processAttributeType));
        }
        return processAttributes;
    }

    /**
     * @param Process
     * @return
     */
    public static ProcessType toXmlType(BpmProcess process) {
        if (process == null) {
            return null;
        }
        ProcessType processType = bpmTypesFactory.createProcessType();
        processType.setId(String.valueOf(process.getId()));
        processType.setName(process.getName());
        processType.setDescription(process.getDescription());
        processType.setPartyId(process.getPartyId());
        processType.setChangeBy(process.getChangeBy());
        processType.setChangeDate(toCalendar(process.getChangeDate()));
        FlowArrayType flowsArrayType = bpmTypesFactory.createFlowArrayType();
//        Set<BpmFlow> flows = process.getBpmFlows();
//        if(flows != null) {
//            for (BpmFlow processFlow : flows) {
//                flowsArrayType.getFlows().add(toXmlType(processFlow));
//            }
//        }
//        if (!flowsArrayType.getFlows().isEmpty()) {
//            processType.setFlows(flowsArrayType);
//        }
        return processType;
    }

    /**
     * @param Process
     * @return
     */
    public static BpmProcess toDomainType(ProcessType processType) {
        BpmProcess process = new BpmProcess();
        if (StringUtils.isNotBlank(processType.getId())) {
            process.setId(toLong(processType.getId()));
        }
        process.setName(processType.getName());
        process.setDescription(processType.getDescription());
        process.setPartyId(processType.getPartyId());
        process.setChangeBy(processType.getChangeBy());
        if (processType.getChangeDate() != null) {
            process.setChangeDate(toLong(processType.getChangeDate()));
        }
        FlowArrayType flowsArrayType = processType.getFlows();
        if (flowsArrayType != null) {
            for (FlowType processFlowType : flowsArrayType.getFlows()) {
                process.getBpmFlows().add(toDomainType(processFlowType));
            }
        }
        return process;
    }

    /**
     * @param BpmStage
     * @return
     */
    public static StageType toXmlType(BpmStage stage, boolean loadAttributes) {
        if (stage == null) {
            return null;
        }
        StageType xsdStage = bpmTypesFactory.createStageType();
        xsdStage.setId(String.valueOf(stage.getId()));
        xsdStage.setName(stage.getName());
        xsdStage.setDescription(stage.getDescription());
        xsdStage.setPartyId(stage.getPartyId());
        xsdStage.setChangeBy(stage.getChangeBy());
        if (stage.getChangeDate() != null) {
            xsdStage.setChangeDate(toCalendar(stage.getChangeDate()));
        }
        BpmQualifier qualifier = stage.getBpmQualifier();
        if (qualifier != null && qualifier.getId() != null) {
            xsdStage.setQualifier(DomainUtil.toXmlType(qualifier, loadAttributes));
        }
        if (loadAttributes) {
            AttributeArrayType attrArrayType = bpmTypesFactory.createAttributeArrayType();
            Set<BpmAttribute> attrs = stage.getBpmAttributes();
            if (attrs != null) {
                for (BpmAttribute attr : attrs) {
                	attrArrayType.getAttributes().add(toXmlType(attr));
                }
                if (!attrArrayType.getAttributes().isEmpty()) {
                    xsdStage.setStageAttributes(attrArrayType);
                }
            }
        }
        return xsdStage;
    }

    /**
     * @param StageType
     * @return
     */
    public static BpmStage toDomainType(StageType stageType) {
        BpmStage stage = new BpmStage();
        if (StringUtils.isNotBlank(stageType.getId())) {
            stage.setId(toLong(stageType.getId()));
        }
        stage.setName(stageType.getName());
        stage.setDescription(stageType.getDescription());
        stage.setPartyId(stageType.getPartyId());
        stage.setChangeBy(stageType.getChangeBy());
        if (stageType.getChangeDate() != null) {
            stage.setChangeDate(toLong(stageType.getChangeDate()));
        }

        QualifierType qualifierType = stageType.getQualifier();

        if (qualifierType != null && qualifierType.getId() != null) {
            stage.setBpmQualifier(DomainUtil.toDomainType(qualifierType));
        }

        AttributeArrayType stageAttributeArrayType = stageType.getStageAttributes();
        if (stageAttributeArrayType != null) {
            for (AttributeType stageAttributeType : stageAttributeArrayType.getAttributes()) {
                stage.getBpmAttributes().add(toDomainType(stageAttributeType));
            }
        }
        return stage;
    }

    /**
     * @param attribute
     * @return
     */
    public static AttributeType toXmlType(BpmAttribute attribute) {
        if (attribute == null) {
            return null;
        }

        AttributeType attrType = bpmTypesFactory.createAttributeType();
        attrType.setId(String.valueOf(attribute.getId()));
        attrType.setPartyId(attribute.getPartyId());
        attrType.setName(attribute.getName());
        attrType.setExpression(attribute.getExpression());
        attrType.setDelimiter(attribute.getDelimiter());
        if (attribute.getDatatype() != null) {
            attrType.setDatatype(attribute.getDatatype());
        }
        if (attribute.getStartPosition() != null) {
            attrType.setStartPosition(attribute.getStartPosition());
        }
        if (attribute.getEndPosition() != null) {
            attrType.setEndPosition(attribute.getEndPosition());
        }
        if (attribute.getPosition() != null) {
            attrType.setPosition(attribute.getPosition());
        }
        if (YES.toString().equalsIgnoreCase(String.valueOf(attribute.getRequired()))) {
            attrType.setRequired(true);
        }
        attrType.setChangeBy(attribute.getChangeBy());
        attrType.setChangeDate(toCalendar(attribute.getChangeDate()));
        return attrType;
    }

    /**
     * @param attrType
     * @return
     */
    public static BpmAttribute toDomainType(AttributeType attrType) {
        BpmAttribute attribute = new BpmAttribute();
        if (StringUtils.isNotBlank(attrType.getId())) {
            attribute.setId(Long.parseLong(attrType.getId()));
        }
        attribute.setPartyId(attrType.getPartyId());
        attribute.setName(attrType.getName());
        attribute.setExpression(attrType.getExpression());
        attribute.setDelimiter(attrType.getDelimiter());
        if (attrType.getDatatype() != null) {
            attribute.setDatatype(attrType.getDatatype());
        }
        if (attrType.getStartPosition() != null) {
            attribute.setStartPosition(attrType.getStartPosition());
        }
        if (attrType.getEndPosition() != null) {
            attribute.setEndPosition(attrType.getEndPosition());
        }
        if (attrType.getPosition() != null) {
            attribute.setPosition(attrType.getPosition());
        }
        if (attrType.isRequired()) {
            attribute.setRequired(YES);
        } else {
            attribute.setRequired(NO);
        }
        attribute.setChangeBy(attrType.getChangeBy());
        if (attrType.getChangeDate() != null) {
            attribute.setChangeDate(toLong(attrType.getChangeDate()));
        }
        return attribute;
    }

    /**
     * @param BpmFlow
     * @return
     */
    public static FlowType toXmlType(BpmFlow flow) {
        if (flow == null) {
            return null;
        }
        FlowType flowType = bpmTypesFactory.createFlowType();
        flowType.setId(String.valueOf(flow.getId()));
        flowType.setUuid(flow.getUuid());
        logger.info("flow uuid: " + flowType.getUuid());
        BpmProcess process = flow.getBpmProcess();
        if (process != null && process.getId() != null) {
            flowType.setProcessId(String.valueOf(process.getId()));
            flowType.setProcess(toXmlType(process));
        }
        BpmQualifier qualifier = flow.getBpmQualifier();
        if (qualifier != null && qualifier.getId() != null) {
            flowType.setTransactionQualifierId(qualifier.getId().toString());
        }
        BpmStage stage = flow.getBpmStage();
        if (stage != null && stage.getId() != null) {
            flowType.setStageId(String.valueOf(stage.getId()));
            flowType.setStage(toXmlType(stage, false));
        }
        flowType.setStageTypeId(flow.getStageTypeCode());
        return flowType;
    }

    /**
     * @param FlowType
     * @return
     */
    public static BpmFlow toDomainType(FlowType flowType) {
        BpmFlow flow = new BpmFlow();
        if (StringUtils.isNotBlank(flowType.getId())) {
            flow.setId(Long.parseLong(flowType.getId()));
        }
        if (StringUtils.isNotBlank(flowType.getProcessId())) {
            BpmProcess process = new BpmProcess();
            process.setId(Long.parseLong(flowType.getProcessId()));
            flow.setBpmProcess(process);
        }
        if (StringUtils.isNotBlank(flowType.getStageId())) {
            BpmStage stage = new BpmStage();
            stage.setId(Long.parseLong(flowType.getStageId()));
            flow.setBpmStage(stage);
        }
        flow.setUuid(flowType.getUuid());
//        if (StringUtils.isBlank(flowType.getUuid())) {
//            flowType.setUuid(UUID.randomUUID().toString());
//        }
//        if (flowType.getStageTypeId() > 0) {
//            BpmStageType stageType = new BpmStageType();
//            stageType.setId(flowType.getStageTypeId());
//            flow.setBpmStageType(stageType);
//        }
        flow.setStageTypeCode(flowType.getStageTypeId());
//        if (flowType.getStage() != null) {
//            flow.setBpmStage(toDomainType(flowType.getStage()));
//        }
        return flow;
    }

    /**
     * @param bpmQualifier
     * @param loadAttributes
     * @return
     */
    public static QualifierType toXmlType(BpmQualifier bpmQualifier, boolean loadAttributes) {
        QualifierType qualifierType = bpmTypesFactory.createQualifierType();
        qualifierType.setId(bpmQualifier.getId().toString());
        qualifierType.setName(bpmQualifier.getName());
        qualifierType.setPartyId(bpmQualifier.getPartyId());

        if (loadAttributes) {
            AttributeArrayType attributeArrayType = bpmTypesFactory.createAttributeArrayType();

            if (bpmQualifier.getBpmQualifierAttributes() != null) {
	            for (Object bpmAttribute : bpmQualifier.getBpmQualifierAttributes()) {
	                attributeArrayType.getAttributes().add(DomainUtil.toXmlType((BpmAttribute) bpmAttribute));
	            }
            }


            qualifierType.setQualifierAttributes(attributeArrayType);
        }

        return qualifierType;
    }

    /**
     * @param qualifierType
     * @return
     */
    public static BpmQualifier toDomainType(QualifierType qualifierType) {
        BpmQualifier bpmQualifier = new BpmQualifier();
        if (StringUtils.isNotBlank(qualifierType.getId())) {
        	bpmQualifier.setId(new Long(qualifierType.getId()));
        }
        bpmQualifier.setName(qualifierType.getName());
        bpmQualifier.setPartyId(qualifierType.getPartyId());
        //TODO add flows, attributes, stages
        return bpmQualifier;
    }

    
    /**
     * @param monitor
     * @return
     */
    public static MonitorType toXmlType(BpmMonitor monitor) {
    	if(monitor == null) {
    		return null;
    	}
    	MonitorType meta = new MonitorType();
    	meta.setCriticalityTypeCode(monitor.getCriticalityTypeCode());
    	meta.setName(monitor.getName());
    	meta.setMessage(monitor.getMessage());
    	meta.setChangeBy(monitor.getChangeBy());
    	meta.setId(monitor.getId());
    	meta.setResourceId(monitor.getResourceId());
    	meta.setOccurrenceInterval(monitor.getOccurrenceInterval() != null ? new BigInteger(String.valueOf(monitor.getOccurrenceInterval())) : null);
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(monitor.getChangeDate());
    	meta.setChangeDate(calendar);
    	meta.setStatus(String.valueOf(monitor.getStatus()));
    	if(meta.getStatus() != null) {
    		meta.setStatus(meta.getStatus().trim());
    	}
    	return meta;
    }
    
    /**
     * @param transaction
     * @return
     */
    public static TransactionDetailsType toXmlType(BpmTransaction transaction) {
    	TransactionDetailsType trn = new TransactionDetailsType();
        trn.setId(transaction.getId());
        GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(transaction.getStartDate());
        trn.setStartDate(calendar);
        if(transaction.getEndDate() != null){
        	calendar.setTimeInMillis(transaction.getEndDate());
            trn.setEndDate(calendar);
        }
        trn.setUuid(transaction.getUuid());
        trn.setTransactionStatusCode(transaction.getTransactionStatusCode());
        if(transaction.getBpmFlowTransactions() != null){
            String processName = null;
            long processId = -1L;
            List<FlowTransactionDetailsType> list = new ArrayList<FlowTransactionDetailsType>(transaction.getBpmFlowTransactions().size());
            for(BpmFlowTransaction flowTransaction : transaction.getBpmFlowTransactions()) {
                list.add(toXmlType(flowTransaction));
                BpmFlow flow = flowTransaction.getBpmFlow();
                if(flow != null){
                    BpmProcess process = flow.getBpmProcess();
                    if(process != null){
                        processName = process.getName();
                        processId = process.getId();
                    }
                }
            }
            trn.getBpmFlowTransactions().addAll(list);
            trn.setProcessId(processId);
            trn.setProcessName(processName);
        }
        return trn;
    }
    
    /**
     * @param transaction
     * @return
     */
    public static FlowTransactionDetailsType toXmlType(BpmFlowTransaction transaction) {
    	FlowTransactionDetailsType rv = new FlowTransactionDetailsType();
        rv.setId(transaction.getId());
        GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(transaction.getTransactionDate());
        rv.setTransactionDate(calendar);
        if(transaction.getBpmFlowTransactionPayloads() != null){
            List<FlowTransactionPayloadDetailsType> list = new ArrayList<FlowTransactionPayloadDetailsType>(transaction.getBpmFlowTransactionPayloads().size());
            for(BpmFlowTransactionPayload payload : transaction.getBpmFlowTransactionPayloads()) {
                list.add(toXmlType(payload));
            }
            rv.getBpmFlowTransactionPayloads().addAll(list);
        }
        BpmFlow flow = transaction.getBpmFlow();
        if(flow != null){
            BpmStage stage = flow.getBpmStage();
            if(stage != null){
                rv.setStageName(stage.getName());
            }
        }
        return rv;
    }
    
    /**
     * @param payload
     * @return
     */
    public static FlowTransactionPayloadDetailsType toXmlType(BpmFlowTransactionPayload payload) {
    	FlowTransactionPayloadDetailsType rv = new FlowTransactionPayloadDetailsType();
        rv.setId(payload.getId());
        rv.getPayloads().addAll(toList(payload.getPayload()));
        return rv;
    }
    
    /**
     * @param sumary
     * @return
     */
    public static TransactionSummaryType toXmlType(BpmTransactionSummary sumary) {
    	if(sumary == null) {
    		return null;
    	}
    	TransactionSummaryType meta = new TransactionSummaryType();
    	meta.setProcessId(sumary.getProcessId());
    	meta.setName(sumary.getName());
    	meta.setTotal(new BigInteger(String.valueOf(sumary.getTotal())));
    	return null;
    }
    
    /**
     * @param flowTransaction
     * @return
     */
    public static FlowTransactionType toFlowTransactionXmlType(BpmFlowTransaction flowTransaction) {
    	if(flowTransaction == null) {
    		return null;
    	}
        FlowTransactionType rv = bpmTypesFactory.createFlowTransactionType();
        rv.setId(String.valueOf(flowTransaction.getId()));
        BpmTransaction transaction = flowTransaction.getBpmTransaction();
        if (transaction != null) {
            rv.setTransactionId(String.valueOf(transaction.getUuid()));
        }
        BpmFlow flow = flowTransaction.getBpmFlow();
        if (flow != null) {
            rv.setFlowId(String.valueOf(flow.getId()));
        }
        rv.setTransactionDate(toCalendar(flowTransaction.getTransactionDate()));
        return rv;
    }
    
    
    /**
     * @param monitor
     * @return
     */
    public static BpmMonitor toDomainType(MonitorType monitor) {
    	if(monitor == null) {
    		return null;
    	}
    	BpmMonitor entity = new BpmMonitor();
    	entity.setCriticalityTypeCode(monitor.getCriticalityTypeCode());
    	entity.setName(monitor.getName());
    	entity.setMessage(monitor.getMessage());
    	entity.setChangeBy(monitor.getChangeBy());
    	entity.setId(monitor.getId());
    	entity.setResourceId(monitor.getResourceId() != null ? monitor.getResourceId() : 0);
    	entity.setOccurrenceInterval(monitor.getOccurrenceInterval() != null ? monitor.getOccurrenceInterval().intValue() : null);
    	entity.setChangeDate(monitor.getChangeDate().getTimeInMillis());
    	if(monitor.getStatus() != null && monitor.getStatus().length() > 0) {
    		entity.setStatus(monitor.getStatus().toCharArray()[0]);
    	}
    	if(monitor.getConditions() != null && !monitor.getConditions().isEmpty()) {
    		entity.setBpmConditions(new HashSet<BpmCondition>());
    		for (ConditionType condition : monitor.getConditions()) {
    			entity.getBpmConditions().add(DomainUtil.toDomainType(condition));
    		}
    	}
    	return entity;
    }
    
    /**
     * @param condition
     * @return
     */
    public static BpmCondition toDomainType(ConditionType condition) {
    	if(condition == null) {
    		return null;
    	}
    	BpmCondition entity = new BpmCondition();
    	entity.setPartyId(condition.getPartyId());
    	entity.setName(condition.getName());
    	entity.setExpression(condition.getExpression());
    	return entity;
    }
}


























