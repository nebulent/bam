package com.netflexity.bam.business.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.utils.DomainUtil;

import netflexity.schema.software.bam.messages._1.GetFlows;
import netflexity.schema.software.bam.messages._1.GetTransactions;
import netflexity.schema.software.bam.messages._1.GetTransactionsResponse;
import netflexity.schema.software.bam.messages._1.GetUsers;
import netflexity.schema.software.bam.messages._1.GetUsersResponse;
import netflexity.schema.software.bam.messages._1.UpdateFlow;
import netflexity.schema.software.bam.types._1.FlowType;
import netflexity.schema.software.bam.types._1.TransactionDetailsType;
import netflexity.schema.software.bam.types._1.UserType;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring/test-bam.xml"})
public class ServiceTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
	
	@Autowired
	private BAMInternal bamInternal;
	
	@Test
	public void testGetFlowsByProcessId() {
		GetFlows body = new GetFlows();
		String processId = "2";
		//body.setProcessId(processId);
		List<FlowType> flows = bamInternal.getFlows(body).getFlows();
		int i = 1;
		for (FlowType flow : flows) {
			LOGGER.debug("\tflow " + i++ + ":\n" + ToStringBuilder.reflectionToString(flow, ToStringStyle.MULTI_LINE_STYLE));
			//Assert.assertEquals(processId, flow.getProcessId());
		}
	}
	
	@Test
	public void testUpdateFlow() {
		GetFlows getFlows = new GetFlows();
		getFlows.setFlowId("1");
		FlowType flow = bamInternal.getFlows(getFlows).getFlows().get(0);
		/*Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		flow.setChangeDate(calendar);*/
		flow.setUuid("vasea3");
		UpdateFlow updateFlow = new UpdateFlow();
		updateFlow.setFlow(flow);
		flow = bamInternal.updateFlow(updateFlow).getFlow();
		LOGGER.debug(ToStringBuilder.reflectionToString(flow, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testGetTransactionsByCriteria() {
		GetTransactions getTransactions = new GetTransactions();
		getTransactions.setLimit(new BigInteger("8"));
		getTransactions.setQuery("131");
		getTransactions.setTransactionStatusCode("STOPED");
		getTransactions.setHealthCode("HEALTHY");
		GetTransactionsResponse getTransactionsResponse = bamInternal.getTransactions(getTransactions);
		List<TransactionDetailsType> transactions = getTransactionsResponse.getTransactions();
		int i = 1;
		for (TransactionDetailsType tr : transactions) {
			LOGGER.debug("\ttransaction " + i++ + ":\n" + ToStringBuilder.reflectionToString(tr, ToStringStyle.MULTI_LINE_STYLE));
		}
		LOGGER.debug("totalTransactions: " + getTransactionsResponse.getTotalTransactions());
	}
	
	@Test
	public void testGetUsers() {
		GetUsers getUsers = new GetUsers();
		GetUsersResponse getUsersResponse = bamInternal.getUsers(getUsers);
		int i = 1;
		for (UserType userType : getUsersResponse.getUsers()) {
			LOGGER.debug("user " + i++ + ": " + ToStringBuilder.reflectionToString(userType, ToStringStyle.MULTI_LINE_STYLE));
		}
	}

	public BAMInternal getBamInternal() {
		return bamInternal;
	}

	public void setBamInternal(BAMInternal bamInternal) {
		this.bamInternal = bamInternal;
	}
}
