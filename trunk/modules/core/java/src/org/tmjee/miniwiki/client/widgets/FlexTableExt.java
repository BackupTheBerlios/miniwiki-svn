package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 20, 2008
 * Time: 1:57:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlexTableExt extends FlexTable {

    public static interface TitleHandler {
        int getTotalCols();
        boolean addCheckableCol();
        Widget[] getControlColWidgets();
        Widget getTitleWidget(int col);
    }


    public static interface DataHandler {
        int getTotalRows();
        Object getRowObject(int row);
        Widget getDataWidget(Object rowObject, int col);
    }

    private List<Object> selectedRowObjects;

    private TitleHandler titleHandler;
    private DataHandler dataHandler;
    private ArrayList<Object> rowObjects;

    public FlexTableExt(TitleHandler titleHandler) {

        this.titleHandler = titleHandler;

        int colCount = 0;
        for (int col=0; col<titleHandler.getTotalCols(); col++) {
            setWidget(0, col, titleHandler.getTitleWidget(col));
            colCount++;
        }
        if (titleHandler.getControlColWidgets() != null) {
            for (Widget widget : titleHandler.getControlColWidgets()) {
                setWidget(0, colCount, new Label(""));
                colCount++;
            }
        }
        if (titleHandler.addCheckableCol()) {
            setWidget(0, colCount, new Label(""));
        }
        
        refresh(null);
    }



    public List<Object> getSelectedRowObjects() {
        return selectedRowObjects;
    }


    public void refresh(DataHandler dataHandler) {

        selectedRowObjects = new ArrayList<Object>();
        rowObjects = new ArrayList<Object>();

        if (dataHandler != null) {
            int totalRows = 1;
            if (dataHandler.getTotalRows() > 0) {
                for (int row=1; row<=dataHandler.getTotalRows(); row++) {

                    int totalCols = 0;

                    Object rowObject = dataHandler.getRowObject(row);
                    rowObjects.add(rowObject);
                    for (int col=0; col<titleHandler.getTotalCols(); col++) {
                        setWidget(row, col, dataHandler.getDataWidget(rowObject, col));
                        getFlexCellFormatter().setColSpan(row, col, 1);
                        totalCols++;
                    }
                    if (titleHandler.getControlColWidgets() != null) {
                        for (Widget controlWidget: handler.getControlColWidgets()) {
                            setWidget(row, totalCols, controlWidget);
                            totalCols++;
                        }
                    }
                    if (handler.addCheckableCol()) {
                        setWidget(row, totalCols, new ObjectHoldableCheckBox(rowObject, new ClickListener() {
                            public void onClick(Widget widget) {
                                ObjectHoldableCheckBox _cb = (ObjectHoldableCheckBox) widget;
                                if (_cb.isChecked()) {
                                    if (!selectedRowObjects.contains(_cb.getObject())) {
                                        selectedRowObjects.add(_cb.getObject());
                                    }
                                }
                                else {
                                    if (selectedRowObjects.contains(_cb.getObject())) {
                                        selectedRowObjects.remove(_cb.getObject());
                                    }
                                }
                            }
                        }));
                    }
                    totalRows++;
                }
            }
            while(totalRows < getRowCount())  {
                removeRow(getRowCount() - 1);
            }
        }
        else {
            setWidget(1, 0, new Label("--- No Entries ---"));
            int totalCols = titleHandler.getTotalCols();
            getFlexCellFormatter().setColSpan(1, 0, totalCols);

            while(getRowCount() > 2) {
                removeRow(getRowCount() - 1);
            }
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
