package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.DialogBox;
import org.tmjee.miniwiki.client.utils.PopupRegistry;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AutoRegisteredDialogBox extends DialogBox {

    public AutoRegisteredDialogBox() {
        PopupRegistry.getInstance().register(this);
    }

    public void show() {
        PopupRegistry.getInstance().register(this);
        super.show();
    }

    public void hide() {
        PopupRegistry.getInstance().unregister(this);
        super.hide();
    }
}
