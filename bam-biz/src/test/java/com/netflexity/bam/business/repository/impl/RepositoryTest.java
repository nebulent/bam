package com.netflexity.bam.business.repository.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.netflexity.bam.business.domain.model.BpmAttribute;
import com.netflexity.bam.business.domain.model.BpmQualifier;
import com.netflexity.bam.business.domain.model.BpmStage;

/**
 * @author
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/test-bam-biz-repositories.xml","classpath:META-INF/spring/test-bam.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class RepositoryTest {
    
	@Autowired
    private JpaMetadataRepository metadataRepository;

    /**
     * 
     */
    @Test
    public void test1() {
	    System.out.println("Start...");
	    BpmStage stage = new BpmStage();
	    stage.setName("Test stage100");
	    stage.setPartyId("party");
	
	    BpmQualifier qualifier = new BpmQualifier("party", "test qualifier");
	    qualifier = metadataRepository.createQualifier(qualifier);
	    
	    System.out.println("QUALIFIER ID = " + qualifier.getId());
	    
	    stage.setBpmQualifier(qualifier);
	    
	    stage = metadataRepository.createStage(stage);
	
	    BpmAttribute attr1 = new BpmAttribute();
	    attr1.setName("attr 1_100");
	    attr1.setPartyId("party");
	
	    BpmAttribute attr2 = new BpmAttribute();
	    attr2.setName("attr 1_200");
	    attr2.setPartyId("party");
	
	    Set<BpmAttribute> attributes = new HashSet<BpmAttribute>();
	    attributes.add(attr1);
	    attributes.add(attr2);
	    stage.setBpmAttributes(attributes);
	
	    metadataRepository.updateStage(stage);
	
	    List<BpmStage> list = metadataRepository.getStages(null);
	    for (BpmStage item : list) {
	        System.out.println("[" + item.getPartyId() + "]" + item.getName());
	    }
	    System.out.println("End !!!");
    }
    
    /**
     * 
     */
    @Test
    public void test2() {
    	BpmStage stage = metadataRepository.entityManager.find(BpmStage.class, new Long(4));
    	System.out.println("Stage name = " + stage.getName());
    	/*
    	if(stage.getBpmAttributes() != null) {
    		for (BpmAttribute attr : stage.getBpmAttributes()) {
    			System.out.println("Attr = " + attr.getName());
    			//stage.getBpmAttributes().remove(attr);
    		}
    	}*/
    	
	    BpmAttribute attr1 = new BpmAttribute();
	    attr1.setName("attr 800");
	    attr1.setPartyId("party");
    	
    	stage.getBpmAttributes().removeAll(stage.getBpmAttributes());
    	stage.getBpmAttributes().add(attr1);
    	
    	metadataRepository.entityManager.persist(stage);
    }
    
    /**
     * 
     */
    @Test
    public void test3() {
    	BpmStage stageTmp = metadataRepository.entityManager.find(BpmStage.class, new Long(6));
    	BpmStage stage = new BpmStage();
    	stage.setId(new Long(6));
    	stage.setName(stageTmp.getName());
    	stage.setPartyId(stageTmp.getPartyId());
    	stage.setBpmQualifier(stageTmp.getBpmQualifier());
    	
	    BpmAttribute attr1 = new BpmAttribute();
	    attr1.setName("attr 1_800");
	    attr1.setPartyId("party");
	    stage.setBpmAttributes(new HashSet<BpmAttribute>());
	    stage.getBpmAttributes().add(attr1);
    	metadataRepository.entityManager.merge(stage);
    }
}




























