package com.mironenko.task2_android;

import com.mironenko.task2_android.R;

public enum NamesCollections {
    ARRAY_LIST(R.string.array_list),
    LINKED_LIST(R.string.linked_list),
    COPY_ON_WRITE_ARRAY_LIST(R.string.copy_on_write_array_list);

    private final int titleName;

    NamesCollections(int titleName) {
        this.titleName = titleName;
    }

    public int getTitleName() {
        return titleName;
    }
}
