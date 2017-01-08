package com.ovwvwvo.common.widget.EditText;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;

import com.ovwvwvo.common.R;

public class PassWordEditText extends RightDrawableEditText {

    public PassWordEditText(Context context) {
        super(context);
    }

    public PassWordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PassWordEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    void onDrawableClicked() {
        int inputType = getInputType();
        Drawable drawable;
        if (inputType == (InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT)) {
            setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
            drawable = getContext().getResources().getDrawable(R.drawable.ic_eye_blue);
        } else {
            setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
            drawable = getContext().getResources().getDrawable(R.drawable.ic_eye);
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
            drawable, getCompoundDrawables()[3]);
        setSelection(getText().length());
    }
}