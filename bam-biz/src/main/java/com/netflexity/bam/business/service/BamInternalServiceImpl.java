/**
 * 
 */
package com.netflexity.bam.business.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import netflexity.schema.software.bam.messages._1.AcknowledgeTransactionTracking;
import netflexity.schema.software.bam.messages._1.CreateAttribute;
import netflexity.schema.software.bam.messages._1.CreateAttributeResponse;
import netflexity.schema.software.bam.messages._1.CreateMonitor;
import netflexity.schema.software.bam.messages._1.CreateMonitorResponse;
import netflexity.schema.software.bam.messages._1.CreateFlow;
import netflexity.schema.software.bam.messages._1.CreateFlowResponse;
import netflexity.schema.software.bam.messages._1.CreateProcess;
import netflexity.schema.software.bam.messages._1.CreateProcessResponse;
import netflexity.schema.software.bam.messages._1.CreateStage;
import netflexity.schema.software.bam.messages._1.CreateStageResponse;
import netflexity.schema.software.bam.messages._1.DeleteAttributes;
import netflexity.schema.software.bam.messages._1.DeleteAttributesResponse;
import netflexity.schema.software.bam.messages._1.DeleteProcesses;
import netflexity.schema.software.bam.messages._1.DeleteFlowsResponse;
import netflexity.schema.software.bam.messages._1.DeleteFlows;
import netflexity.schema.software.bam.messages._1.DeleteProcessesResponse;
import netflexity.schema.software.bam.messages._1.DeleteStages;
import netflexity.schema.software.bam.messages._1.DeleteStagesResponse;
import netflexity.schema.software.bam.messages._1.GetAttributes;
import netflexity.schema.software.bam.messages._1.GetAttributesResponse;
import netflexity.schema.software.bam.messages._1.GetFlows;
import netflexity.schema.software.bam.messages._1.GetFlowsResponse;
import netflexity.schema.software.bam.messages._1.GetProcesses;
import netflexity.schema.software.bam.messages._1.GetProcessesResponse;
import netflexity.schema.software.bam.messages._1.GetStages;
import netflexity.schema.software.bam.messages._1.GetStagesResponse;
import netflexity.schema.software.bam.messages._1.GetTransactionSummary;
import netflexity.schema.software.bam.messages._1.GetTransactionSummaryResponse;
import netflexity.schema.software.bam.messages._1.GetTransactions;
import netflexity.schema.software.bam.messages._1.GetTransactionsResponse;
import netflexity.schema.software.bam.messages._1.ProcessTransactionTracking;
import netflexity.schema.software.bam.messages._1.StartMonitor;
import netflexity.schema.software.bam.messages._1.UpdateAttribute;
import netflexity.schema.software.bam.messages._1.UpdateAttributeResponse;
import netflexity.schema.software.bam.messages._1.UpdateFlow;
import netflexity.schema.software.bam.messages._1.UpdateFlowResponse;
import netflexity.schema.software.bam.messages._1.UpdateProcess;
import netflexity.schema.software.bam.messages._1.UpdateProcessResponse;
import netflexity.schema.software.bam.messages._1.UpdateStage;
import netflexity.schema.software.bam.messages._1.UpdateStageResponse;
import netflexity.schema.software.bam.types._1.AttributeType;
import netflexity.schema.software.bam.types._1.FlowTransactionType;
import netflexity.schema.software.bam.types._1.FlowType;
import netflexity.schema.software.bam.types._1.ProcessType;
import netflexity.schema.software.bam.types._1.StageType;
import netflexity.schema.software.bam.types._1.TransactionDetailsType;
import netflexity.schema.software.bam.types._1.TransactionSummaryType;
import netflexity.ws.software.bam.services._1_0.BAM;
import netflexity.ws.software.bam.services._1_0.BAMInternal;
import netflexity.ws.software.bam.services._1_0.BamMonitorService;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

import com.netflexity.bam.business.domain.model.BpmAttribute;
import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.domain.model.BpmFlowTransaction;
import com.netflexity.bam.business.domain.model.BpmFlowTransactionPayload;
import com.netflexity.bam.business.domain.model.BpmMonitor;
import com.netflexity.bam.business.domain.model.BpmProcess;
import com.netflexity.bam.business.domain.model.BpmStage;
import com.netflexity.bam.business.domain.model.BpmTransaction;
import com.netflexity.bam.business.domain.model.BpmTransactionSummary;
import com.netflexity.bam.business.repository.MetadataRepository;
import com.netflexity.bam.business.repository.TransactionProcessorRepository;
import com.netflexity.bam.business.utils.DomainUtil;

