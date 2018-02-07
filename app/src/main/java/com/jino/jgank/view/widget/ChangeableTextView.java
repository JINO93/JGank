package com.jino.jgank.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jino.jgank.R;

import timber.log.Timber;

/**
 * Created by JINO on 2018/2/1.
 */

public class ChangeableTextView extends LinearLayout implements View.OnClickListener {

    private static final int MIN_HEIGHT = 50;
    private static final int MIN_WIDTH = 120;

    private int rigthIconId;
    private int leftIconId;
    private CharSequence[] mValues;
    private int textColor;
    private ImageView btnLeft;
    private ImageView btnRight;
    private TextView tvContent;

    private int currentIndex;
    private OnTextContentChangeListener mListener;

    public ChangeableTextView(Context context) {
        this(context, null);
    }

    public ChangeableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChangeableTextView);
        leftIconId = typedArray.getResourceId(R.styleable.ChangeableTextView_left_icon, R.drawable.ic_arrow_up_c);
        rigthIconId = typedArray.getResourceId(R.styleable.ChangeableTextView_rigth_icon, R.drawable.ic_arrow_up_c);
        mValues = typedArray.getTextArray(R.styleable.ChangeableTextView_text_value);
        textColor = typedArray.getColor(R.styleable.ChangeableTextView_text_color, getResources().getColor(android.R.color.black));
        typedArray.recycle();
        init(context);
        Timber.tag("ChangeableTextView");
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_changeabel_textview, this, true);
        btnLeft = view.findViewById(R.id.img_left);
        btnRight = view.findViewById(R.id.img_right);
        tvContent = view.findViewById(R.id.tv_content);

        btnLeft.setImageResource(leftIconId);
        btnLeft.setOnClickListener(this);
        btnRight.setImageResource(rigthIconId);
        btnRight.setOnClickListener(this);
        tvContent.setText(mValues[currentIndex]);
//        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        addView(view, layoutParams);
        setGravity(Gravity.CENTER_VERTICAL);
//        setBackgroundResource(R.color.colorAccent);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                btnLeft.measure(heightMeasureSpec, heightMeasureSpec);
                btnRight.measure(heightMeasureSpec, heightMeasureSpec);
                break;
        }
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                int contentWidth = measureWidth - 2 * btnLeft.getMeasuredWidth();
                tvContent.measure(MeasureSpec.makeMeasureSpec(contentWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
                break;
        }
        Timber.d("width:%d--mode:%d,height:%d--mode:%d",
                measureWidth, widthMode, measureHeight, heightMode);
    }



    public void setContentValue(String[] value) {
        mValues = value;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_left:
                --currentIndex;
                if (currentIndex < 0) currentIndex = mValues.length - 1;
                changeContent(currentIndex);
                break;
            case R.id.img_right:
                ++currentIndex;
                if (currentIndex >= mValues.length) currentIndex = 0;
                changeContent(currentIndex);
                break;
        }
    }

    void changeContent(int index) {
        if (index < 0 || index >= mValues.length) return;
        CharSequence value = mValues[index];
        if (mListener != null) {
            mListener.onContentChange((String) value);
        }
        tvContent.setText(value);
    }

    public void setTextContentChangeListener(OnTextContentChangeListener listener) {
        mListener = listener;
    }

    interface OnTextContentChangeListener {
        void onContentChange(String content);
    }

    private float dp2px(int dp) {
        return getResources().getDisplayMetrics().density * dp + 0.5f;
    }
}
