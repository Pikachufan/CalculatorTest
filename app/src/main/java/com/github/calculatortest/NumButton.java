package com.github.calculatortest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class NumButton extends android.support.v7.widget.AppCompatButton{

    public NumButton(Context context) {
        super(context);
    }

    public NumButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            this.setBackgroundColor(Color.GRAY);

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            this.setBackgroundColor(Color.WHITE);
        }
       return super.onTouchEvent(event);
    }
}
