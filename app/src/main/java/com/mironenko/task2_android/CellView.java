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
    private final int CELL_VISIBLE = 0;
    private final int CELL_INVISIBLE = 1;
    private final int CELL_GONE = 2;
    private ProgressBar progressBar;
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;

    public CellView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public CellView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CellView);
            try {
                String cellHintFromAttr = typedArray.getString(R.styleable.CellView_cellHint);
                textInputLayout.setHint(cellHintFromAttr);
                int cellVisibleFromAttr = typedArray.getInt(R.styleable.CellView_cellVisibility, CELL_VISIBLE);
                setProgressVisible(cellVisibleFromAttr);
                String cellTextFromAttr = typedArray.getString(R.styleable.CellView_cellText);
                setResult(cellTextFromAttr);
            } finally {
                typedArray.recycle();
            }
        }
    }

    public CellView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_item, null, false);
        progressBar = view.findViewById(R.id.progress_circular);
        textInputLayout = view.findViewById(R.id.til_progressItem);
        textInputEditText = view.findViewById(R.id.et_progress_item);
        this.addView(view);
    }

    private void setProgressVisible(int visible) {
        switch (visible) {
            case CELL_VISIBLE:
                progressBar.setVisibility(VISIBLE);
                break;
            case CELL_INVISIBLE:
                progressBar.setVisibility(INVISIBLE);
                break;
            case CELL_GONE:
                progressBar.setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    private void setResult(String result) {
        if (result == null) {
            textInputEditText.setText(" ");
        } else {
            textInputEditText.setText(result);
        }
        this.invalidate();
    }

    public void showResult(DataCell dataCell) {
        setProgressVisible(CELL_GONE);
        setResult(String.valueOf(dataCell.getTimeComplete()));
        Log.d(LOG_TAG, "result = " + dataCell.getTimeComplete());
    }
}