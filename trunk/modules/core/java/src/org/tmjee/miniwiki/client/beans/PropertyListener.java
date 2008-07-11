package org.tmjee.miniwiki.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface PropertyListener extends IsSerializable {
    void propertyChange(EventObject event);
}
