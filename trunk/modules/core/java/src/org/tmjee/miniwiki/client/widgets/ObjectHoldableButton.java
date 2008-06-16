package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 6:16:40 PM
 * To change this template use File | Settings | File Templates.
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
