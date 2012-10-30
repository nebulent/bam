/**
 * 
 */
package com.netflexity.bam.business.service;

import netflexity.schema.software.bam.messages._1.UserAwareMessage;

/**
 * @author Alexei SCLIFOS
 *
 */
public class FakeSecurityManagementService implements SecurityManagementService {

	/*constants*/
	private static final String PARTY_ID = "nebulent";
	
	/* (non-Javadoc)
	 * @see com.netflexity.bam.business.service.SecurityManagementService#getPartyId(netflexity.schema.software.bam.messages._1.UserAwareMessage)
	 */
	public String getPartyId(UserAwareMessage user) {
		return PARTY_ID;
	}
}
