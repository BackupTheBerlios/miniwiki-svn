package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ObjectHoldableButton extends Button {

    private Object object;

    public ObjectHoldableButton(Object object, String html) {
        super(html);
        this.object = object;
    }

    public ObjectHoldableButton(Object object, String html, ClickListener listener) {
        super(html, listener);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
