/**
 * 
 */
package com.netflexity.bam.business.service;

import netflexity.schema.software.bam.messages._1.UserAwareMessage;

/**
 * @author Alexei SCLIFOS
 *
 */
public interface SecurityManagementService {

	/**
	 * @param user
	 * @return
	 */
	String getPartyId(UserAwareMessage user);
}
