/**
 * 
 */
package com.netflexity.bam.business.repository.impl;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}














