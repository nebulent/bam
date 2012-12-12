package com.netflexity.bam.business.repository;

import java.util.List;

import netflexity.schema.software.bam.messages._1.Authentication;
import netflexity.schema.software.bam.messages._1.GetUsers;

import com.netflexity.bam.business.domain.model.BpmOrganization;
import com.netflexity.bam.business.domain.model.BpmUser;

/**
 * @author vanovskii
 *
 */
public interface UserManagementRepository {
	
	/**
	 * @param body
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmUser> getUsers(GetUsers body) throws RepositoryException;
	
	/**
	 * @param body
	 * @return
	 * @throws RepositoryException
	 */
	BpmUser authenticate(Authentication body) throws RepositoryException;
	
	/**
	 * @param ids
	 * @throws RepositoryException
	 */
	void removeUsers(Long[] ids) throws RepositoryException;
	
	/**
	 * @param user
	 * @return
	 * @throws RepositoryException
	 */
	BpmUser createUser(BpmUser user) throws RepositoryException;
	
	/**
	 * @param user
	 * @return
	 * @throws RepositoryException
	 */
	BpmUser updateUser(BpmUser user) throws RepositoryException;
	
	/**
	 * @param organizationId
	 * @return
	 * @throws RepositoryException
	 */
	BpmOrganization getOrganization(long organizationId) throws RepositoryException;
	
	/**
	 * @return
	 * @throws RepositoryException
	 */
	List<BpmOrganization> getOrganizations() throws RepositoryException;
	
	/**
	 * @param ids
	 * @throws RepositoryException
	 */
	void removeOrganizations(Long[] ids) throws RepositoryException;
	
	/**
	 * @param organization
	 * @return
	 * @throws RepositoryException
	 */
	BpmOrganization createOrganization(BpmOrganization organization) throws RepositoryException;
	
	/**
	 * @param organization
	 * @return
	 * @throws RepositoryException
	 */
	BpmOrganization updateOrganization(BpmOrganization organization) throws RepositoryException;
}
