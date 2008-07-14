package org.tmjee.miniwiki.client.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface BusinessException extends IsSerializable, Serializable {

    String getBusinessMessage();
            
}

