package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiIdentifiable extends IsSerializable, Serializable {
    long getId();
}
