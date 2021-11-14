package com.mironenko.task2_android;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CellView extends FrameLayout {
    private static final String LOG_TAG = "CellView";
    private final boolean CELL_VISIBLE = true;
    private final boolean CELL_GONE = false;
    private ProgressBar progressBar;
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;

    public CellView(@NonNull Context context) {
        super(context);
        Log.d("CellView CONSTRUCTOR", "attr = null");
        initView(context, null);
    }

    public CellView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("CellView CONSTRUCTOR", "attr = !=null");
        initView(context, attrs);

    }

    public CellView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_item, null, false);
        progressBar = view.findViewById(R.id.progress_circular);
        textInputLayout = view.findViewById(R.id.til_progressItem);
        textInputEditText = view.findViewById(R.id.et_progress_item);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CellView);
            try {
                String cellHintFromAttr = typedArray.getString(R.styleable.CellView_cellHint);
                textInputLayout.setHint(cellHintFromAttr);
                boolean cellVisibleFromAttr = typedArray.getBoolean(R.styleable.CellView_cellVisibility, CELL_GONE);
                setProgressVisible(cellVisibleFromAttr);
                String cellTextFromAttr = typedArray.getString(R.styleable.CellView_cellText);
                textInputEditText.setText(cellTextFromAttr != null ? cellTextFromAttr : " ");
            } finally {
                typedArray.recycle();
            }
        }
        this.addView(view);
    }

    private void setProgressVisible(boolean visible) {
        if (visible == CELL_VISIBLE) {
            progressBar.setVisibility(VISIBLE);
        } else {
            progressBar.setVisibility(GONE);
        }
    }

    public void showResult(DataCell dataCell) {
        String result;
        setProgressVisible(CELL_GONE);
        result = String.valueOf(dataCell.getTimeComplete());
        textInputEditText.setText(result);
        this.invalidate();
        Log.d(LOG_TAG, "result = " + dataCell.getTimeComplete());
    }

    public void showProgress() {
        setProgressVisible(CELL_VISIBLE);
    }
}