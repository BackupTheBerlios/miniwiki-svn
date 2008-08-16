package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.MessageEvent;

/**
 * @author tmjee
 * @version $Date$ $Id$
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

    private boolean editMode;

    public PropertyDetailsPopupPanel(boolean editMode, Handler handler) {
        this(editMode, "", "", handler);
    }


    public PropertyDetailsPopupPanel(boolean editMode, String propName, String propValue, Handler handler) {

        setText("Property Details");
        setAnimationEnabled(true);

        eventsSupport = new SourcesEventsSupport();

        this.handler = handler;

        mainPanel = new VerticalPanel();

        propertyName = new TextBox();
        propertyName.setText(propName);
        if (editMode)
            propertyName.setEnabled(false);
        propertyValue = new TextBox();
        propertyValue.setText(propValue);

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
                else {
                    PropertyDetailsPopupPanel.this.handler.save(propertyName.getText(), propertyValue.getText());
                    PropertyDetailsPopupPanel.this.hide();
                }
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
