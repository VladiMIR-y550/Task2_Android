package com.mironenko.task2_android.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mironenko.task2_android.Model.MyList;
import com.mironenko.task2_android.R;
import java.util.Arrays;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    private final int ITEM_COUNT;

    private final int FRAGMENT_INDEX;

    private final int NUMBER_HOLDER_FOR_ONE_TASK;

    private final int TYPE_ITEM1 = 0;

    private final int TYPE_ITEM2 = 1;

    private final List<MyList> collections;

    private final List<String> titlesList;

    private final List<String> titlesMap;

    public RecyclerAdapter(List<MyList> myCollections, int itemCount, int fragment_index, Context context) {
        this.ITEM_COUNT = itemCount;
        this.FRAGMENT_INDEX = fragment_index;
        this.collections = myCollections;
        this.titlesList = Arrays.asList(context.getResources().getStringArray(R.array.array_tasksList));
        this.titlesMap = Arrays.asList(context.getResources().getStringArray(R.array.array_tasksMap));
        this.NUMBER_HOLDER_FOR_ONE_TASK = checkFragment(fragment_index);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_ITEM2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_title, parent, false);
                break;
            case TYPE_ITEM1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
                break;
            default:
                break;
        }
        assert view != null;
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {

        int type = getItemViewType(position);

        MyList myList;
        switch (type) {
            case TYPE_ITEM1:
                myList = collections.get(getMyCollectionByPosition(position));
                holder.bind(myList);
                break;
            case TYPE_ITEM2:
                if (FRAGMENT_INDEX == 0) {
                    checkPositionFromBind(holder, position, titlesList);
                } else if (FRAGMENT_INDEX == 1) {
                    checkPositionFromBind(holder, position, titlesMap);
                }
                break;

            default:
                break;
        }
    }

    private void checkPositionFromBind(RecyclerHolder holder, int position, List<String> titles) {
        if (position == 0) {
            holder.bind(titles.get(position));
        } else {
            holder.bind(titles.get((position) / NUMBER_HOLDER_FOR_ONE_TASK));
        }
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % NUMBER_HOLDER_FOR_ONE_TASK == 0 && position != ITEM_COUNT) {
            return TYPE_ITEM2;
        }
        return TYPE_ITEM1;
    }

    private int checkFragment(int FRAGMENT_RECEIVED) {
        if (FRAGMENT_RECEIVED == 0) {
            return 4;
        } else
            return 3;
    }

    private int getMyCollectionByPosition(int position) {
        if (FRAGMENT_INDEX == 0) {
            if (position == 1 || position == 2 || position == 3) {
                return position - 1;
            } else if (position == 5 || position == 6 || position == 7) {
                return position - 2;
            } else if (position == 9 || position == 10 || position == 11) {
                return position - 3;
            } else if (position == 13 || position == 14 || position == 15) {
                return position - 4;
            } else if (position == 17 || position == 18 || position == 19) {
                return position - 5;
            } else if (position == 21 || position == 22 || position == 23) {
                return position - 6;
            } else if (position == 25 || position == 26 || position == 27) {
                return position - 7;
            }
        } else {
            if (position == 1 || position == 2) {
                return position - 1;
            } else if (position == 4 || position == 5) {
                return position - 2;
            } else if (position == 7 || position == 8) {
                return position - 3;
            }
        }
        return position;
    }
}