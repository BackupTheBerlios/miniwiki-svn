package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 21, 2008
 * Time: 2:58:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestGenericTableWidget extends GenericTableWidget {

    private FlexTableExt.Handler handler = new InternalFlexTableExtHandler();

    public TestGenericTableWidget() {
        super(handler);
    }

    protected void addWidgetsToSearchPanel(HorizontalPanel searchPanel) {

    }

    protected void refresh() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    private class InternalFlexTableExtHandler implements FlexTableExt.Handler {

        public int getTotalRows() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public int getTotalCols() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean addCheckableCol() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Widget[] getControlColWidgets() {
            return new Widget[0];  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Object getRowObject(int row) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Widget getTitleWidget(int col) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Widget getDataWidget(Object rowObject, int col) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
