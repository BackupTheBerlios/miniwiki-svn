package org.tmjee.miniwiki.client.widgets;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ClickListener;

import java.util.ArrayList;
import java.util.List;

import org.tmjee.miniwiki.client.utils.Utils;

/**
 * Generic extention of FlexTable allowing :-
 *  - addRow/s
 *  - deleteRow/s
 *  - refresh
 * through rowObjects.
 *
 * @author tmjee
 * @version $Date$ $Id$
 */
public class FlexTableExt<T> extends FlexTable {

    public static interface TitleHandler<T> {
        int getTotalCols();
        boolean hasCheckableCol();
        int numOfControlWidget();
        Widget getTitleWidget(int col);
    }

    public static interface DataHandler<T> {
        Widget getDataWidget(T rowObject, int col);
        Widget getControlWidget(T rowObject, int col, int index);
    }

    private ArrayList<T> selectedRowObjects;

    private TitleHandler titleHandler;
    private DataHandler dataHandler;
    private ArrayList<Object> rowObjects;

    public FlexTableExt() {

        setStyleName("flexTableExt");

        selectedRowObjects = new ArrayList<T>();
        rowObjects = new ArrayList<Object>();
    }

    public void init(TitleHandler titleHandler, DataHandler dataHandler) {
        this.titleHandler = titleHandler;
        this.dataHandler = dataHandler;

        int colCount = 0;
        for (int col=0; col<titleHandler.getTotalCols(); col++) {
            setWidget(0, col, titleHandler.getTitleWidget(col));
            colCount++;
        }
        if (titleHandler.numOfControlWidget() > 0) {
            for (int a=0; a< titleHandler.numOfControlWidget(); a++) {
                setWidget(0, colCount, new Label(""));
                colCount++;
            }
        }
        if (titleHandler.hasCheckableCol()) {
            setWidget(0, colCount, new Label(""));
        }
        getRowFormatter().setStyleName(0, "flexTableExt-header");
    }


    public void refresh(T[] _rowObjects) {
        selectedRowObjects.clear();
        rowObjects.clear();

        for (int a=0; a< _rowObjects.length; a++) {
                int row = a+1;
                T rowObject = _rowObjects[a];
                int totalCols = 0;
                _doAdd(rowObject, row);
        }

        restyleRows();
    }

    public List<T> getSelectedRowObjects() {
        return selectedRowObjects;
    }

    public void deleteRow(T rowObject) {
        deleteRows(Utils.toArray(rowObject));
    }

    public void deleteRows(T[] _rowObjects) {
        boolean anyRowsAffected = false;
        for (Object rowObject: _rowObjects) {
            if (rowObjects.contains(rowObject)) {
                anyRowsAffected = true;
                int index = rowObjects.indexOf(rowObject);
                int rowNum = index + 1;
                if (getRowCount() > rowNum) {
                    removeRow(rowNum);
                    rowObjects.remove(rowObject);
                }
            }
        }
        if (anyRowsAffected) {
            restyleRows();
        }
    }

    public void addRow(T rowObject) {
        addRows(Utils.toArray(rowObject));
    }

    public void addRows(T[] _rowObjects) {
        boolean rowAffected = false;
        for (T rowObject: _rowObjects) {
            if (! rowObjects.contains(rowObject)) {
                rowAffected = true;
                int row = getRowCount();
                _doAdd(rowObject, row);
            }
        }
        if (rowAffected) {
            restyleRows();
        }
    }

    protected void restyleRows() {
        int totalRows = getRowCount();
        for (int row = 1; row <totalRows; row++) {
            getRowFormatter().setStyleName(row, ((row%2==0)?"flexTableExt-even":"flexTableExt-odd"));
        }
    }


    private void _doAdd(T rowObject, int row) {
        int totalCols = 0;
                rowObjects.add(rowObject);
                                for (int col=0; col<titleHandler.getTotalCols(); col++) {
                                    setWidget(row, col, dataHandler.getDataWidget(rowObject, col));
                                    getFlexCellFormatter().setColSpan(row, col, 1);
                                    totalCols++;
                                }
                                if (titleHandler.numOfControlWidget() > 0) {
                                    for (int z=0; z< titleHandler.numOfControlWidget(); z++) {
                                        Widget controlWidget = dataHandler.getControlWidget(rowObject, totalCols, z);
                                        setWidget(row, totalCols, controlWidget);
                                        totalCols++;
                                    }
                                }
                                if (titleHandler.hasCheckableCol()) {
                                    setWidget(row, totalCols, new ObjectHoldableCheckBox(rowObject, new ClickListener() {
                                        public void onClick(Widget widget) {
                                            ObjectHoldableCheckBox _cb = (ObjectHoldableCheckBox) widget;
                                            if (_cb.isChecked()) {
                                                if (!selectedRowObjects.contains(_cb.getObject())) {
                                                    selectedRowObjects.add((T)_cb.getObject());
                                                }
                                                int rowNum =  rowObjects.indexOf(_cb.getObject())+1;
                                                getRowFormatter().setStyleName(rowNum, "flexTableExt-selected");
                                            }
                                            else {
                                                if (selectedRowObjects.contains(_cb.getObject())) {
                                                    selectedRowObjects.remove(_cb.getObject());
                                                }
                                                int rowNum =  rowObjects.indexOf(_cb.getObject())+1;
                                                getRowFormatter().setStyleName(rowNum, ((rowNum%2==0)?"flexTableExt-even":"flexTableExt-odd"));
                                            }
                                        }
                                    }));
                                }
    }
}
