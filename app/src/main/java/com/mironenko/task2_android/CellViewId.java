package com.mironenko.task2_android;

import com.mironenko.task2_android.R;

public enum CellViewId {
    CELL_ADDING_BEGIN_ARRAY_LIST(R.id.cell_arrayList_addingBegin),
    CELL_ADDING_BEGIN_LINKED_LIST(R.id.cell_linkedList_addingBegin),
    CELL_ADDING_BEGIN_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_addingBegin),
    CELL_ADDING_MIDDLE_ARRAY_LIST(R.id.cell_arrayList_addingMiddle),
    CELL_ADDING_MIDDLE_LINKED_LIST(R.id.cell_linkedList_addingMiddle),
    CELL_ADDING_MIDDLE_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_addingMiddle),
    CELL_ADDING_END_ARRAY_LIST(R.id.cell_arrayList_addingEnd),
    CELL_ADDING_END_LINKED_LIST(R.id.cell_linkedList_addingEnd),
    CELL_ADDING_END_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_addingEnd),

    CELL_SEARCH_BY_VALUE_ARRAY_LIST(R.id.cell_arrayList_searchValue),
    CELL_SEARCH_BY_VALUE_LINKED_LIST(R.id.cell_linkedList_searchValue),
    CELL_SEARCH_BY_VALUE_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_searchValue),

    CELL_REMOVING_BEGIN_ARRAY_LIST(R.id.cell_arrayList_removeBegin),
    CELL_REMOVING_BEGIN_LINKED_LIST(R.id.cell_linkedList_removeBegin),
    CELL_REMOVING_BEGIN_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_removeBegin),
    CELL_REMOVING_MIDDLE_ARRAY_LIST(R.id.cell_arrayList_removeMiddle),
    CELL_REMOVING_MIDDLE_LINKED_LIST(R.id.cell_linkedList_removeMiddle),
    CELL_REMOVING_MIDDLE_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_removeMiddle),
    CELL_REMOVING_END_ARRAY_LIST(R.id.cell_arrayList_removeEnd),
    CELL_REMOVING_END_LINKED_LIST(R.id.cell_linkedList_removeEnd),
    CELL_REMOVING_END_COPY_ON_WRITE_ARRAY_LIST(R.id.cell_copyOnWrite_removeEnd);

    private final int cellViewId;

    CellViewId(int cellViewId) {
        this.cellViewId = cellViewId;
    }

    public int getCellViewId() {
        return cellViewId;
    }
}
