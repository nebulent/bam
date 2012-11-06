package com.netflexity.bam.business.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflexity.bam.business.domain.model.BpmFlow;
import com.netflexity.bam.business.utils.DomainUtil;

import netflexity.schema.software.bam.messages._1.GetFlows;
import netflexity.schema.software.bam.types._1.FlowType;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/test-bam-biz-services.xml","classpath:META-INF/spring/test-bam.xml"})
public class ServiceTest {
	
	@Autowired
	private BAMInternal bamInternal;
	
	@Test
	public void testGetFlows() {
		GetFlows body = new GetFlows();
		List<BpmFlow> flows = new ArrayList<BpmFlow>();
		System.out.println("bamInternal: " + bamInternal);
		for (FlowType flow : bamInternal.getFlows(body).getFlows()) {
			flows.add(DomainUtil.toDomainType(flow));
		}
		System.out.println(ToStringBuilder.reflectionToString(flows, ToStringStyle.MULTI_LINE_STYLE));
	}

	public BAMInternal getBamInternal() {
		return bamInternal;
	}

	public void setBamInternal(BAMInternal bamInternal) {
		this.bamInternal = bamInternal;
	}
}
