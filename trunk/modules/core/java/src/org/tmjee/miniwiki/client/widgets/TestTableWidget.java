package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 21, 2008
 * Time: 2:58:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestTableWidget extends SimpleTableWidget {

    public TestTableWidget() {
        super(new FlexTableExt.TitleHandler() {

            public int getTotalCols() {
                return 3;
            }

            public boolean hasCheckableCol() {
                return true;
            }

            public boolean hasControlWidget() {
                return true;
            }

            public int numOfControlWidget() {
                return 2;
            }

            public Widget getTitleWidget(int col) {
                switch(col) {
                    case 0:
                        return new Label("Col1");
                    case 1:
                        return new Label("Col2");
                    default:
                        return null;

                }
            }
        });
    }

    protected void refresh(PagingInfo pagingInfo, Status status) {
        update(new ResponsePagingInfo(pagingInfo, 2),
                new FlexTableExt.DataHandler() {
                    public int getTotalRows() {
                        return 10;
                    }

                    public Object getRowObject(int row) {
                        return new String("Row_"+row);
                    }

                    public Widget getDataWidget(Object rowObject, int col) {
                        return new Label(rowObject+"_"+col);
                    }

                    public Widget getControlWidget(Object rowObject, int col, int index) {
                        return new Button("Click Me");
                    }
                });
    }

}
