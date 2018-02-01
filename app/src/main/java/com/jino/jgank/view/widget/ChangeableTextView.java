package com.jino.jgank.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jino.jgank.R;

/**
 * Created by JINO on 2018/2/1.
 */

public class ChangeableTextView extends FrameLayout implements View.OnClickListener {

    private int rigthIconId;
    private int leftIconId;
    private CharSequence[] mValues;
    private int textColor;
    private ImageView ivLeft;
    private ImageView ivRight;
    private TextView tvContent;

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
        mValues =  typedArray.getTextArray(R.styleable.ChangeableTextView_text_value);
        textColor = typedArray.getColor(R.styleable.ChangeableTextView_text_color, getResources().getColor(android.R.color.black));
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_changeabel_textview, null);
        ivLeft = view.findViewById(R.id.img_left);
        ivRight = view.findViewById(R.id.img_right);
        tvContent = view.findViewById(R.id.tv_content);

        ivLeft.setImageResource(leftIconId);
        ivLeft.setOnClickListener(this);
        ivRight.setImageResource(rigthIconId);
        ivRight.setOnClickListener(this);
        tvContent.setText(mValues[0]);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(view, layoutParams);
    }

    public void setContentValue(String[] value) {
        mValues = value;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_left:
                break;
            case R.id.img_right:
                break;
        }
    }

    public void setTextContentChangeListener(OnTextContentChangeListener listener) {
        mListener = listener;
    }

    interface OnTextContentChangeListener {
        void onContentChange(String content);
    }
}
