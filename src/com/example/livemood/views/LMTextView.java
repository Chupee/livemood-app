package com.example.livemood.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LMTextView extends TextView {
	
    public LMTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LMTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LMTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/opensansregular.ttf");
        setTypeface(tf ,1);

    }

}