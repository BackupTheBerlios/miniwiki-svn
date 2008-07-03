package org.tmjee.miniwiki.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:18:27 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PropertyListener extends IsSerializable {
    void propertyChange(EventObject event);
}
