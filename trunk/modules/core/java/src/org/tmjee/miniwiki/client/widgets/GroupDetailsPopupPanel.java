package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import org.tmjee.miniwiki.client.domain.UiGroup;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 2:32:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupDetailsPopupPanel extends DialogBox {

    public GroupDetailsPopupPanel(UiGroup uiGroup) {
        setText("Group Details");
        setAnimationEnabled(true);


        center();
    }

}
