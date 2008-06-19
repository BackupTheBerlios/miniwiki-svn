package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 2:32:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupDetailsPopupPanel extends DialogBox implements SourcesMessageEvents {

    private SourcesEventsSupport sourcesEventsSupport;

    private VerticalPanel mainPanel;

    private TextBox groupName;
    private 

    public GroupDetailsPopupPanel(UiGroup uiGroup) {

        this.sourcesEventsSupport = new SourcesEventsSupport();

        setText("Group Details");
        setAnimationEnabled(true);


        center();
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.removeListener(listener);
    }
}
