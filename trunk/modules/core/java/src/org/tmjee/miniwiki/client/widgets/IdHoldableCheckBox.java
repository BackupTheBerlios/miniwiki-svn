package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.CheckBox;

/**
 * @author tmjee
 * @version $Date$ $Id$
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
