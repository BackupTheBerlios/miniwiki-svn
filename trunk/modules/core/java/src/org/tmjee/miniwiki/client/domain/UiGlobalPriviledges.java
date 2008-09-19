package org.tmjee.miniwiki.client.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UiGlobalPriviledges implements IsSerializable {

    private List<UiGlobalPriviledge> priviledges = new ArrayList<UiGlobalPriviledge>();


    public UiGlobalPriviledges(List<UiGlobalPriviledge> priviledges) {
        this.priviledges = priviledges;
    }

    public List<UiGlobalPriviledge> getPriviledges() {
        return priviledges;
    }

}
