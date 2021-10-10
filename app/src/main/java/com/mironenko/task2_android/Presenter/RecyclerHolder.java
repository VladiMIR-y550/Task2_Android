package com.mironenko.task2_android.Presenter;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mironenko.task2_android.Model.MyList;
import com.mironenko.task2_android.R;


public class RecyclerHolder extends RecyclerView.ViewHolder {
    private final TextInputLayout itemHint;
    private final TextInputEditText itemResult;
    private final ProgressBar progressBar;
    private final TextView tv_title;
    private MyList holdCollection;

    public RecyclerHolder(@NonNull View itemView) {
        super(itemView);

        itemHint = itemView.findViewById(R.id.til_progressItem);
        tv_title = itemView.findViewById(R.id.tv_title);
        itemResult = itemView.findViewById(R.id.et_progress_item);
        progressBar = itemView.findViewById(R.id.progress_circular);
    }


    public void bind(MyList myList) {
        holdCollection = myList;
        itemHint.setHint(holdCollection.getNameItem());
        if (holdCollection.getTimeComplete() != null) {
            itemResult.setText(myList.getTimeComplete());
            progressBar.setVisibility(View.GONE);

        }
    }

    public void bind(int textTitle) {
        tv_title.setText(textTitle);
    }

    /**
     * этот инстанс callback интерфейса нужно передать в каждый поток как параметр
     *
     */

//    IJob callback = new IJob() {
//        @Override
//        public void onComplete(long result) {
//            progressBar.setVisibility(View.GONE);
//
//            itemResult.setText(String.valueOf(result));
//        }
//    };
}
