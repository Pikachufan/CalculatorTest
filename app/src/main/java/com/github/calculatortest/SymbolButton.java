package com.github.calculatortest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class SymbolButton extends android.support.v7.widget.AppCompatButton {

    public SymbolButton(Context context) {
        super(context);
    }

    public SymbolButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            this.setBackgroundColor(Color.WHITE);

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            this.setBackground(getResources().getDrawable(R.drawable.symbol));
        }
        return super.onTouchEvent(event);
    }
}