/**
 * @author Alexei SCLIFOS
 *
 */
@Transactional
public class BamInternalServiceImpl implements BAMInternal, BAM, BamServiceErrors {

	/*message source*/
	private ResourceBundleMessageSource serviceErrorsMessageSource;
	
	/*repositories*/
	private MetadataRepository metadataRepository;
	private TransactionProcessorRepository transactionProcessorRepository;
	
	/*services*/
	private SecurityManagementService securityManagementService;
	private BamMonitorService bamMonitorService;
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#createAttribute(netflexity.schema.software.bam.messages._1.CreateAttribute)
	 */
	public CreateAttributeResponse createAttribute(CreateAttribute body) {
		CreateAttributeResponse response = new CreateAttributeResponse();
		body.getAttr().setPartyId(securityManagementService.getPartyId(body));
		response.setAttr(DomainUtil.toXmlType(metadataRepository.createAttribute(DomainUtil.toDomainType(body.getAttr()))));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#createMonitor(netflexity.schema.software.bam.messages._1.CreateMonitor)
	 */
	public CreateMonitorResponse createMonitor(CreateMonitor body) {
		CreateMonitorResponse response = new CreateMonitorResponse();
		/*creating the monitor*/
		BpmMonitor monitor = metadataRepository.createMonitor(DomainUtil.toDomainType(body.getMonitor()));
		/*starting the monitor*/
		StartMonitor startMonitorRequest = new StartMonitor();
		startMonitorRequest.setMonitor(body.getMonitor());
		bamMonitorService.startMonitor(startMonitorRequest);
		/*setting the response*/
		response.setMonitor(DomainUtil.toXmlType(monitor));
		return response;
	}
	
	public CreateFlowResponse createFlow(CreateFlow body) {
		CreateFlowResponse response = new CreateFlowResponse();
		//body.getFlow().setPartyId(securityManagementService.getPartyId(body));
		response.setFlow(DomainUtil.toXmlType(metadataRepository.createFlow(DomainUtil.toDomainType(body.getFlow()))));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#createProcess(netflexity.schema.software.bam.messages._1.CreateProcess)
	 */
	public CreateProcessResponse createProcess(CreateProcess body) {
		CreateProcessResponse response = new CreateProcessResponse();
		body.getProcess().setPartyId(securityManagementService.getPartyId(body));
		response.setProcess(DomainUtil.toXmlType(metadataRepository.createProcess(DomainUtil.toDomainType(body.getProcess()))));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#createStage(netflexity.schema.software.bam.messages._1.CreateStage)
	 */
	public CreateStageResponse createStage(CreateStage body) {
		CreateStageResponse response = new CreateStageResponse();
		body.getStage().setPartyId(securityManagementService.getPartyId(body));
		response.setStage(DomainUtil.toXmlType(metadataRepository.createStage(DomainUtil.toDomainType(body.getStage())), false));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#deleteAttributes(netflexity.schema.software.bam.messages._1.DeleteAttributes)
	 */
	public DeleteAttributesResponse deleteAttributes(DeleteAttributes body) {
		DeleteAttributesResponse response = new DeleteAttributesResponse();
		if(body != null && body.getIds() != null) {
			metadataRepository.removeAttributes(toLongArray(body.getIds()));
			response.setResponse(true);
		}
		return response;
	}
	
	public DeleteFlowsResponse deleteFlows(DeleteFlows body) {
		DeleteFlowsResponse response = new DeleteFlowsResponse();
		if(body != null && body.getIds() != null) {
			metadataRepository.removeFlows(toLongArray(body.getIds()));
			response.setResponse(true);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#deleteProcesses(netflexity.schema.software.bam.messages._1.DeleteProcesses)
	 */
	public DeleteProcessesResponse deleteProcesses(DeleteProcesses body) {
		DeleteProcessesResponse response = new DeleteProcessesResponse();
		if(body != null && body.getIds() != null) {
			metadataRepository.removeProcesses(toLongArray(body.getIds()));
			response.setResponse(true);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#deleteStages(netflexity.schema.software.bam.messages._1.DeleteStages)
	 */
	public DeleteStagesResponse deleteStages(DeleteStages body) {
		DeleteStagesResponse response = new DeleteStagesResponse();
		if(body != null && body.getIds() != null) {
			metadataRepository.removeStages(toLongArray(body.getIds()));
			response.setResponse(true);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#getAttributes(netflexity.schema.software.bam.messages._1.GetAttributes)
	 */
	public GetAttributesResponse getAttributes(GetAttributes body) {
		body.setPartyId(securityManagementService.getPartyId(body));
		List<BpmAttribute> attrs = metadataRepository.getAttributes(body.getPartyId());
		List<AttributeType> attrsResponse = null;
		if(attrs != null && !attrs.isEmpty()) {
			attrsResponse = new ArrayList<AttributeType>();
			for (BpmAttribute attr : attrs) {
				attrsResponse.add(DomainUtil.toXmlType(attr));
			}
		}
		GetAttributesResponse response = new GetAttributesResponse();
		if(attrsResponse != null && !attrsResponse.isEmpty()) {
			response.getAttrs().addAll(attrsResponse);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#getFlows(netflexity.schema.software.bam.messages._1.GetFlows)
	 */
	public GetFlowsResponse getFlows(GetFlows body) {
		List<BpmFlow> flows = new ArrayList<BpmFlow>();
		if (StringUtils.isNotBlank(body.getProcessId())) {
			flows = metadataRepository.getFlows(Long.parseLong(body.getProcessId()));
		}
		else if (StringUtils.isNotBlank(body.getFlowId())) {
			flows.add(metadataRepository.getFlow(Long.parseLong(body.getFlowId())));
		}
		else {
			flows = metadataRepository.getFlows();
		}
		List<FlowType> flowsResponse = null;
		if (flows != null && !flows.isEmpty()) {
			flowsResponse = new ArrayList<FlowType>();
			for (BpmFlow flow : flows) {
				flowsResponse.add(DomainUtil.toXmlType(flow));
			}
		}
		GetFlowsResponse response = new GetFlowsResponse();
		if (flowsResponse != null && !flowsResponse.isEmpty()) {
			response.getFlows().addAll(flowsResponse);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#getProcesses(netflexity.schema.software.bam.messages._1.GetProcesses)
	 */
	public GetProcessesResponse getProcesses(GetProcesses body) {
		body.setPartyId(securityManagementService.getPartyId(body));
		List<BpmProcess> processes = new ArrayList<BpmProcess>();
		if (StringUtils.isBlank(body.getProcessId())) {
			processes = metadataRepository.getProcesses(body.getPartyId());
		} else {
			processes.add(metadataRepository.getProcess(body.getPartyId(), Long.parseLong(body.getProcessId())));
		}
		List<ProcessType> processesResponse = null;
		if(processes != null && !processes.isEmpty()) {
			processesResponse = new ArrayList<ProcessType>();
			for (BpmProcess process : processes) {
				processesResponse.add(DomainUtil.toXmlType(process));
			}
		}
		GetProcessesResponse response = new GetProcessesResponse();
		if(processesResponse != null && !processesResponse.isEmpty()) {
			response.getProcesses().addAll(processesResponse);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#getStages(netflexity.schema.software.bam.messages._1.GetStages)
	 */
	public GetStagesResponse getStages(GetStages body) {
		body.setPartyId(securityManagementService.getPartyId(body));
		List<BpmStage> stages = new ArrayList<BpmStage>();
		if (StringUtils.isBlank(body.getStageId())) {
			stages = metadataRepository.getStages(body.getPartyId());;
		} else {
			stages.add(metadataRepository.getStage(body.getPartyId(), Long.parseLong(body.getStageId())));
		}
		List<StageType> stagesResponse = null;
		if(stages != null && !stages.isEmpty()) {
			stagesResponse = new ArrayList<StageType>();
			for (BpmStage stage : stages) {
				stagesResponse.add(DomainUtil.toXmlType(stage, true));
			}
		}
		GetStagesResponse response = new GetStagesResponse();
		if(stagesResponse != null && !stagesResponse.isEmpty()) {
			response.getStages().addAll(stagesResponse);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#getTransactions(netflexity.schema.software.bam.messages._1.GetTransactions)
	 */
	public GetTransactionsResponse getTransactions(GetTransactions body) {
		List<BpmTransaction> transactions;
		/*if (body.getLimit() != null) {
			transactions = transactionProcessorRepository.getTransactions(body.getLimit().intValue());
		}
		else if (body.getPageNumber() != null && body.getPageSize() != null) {
			transactions = transactionProcessorRepository.getTransactions(body.getPageNumber(), body.getPageSize());
		}
		else {
			transactions = transactionProcessorRepository.getTransactions();
		}*/
		transactions = transactionProcessorRepository.getTransactions(body);
		
		List<TransactionDetailsType> transactionsResponse = null;
		if(transactions != null && !transactions.isEmpty()) {
			transactionsResponse = new ArrayList<TransactionDetailsType>();
			for (BpmTransaction transaction : transactions) {
				transactionsResponse.add(DomainUtil.toXmlType(transaction));
			}
		}
		GetTransactionsResponse response = new GetTransactionsResponse();
		if(transactionsResponse != null && !transactionsResponse.isEmpty()) {
			response.getTransactions().addAll(transactionsResponse);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#getTransactionSummary(netflexity.schema.software.bam.messages._1.GetTransactionSummary)
	 */
	public GetTransactionSummaryResponse getTransactionSummary(GetTransactionSummary body) {
		body.setPartyId(securityManagementService.getPartyId(body));
		List<BpmTransactionSummary> summaries = transactionProcessorRepository.getTransactionSummary(body.getPartyId());
		List<TransactionSummaryType> summariesResponse = null;
		if(summaries != null && !summaries.isEmpty()) {
			summariesResponse = new ArrayList<TransactionSummaryType>();
			for (BpmTransactionSummary sumary : summaries) {
				summariesResponse.add(DomainUtil.toXmlType(sumary));
			}
		}
		GetTransactionSummaryResponse response = new GetTransactionSummaryResponse();
		if(summariesResponse != null && !summariesResponse.isEmpty()) {
			response.getTransactions().addAll(summariesResponse);
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#updateAttribute(netflexity.schema.software.bam.messages._1.UpdateAttribute)
	 */
	public UpdateAttributeResponse updateAttribute(UpdateAttribute body) {
		UpdateAttributeResponse response = new UpdateAttributeResponse();
		body.getAttr().setPartyId(securityManagementService.getPartyId(body));
		response.setAttr(DomainUtil.toXmlType(metadataRepository.updateAttribute(DomainUtil.toDomainType(body.getAttr()))));
		return response;
	}
	
	public UpdateFlowResponse updateFlow(UpdateFlow body) {
		UpdateFlowResponse response = new UpdateFlowResponse();
		response.setFlow(DomainUtil.toXmlType(metadataRepository.updateFlow(DomainUtil.toDomainType(body.getFlow()))));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#updateProcess(netflexity.schema.software.bam.messages._1.UpdateProcess)
	 */
	public UpdateProcessResponse updateProcess(UpdateProcess body) {
		UpdateProcessResponse response = new UpdateProcessResponse();
		body.getProcess().setPartyId(securityManagementService.getPartyId(body));
		response.setProcess(DomainUtil.toXmlType(metadataRepository.updateProcess(DomainUtil.toDomainType(body.getProcess()))));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAMInternal#updateStage(netflexity.schema.software.bam.messages._1.UpdateStage)
	 */
	public UpdateStageResponse updateStage(UpdateStage body) {
		UpdateStageResponse response = new UpdateStageResponse();
		body.getStage().setPartyId(securityManagementService.getPartyId(body));
		response.setStage(DomainUtil.toXmlType(metadataRepository.updateStage(DomainUtil.toDomainType(body.getStage())), true));
		return response;
	}

	/* (non-Javadoc)
	 * @see netflexity.ws.software.bam.services._1_0.BAM#processTransactionTracking(netflexity.schema.software.bam.messages._1.ProcessTransactionTracking)
	 */
	public AcknowledgeTransactionTracking processTransactionTracking(ProcessTransactionTracking body) {
		try {
			AcknowledgeTransactionTracking response = new AcknowledgeTransactionTracking();
	        String transactionUuid = body.getTransactionUuid();
	        String flowUuid = body.getFlowUuid();
	        byte[] content = body.getTransactionContent();
	        /*Create process flow transaction.*/
	        FlowTransactionType flowTransactionType = createProcessFlowTransaction(transactionUuid, flowUuid, body.getTransactionDate() != null ? body.getTransactionDate() : null, content);
	        response.setFlowUuid(flowUuid);
	        response.setTransactionUuid(flowTransactionType.getTransactionId());
	        return response;
		} catch(Exception exc) {
			throw new RuntimeException(exc);
		}
	}
	
    /**
     * @param transactionUuid
     * @param flowUuid
     * @return
     * @throws BamServiceException
     */
    private FlowTransactionType createProcessFlowTransaction(final String transactionUuid, final String flowUuid, Calendar time, byte[] content) throws BamServiceException {
        if (StringUtils.isBlank(flowUuid)) {
            throw createServiceException(FLOW_UUID_REQUIRED_ERROR, new Object[]{});
        }
        BpmFlowTransaction processFlowTransaction = null;
        long now = time != null ? time.getTimeInMillis() : System.currentTimeMillis(); //making best attempt to preserve times
        BpmFlow processFlow = null;
        if (StringUtils.isNotBlank(flowUuid)) {
            processFlow = metadataRepository.findProcessFlowByUuid(flowUuid);
        }
        if (processFlow == null) {
        	throw createServiceException(FLOW_UUID_INVALID_ERROR, new Object[]{flowUuid});
        }
        String state = processFlow.getStageTypeCode();
        BpmTransaction processTransaction = null;
        if (StringUtils.isNotBlank(transactionUuid)) {
            processTransaction = transactionProcessorRepository.findProcessTransactionByUuid(transactionUuid);
        } else if (!BpmStage.START.equals(state) && !BpmStage.ALLINONE.equals(state)) {
            throw createServiceException(TRANSACTION_UUID_REQUIRED_ERROR, new Object[]{});
        } 
        if (processTransaction == null) {
        	// trying to create a new transaction
            processTransaction = new BpmTransaction();
            processTransaction.setUuid(StringUtils.isNotBlank(transactionUuid) ? transactionUuid : UUID.randomUUID().toString());
            processTransaction.setStartDate(now);
            processTransaction.setTransactionStatusCode(BpmTransaction.STARTED);
            processTransaction.setHealthCode(BpmTransaction.HEALTHY);
            processTransaction = transactionProcessorRepository.createTransaction(processTransaction);
        }

        processFlowTransaction = new BpmFlowTransaction();
        processFlowTransaction.setBpmFlow(processFlow);
        processFlowTransaction.setBpmTransaction(processTransaction);
        processFlowTransaction.setTransactionDate(now);

        Set<BpmFlowTransactionPayload> bpmFlowTransactionPayloads = new HashSet<BpmFlowTransactionPayload>();
        if (content != null && content.length > 0) {
        	BpmFlowTransactionPayload bpmFlowTransactionPayload = new BpmFlowTransactionPayload();
        	bpmFlowTransactionPayload.setPayload(content);
        	bpmFlowTransactionPayload.setBpmFlowTransaction(processFlowTransaction);
        	bpmFlowTransactionPayloads.add(bpmFlowTransactionPayload);
        }
        processFlowTransaction.setBpmFlowTransactionPayloads(bpmFlowTransactionPayloads);

        processTransaction.getBpmFlowTransactions().add(processFlowTransaction);
        metadataRepository.createFlowTransaction(processFlowTransaction);
        if (BpmStage.END.equals(state) || BpmStage.ALLINONE.equals(state) || BpmStage.ERROR.equals(state) ) {
        	processTransaction.setEndDate(now);
        	processTransaction.setTransactionStatusCode(BpmTransaction.STOPED);
        	if (BpmStage.ERROR.equals(state)) {
        		processTransaction.setHealthCode(BpmTransaction.ERROR);
        	}
        	transactionProcessorRepository.createTransaction(processTransaction);
        }
        
        return DomainUtil.toFlowTransactionXmlType(processFlowTransaction);
    }
	
    /**
     * @param errorCode
     * @param args
     * @return
     */
    private BamServiceException createServiceException(int errorCode, Object[] args) {
        return new BamServiceException(serviceErrorsMessageSource.getMessage(String.valueOf(errorCode), args, Locale.getDefault()));
    }

    /**
     * @param lst
     * @return
     */
    @SuppressWarnings("unchecked")
	private Long[] toLongArray(List lst) {
    	if(lst != null && !lst.isEmpty()) {
    		Long[] array = new Long[lst.size()];
    		int count = lst.size();
    		for (int index = 0 ; index < count ; index ++ ) {
    			array[index] = Long.parseLong((String)lst.get(index));
    		}
    		return array;
    	}
    	return null;
    }
    
	/**
	 * @param metadataRepository the metadataRepository to set
	 */
	public void setMetadataRepository(MetadataRepository metadataRepository) {
		this.metadataRepository = metadataRepository;
	}

	/**
	 * @param transactionProcessorRepository the transactionProcessorRepository to set
	 */
	public void setTransactionProcessorRepository(TransactionProcessorRepository transactionProcessorRepository) {
		this.transactionProcessorRepository = transactionProcessorRepository;
	}

	/**
	 * @param serviceErrorsMessageSource the serviceErrorsMessageSource to set
	 */
	public void setServiceErrorsMessageSource(ResourceBundleMessageSource serviceErrorsMessageSource) {
		this.serviceErrorsMessageSource = serviceErrorsMessageSource;
	}

	/**
	 * @param securityManagementService the securityManagementService to set
	 */
	public void setSecurityManagementService(SecurityManagementService securityManagementService) {
		this.securityManagementService = securityManagementService;
	}

	/**
	 * @param bamMonitorService the bamMonitorService to set
	 */
	public void setBamMonitorService(BamMonitorService bamMonitorService) {
		this.bamMonitorService = bamMonitorService;
	}
}
