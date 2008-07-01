package org.tmjee.miniwiki.client.widgets;

import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiUser;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jul 1, 2008
 * Time: 6:40:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssignUserPopupPanel extends DialogBox  {

    public static interface Handler {
        void assign(UiUser user, UiGroup group);
        void unassign(UiUser user, UiGroup group);
    }


    private UiGroup group;
    private Handler handler;

    public AssignUserPopupPanel(UiGroup group, Handler handler) {
        this.group = group;
        this.handler = handler;
    }

    
    

}
