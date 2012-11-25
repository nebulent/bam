/**
 * 
 */
package com.netflexity.bam.business.repository.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.javac.comp.Enter;

/**
 * @author Alexei SCLIFOS
 *
 */
public class JpaAbstractRepository {

    /*logger*/
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    /*entity manager*/
    protected EntityManager entityManager;

    /**
     * @param entityManager
     */
    @PersistenceContext(unitName = "netflexity-bam-db")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @param entity
     * @return
     */
    protected Object save(Object entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * @param entity
     * @return
     */
    protected Object update(Object entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * @param entity
     * @return
     */
    protected Object merge(Object entity) {
        return entityManager.merge(entity);
    }


    /**
     * @param id
     * @param clazz
     */
    @SuppressWarnings("unchecked")
    protected void remove(Object id, Class clazz) {
        Object entity = entityManager.find(clazz, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
    
    /**
     * @return
     */
    protected String createUUID() {
    	return UUID.randomUUID().toString();
    }
    
    protected String appendCondition(String query, String condition, Map<String, Object> params, String paramName, Object paramValue) {
    	params.put(paramName, paramValue);
    	return appendCondition(query, condition);
    }
    
    protected String appendCondition(String query, String condition) {
    	if (StringUtils.isNotBlank(query)) {
    		query += " AND "; 
    	} else {
    		query = "";
    	}
    	return query + condition;
    }
    
    protected Query composeQuery(String select, String query, String postfix, Map<String, Object> params) {
    	if (StringUtils.isNotBlank(query)) {
    		select += " WHERE " + query;
    	}
    	if (StringUtils.isNotBlank(postfix)) {
    		select += " " + postfix;
    	}
    	Query jpaQuery = entityManager.createQuery(select);
    	setParameters(jpaQuery, params);
    	return jpaQuery;
    	
    }
    
    protected void setParameters(Query query, Map<String, Object> params) {
    	for (Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
    }

}














