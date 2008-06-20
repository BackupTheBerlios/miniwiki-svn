package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 20, 2008
 * Time: 1:57:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlexTableExt extends FlexTable {

    
    public static interface Handler {
        int getTotalRows();
        int getTotalCols();
        Object getRowObject(int row);
        Widget getTitleWidget(int col);
        Widget getDataWidget(Object rowObject, int col);
    }

    private Handler handler;
    private ArrayList<Object> rowObjects;

    public FlexTableExt(Handler handler) {

        this.handler = handler;

        for (int col=0; col<handler.getTotalCols(); col++) {
            setWidget(0, col, handler.getTitleWidget(col));
        }
        
        refresh();
    }


    public void refresh() {

        rowObjects = new ArrayList<Object>();

        this.handler = handler;

        int totalRows = 1;
        if (handler.getTotalRows() > 0) {
            for (int row=1; row<=handler.getTotalRows(); row++) {
                Object rowObject = handler.getRowObject(row);
                rowObjects.add(rowObject);
                for (int col=0; col<handler.getTotalCols(); col++) {
                    setWidget(row, col, handler.getDataWidget(rowObject, col));
                    getFlexCellFormatter().setColSpan(row, col, 1);
                }
                totalRows++;
            }
        }
        else {
            setWidget(1, 0, new Label("--- No Entries ---"));
            getFlexCellFormatter().setColSpan(1, 0, handler.getTotalCols());
            totalRows++;
        }

        while(totalRows < getRowCount())  {
            removeRow(getRowCount() - 1);
        }
    }

    public void deleteRow(Object rowObject) {
        if (rowObjects.contains(rowObject)) {
            int index = rowObjects.indexOf(rowObject);
            int rowNum = index + 1;
            if (getRowCount() > rowNum) {
                removeRow(rowNum);
                rowObjects.remove(rowObject);
            }
        }
    }
}
