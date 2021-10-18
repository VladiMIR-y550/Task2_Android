package com.mironenko.task2_android;

import com.mironenko.task2_android.R;

public enum TasksList {
    ADDING_IN_THE_BEGINNING(R.string.adding_in_the_beginning),
    ADDING_IN_THE_MIDDLE(R.string.adding_in_the_middle),
    ADDING_IN_THE_END(R.string.adding_in_the_end),
    SEARCH_BY_VALUE(R.string.search_by_value),
    REMOVING_IN_THE_BEGINNING(R.string.removing_in_the_beginning),
    REMOVING_IN_THE_MIDDLE(R.string.adding_in_the_middle),
    REMOVING_IN_THE_END(R.string.removing_in_the_end);

    private final int titleTask;

    TasksList(int titleTask) {
        this.titleTask = titleTask;
    }

    public int getTitleName() {
        return titleTask;
    }
}
