package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.CheckBox;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 3, 2008
 * Time: 6:27:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdHoldableCheckBox extends CheckBox {

    private long id;

    public IdHoldableCheckBox(long id) {
        this.id = id;
    }


    public IdHoldableCheckBox(long id, String label) {
        super(label);
        this.id = id;
    }

    public IdHoldableCheckBox(long id, String label, boolean asHtml) {
        super(label, asHtml);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
