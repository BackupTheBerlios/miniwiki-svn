package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import org.tmjee.miniwiki.client.service.Service;
import org.tmjee.miniwiki.client.service.Myself;
import org.tmjee.miniwiki.client.server.UiUserManagementServiceAsync;
import org.tmjee.miniwiki.client.events.SourcesMessageEvents;
import org.tmjee.miniwiki.client.events.SourcesEventsSupport;
import org.tmjee.miniwiki.client.events.MessageEventListener;
import org.tmjee.miniwiki.client.events.MessageEvent;
import org.tmjee.miniwiki.client.domain.UiCredentials;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 8, 2008
 * Time: 9:05:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPopupPanel extends DialogBox implements SourcesMessageEvents {

    private SourcesEventsSupport sourcesEventsSupport;


    private MessageDisplayWidget messagesDisplayWidget;
    private VerticalPanel mainPanel;
    private TextBox username;
    private PasswordTextBox password;

    private HorizontalPanel buttonsPanel;
    private Button ok;
    private Button cancel;

    public LoginPopupPanel() {

        setText("Login");
        setAnimationEnabled(true);

        sourcesEventsSupport = new SourcesEventsSupport();

        username = new TextBox();

        password = new PasswordTextBox();
        
        messagesDisplayWidget = new MessageDisplayWidget();
        addMessageEventListener(messagesDisplayWidget);

        buttonsPanel = new HorizontalPanel();
        ok = new Button("Ok", new ClickListener() {
            public void onClick(Widget sender) {
                boolean badInput = false;
                if (username.getText() == null || (username.getText().trim().length() <= 0)) {
                    sourcesEventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Username cannot be empty"));
                        }
                    });
                    badInput = true;
                }
                if (password.getText() == null || (password.getText().trim().length() <= 0)) {
                    sourcesEventsSupport.iterateThroughListener(new SourcesEventsSupport.Handler() {
                        public void handle(Object listener) {
                            ((MessageEventListener)listener).onMessageEvent(new MessageEvent(MessageEvent.LEVEL_ERROR, "Password cannot be empty"));
                        }
                    });
                    badInput = true;
                }
                if (badInput) {
                    return;
                }



                LoadingMessageDisplayWidget.getInstance().display("Authenticating ...");
                UiUserManagementServiceAsync userManagementService = Service.getUserManagementService();
                userManagementService.authenticate(
                        username.getText(),
                        password.getText(),
                        new AsyncCallback() {
                            public void onFailure(Throwable caught) {
                                // TODO: logging
                                GWT.log(caught.toString(), caught);
                            }
                            public void onSuccess(Object result) {
                                UiCredentials credentials = (UiCredentials) result;
                                Myself.getInstance().setCredentials(credentials);
                                LoadingMessageDisplayWidget.getInstance().done();
                            }
                        }
                );
            }
        });
        cancel = new Button("Cancel", new ClickListener() {
            public void onClick(Widget sender) {
                hide();
            }
        });
        buttonsPanel.add(ok);
        buttonsPanel.add(cancel);


        mainPanel.add(messagesDisplayWidget);
        mainPanel.add(new Label("Username:"));
        mainPanel.add(username);
        mainPanel.add(new Label("Password;"));
        mainPanel.add(password);
        mainPanel.add(buttonsPanel);

        setWidget(mainPanel);

        center();
    }

    public void addMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.addListener(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        sourcesEventsSupport.removeListener(listener);
    }
}
