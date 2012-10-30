/*
 * © 2007 Netflexity, Ltd. All Rights Reserved.
 *
 * CONFIDENTIAL BUSINESS INFORMATION
 *
 * THIS PROGRAM IS PROPRIETARY INFORMATION OF NETFLEXITY, LTD. AND
 * IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF, NOR USED FOR ANY
 * OTHER PURPOSE, UNLESS THE WRITTEN PERMISSION FROM THE STATED ABOVE CORPORATION
 * IS GIVEN.
 */
package com.netflexity.bam.business.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NestedCheckedException;

/**
 * @author MAX
 *
 */
public class BamServiceException extends NestedCheckedException {

	/**/
    private static final long serialVersionUID = 1L;
    
    /*logger*/
    protected static Log logger = LogFactory.getLog(BamServiceException.class);

    /**
     * @param msg
     */
    protected BamServiceException(String msg) {
        super(msg);
        logger.error(msg);
    }

    /**
     * @param msg
     * @param t
     */
    public BamServiceException(String msg, Throwable t) {
        super(msg, t);
        logger.error(msg, t);
    }
}
