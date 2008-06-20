package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 5:56:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectHoldableCheckBox extends CheckBox {

    private Object object;

    public ObjectHoldableCheckBox(Object object) {
        this.object = object;
    }

    public ObjectHoldableCheckBox(Object object, String label) {
        super(label);
        this.object = object;
    }

    public ObjectHoldableCheckBox(Object object, String label, boolean asHtml) {
        super(label, asHtml);
        this.object = object;
    }

    public ObjectHoldableCheckBox(Object object, ClickListener clickListener) {
        this.object = object;
        addClickListener(clickListener);
    }

    public ObjectHoldableCheckBox(Object object, String label, ClickListener clickListener) {
        super(label);
        this.object = object;
        addClickListener(clickListener);
    }

    public ObjectHoldableCheckBox(Object object, String label, boolean asHtml, ClickListener clickListener) {
        super(label, asHtml);
        this.object = object;
        addClickListener(clickListener);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
