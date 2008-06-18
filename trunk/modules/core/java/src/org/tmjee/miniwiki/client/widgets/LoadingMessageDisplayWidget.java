package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 22, 2008
 * Time: 2:34:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadingMessageDisplayWidget extends SimplePanel {

    private List messages;
    private Label label;

    private LoadingMessageDisplayWidget() {
        setStyleName("LoadingMessageDisplayWidget");
        label = new Label("");
        messages = new ArrayList();
        setWidget(label);
        show();
    }

    private static LoadingMessageDisplayWidget INSTANCE;

    public static LoadingMessageDisplayWidget getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoadingMessageDisplayWidget();
        }
        return INSTANCE;
    }


    public void display(String message) {
        messages.add(message);
        show();
    }

    public void display() {
        messages.add("Loading ...");
        show();
    }

    public void done() {
        if (messages.size() > 0) {
            messages.remove(0);
        }
        show();
    }

    protected void show() {
        if (messages.size() > 0) {
            String message = (String) messages.get(0);
            label.setText(message);

            DOM.setStyleAttribute(getElement(), "position", "absolute");
            DOM.setElementPropertyInt(getElement(), "top", Window.getScrollTop());
            DOM.setElementPropertyInt(getElement(), "left", Window.getScrollLeft());
            setVisible(true);
        }
        else {
            DOM.setStyleAttribute(getElement(), "position", "absolute");
            DOM.setIntStyleAttribute(getElement(), "top", Window.getScrollTop());
            DOM.setIntStyleAttribute(getElement(), "left", Window.getScrollLeft());
            setVisible(false);
        }
    }
}
