package com.netflexity.bam.business.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import netflexity.schema.software.bam.messages._1.Authentication;
import netflexity.schema.software.bam.messages._1.GetUsers;

import com.netflexity.bam.business.domain.model.BpmOrganization;
import com.netflexity.bam.business.domain.model.BpmUser;
import com.netflexity.bam.business.repository.RepositoryException;
import com.netflexity.bam.business.repository.UserManagementRepository;

/**
 * @author vanovskii
 *
 */
public class JpaUserManagementRepository extends JpaAbstractRepository implements UserManagementRepository {

	/*constants*/
	private static final String ID = "ID";
	private static final String IDS = "IDS";
	private static final String ALIAS = "ALIAS";
	private static final String EMAIL = "EMAIL";
	private static final String PASSWORD = "PASSWORD";

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#getUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<BpmUser> getUsers(GetUsers body) throws RepositoryException {
		List<BpmUser> users = new ArrayList<BpmUser>();
		String select = "SELECT distinct(user) FROM com.netflexity.bam.business.domain.model.BpmUser user";
		String queryString = "";
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(body.getPassword())) {
			if (StringUtils.isNotBlank(body.getAlias())) {
				queryString = appendCondition(queryString, " user.alias = :ALIAS", params, ALIAS, body.getAlias());
				queryString = appendCondition(queryString, " user.password = :PASSWORD", params, PASSWORD, body.getPassword());
			}
			else if (StringUtils.isNotBlank(body.getEmail())) {
				queryString = appendCondition(queryString, " user.email = :EMAIL", params, EMAIL, body.getEmail());
				queryString = appendCondition(queryString, " user.password = :PASSWORD", params, PASSWORD, body.getPassword());
			}
		}
		else if (StringUtils.isNotBlank(body.getUserId())) {
			queryString = appendCondition(queryString, " user.id = :ID", params, ID, body.getUserId());
		}
		Query query = composeQuery(select, queryString, "", params);
    	return query.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#authenticate(netflexity.schema.software.bam.messages._1.GetUsers)
	 */
	public BpmUser authenticate(Authentication body) throws RepositoryException {
		String select = "SELECT distinct(user) FROM com.netflexity.bam.business.domain.model.BpmUser user";
		String queryString = "";
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(body.getPassword())) {
			if (StringUtils.isNotBlank(body.getAlias())) {
				queryString = appendCondition(queryString, " user.alias = :ALIAS", params, ALIAS, body.getAlias());
				queryString = appendCondition(queryString, " user.password = :PASSWORD", params, PASSWORD, body.getPassword());
			}
			else if (StringUtils.isNotBlank(body.getEmail())) {
				queryString = appendCondition(queryString, " user.email = :EMAIL", params, EMAIL, body.getEmail());
				queryString = appendCondition(queryString, " user.password = :PASSWORD", params, PASSWORD, body.getPassword());
			}
		}
		else {
			return null;
		}
		Query query = composeQuery(select, queryString, "", params);
    	return (BpmUser)query.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#removeUsers(long[])
	 */
	public void removeUsers(Long[] ids) throws RepositoryException {
		if(ids == null || ids.length == 0) {
    		return;
    	}
		Query query = entityManager.createQuery("DELETE FROM com.netflexity.bam.business.domain.model.BpmUser user WHERE user.id IN (:IDS)");
        query.setParameter(IDS, Arrays.asList(ids));
        query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#createUser(com.netflexity.bam.business.domain.model.BpmUser)
	 */
	public BpmUser createUser(BpmUser user) throws RepositoryException {
		if (user.getId() != null) {
			user.setId(null);
		}
		return (BpmUser)save(user);
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#updateUser(com.netflexity.bam.business.domain.model.BpmUser)
	 */
	public BpmUser updateUser(BpmUser user) throws RepositoryException {
		return (BpmUser)merge(user);
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#getOrganization(long)
	 */
	public BpmOrganization getOrganization(long organizationId) throws RepositoryException {
		Query query = entityManager.createQuery("SELECT organization FROM com.netflexity.bam.business.domain.model.BpmOrganization organization " +
												"WHERE organization.id = :ID");
		query.setParameter(ID, organizationId);
		return (BpmOrganization)query.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#getOrganizations()
	 */
	@SuppressWarnings("unchecked")
	public List<BpmOrganization> getOrganizations() throws RepositoryException {
		Query query = entityManager.createQuery("SELECT organization FROM com.netflexity.bam.business.domain.model.BpmOrganization organization");
    	return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#removeOrganizations(long[])
	 */
	public void removeOrganizations(Long[] ids) throws RepositoryException {
		if(ids == null || ids.length == 0) {
    		return;
    	}
		Query query = entityManager.createQuery("DELETE FROM com.netflexity.bam.business.domain.model.BpmOrganization organization WHERE organization.id IN (:IDS)");
        query.setParameter(IDS, Arrays.asList(ids));
        query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#createOrganization(com.netflexity.bam.business.domain.model.BpmOrganization)
	 */
	public BpmOrganization createOrganization(BpmOrganization organization) throws RepositoryException {
		if (organization.getId() != null) {
			organization.setId(null);
		}
		return (BpmOrganization)save(organization);
	}

	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.repository.UserManagementRepository#updateOrganization(com.netflexity.bam.business.domain.model.BpmOrganization)
	 */
	public BpmOrganization updateOrganization(BpmOrganization organization) throws RepositoryException {
		return (BpmOrganization)merge(organization);
	}
}
