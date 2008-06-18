package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.MessageEvent;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 5, 2008
 * Time: 3:00:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyDetailsPopupPanel extends DialogBox implements SourcesMessageEvents {


    public static interface Handler {
        void save(String propertyName, String propertyValue);
    }

    private SourcesEventsSupport eventsSupport;

    private Handler handler;

    private VerticalPanel mainPanel;
    private TextBox propertyName;
    private TextBox propertyValue;
    private Button save;
    private Button cancel;
    private HorizontalPanel buttonPanel;

    public PropertyDetailsPopupPanel(Handler handler) {

        setText("Property Details");
        setAnimationEnabled(true);

        eventsSupport = new SourcesEventsSupport();

        this.handler = handler;

        mainPanel = new VerticalPanel();

        propertyName = new TextBox();
        propertyValue = new TextBox();

        save = new Button("Save", new ClickListener() {
            public void onClick(Widget sender) {
                if (propertyName.getText() == null || (propertyName.getText().trim().length() <= 0) ||
                    propertyValue.getText() == null || (propertyValue.getText().trim().length() <= 0)) {
                    eventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Property Name and Value cannot be empty"));
                        }
                    });
                }
                PropertyDetailsPopupPanel.this.handler.save(propertyName.getText(), propertyValue.getText());
            }
        });
        cancel = new Button("Cancel", new ClickListener() {
            public void onClick(Widget sender) {
                PropertyDetailsPopupPanel.this.hide();
            }
        });

        buttonPanel = new HorizontalPanel();
        buttonPanel.add(save);
        buttonPanel.add(cancel);

        mainPanel.add(new Label("Property Name:"));
        mainPanel.add(propertyName);
        mainPanel.add(new Label("Property Value"));
        mainPanel.add(propertyValue);
        mainPanel.add(buttonPanel);

        setWidget(mainPanel);

        center();
    }


    public void addMessageEventListener(MessageEventListener listener) {
        eventsSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        eventsSupport.addListener(listener);
    }


    
}
