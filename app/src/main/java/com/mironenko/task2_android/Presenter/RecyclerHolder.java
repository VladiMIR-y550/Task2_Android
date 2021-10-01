package com.mironenko.task2_android.Presenter;

import android.os.Handler;
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
    Handler h = new Handler();
    private String result;

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
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                result = holdCollection.startCalculate();
                h.post(showResult);
            }
        });
        if (result == null) {
            h.post(showProgress);
            t.start();
        } else {
            itemResult.setText(result);
        }
    }

    public void bind(String textTitle) {
        tv_title.setText(textTitle);
    }

    Runnable showResult = new Runnable() {
        @Override
        public void run() {
            progressBar.setVisibility(View.GONE);
            itemResult.setText(result);
        }
    };

    Runnable showProgress = new Runnable() {
        @Override
        public void run() {
            progressBar.setVisibility(View.VISIBLE);
        }
    };


//    private void showProgress() {
//        progressBar.setVisibility(View.VISIBLE);
//    }
}
