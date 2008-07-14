package org.tmjee.miniwiki.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface PropertyListener extends IsSerializable, Serializable {
    void propertyChange(EventObject event);
}
