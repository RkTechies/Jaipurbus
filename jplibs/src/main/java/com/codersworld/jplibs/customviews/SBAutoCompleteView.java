package com.codersworld.jplibs.customviews;

import android.content.Context;
import android.util.AttributeSet;


public class SBAutoCompleteView extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {

    public SBAutoCompleteView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public SBAutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public SBAutoCompleteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    // this is how to disable AutoCompleteTextView filter
    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(filterText, keyCode);
    }

    /*
     * after a selection we have to capture the new value and append to the existing text
     */
    @Override
    protected void replaceText(final CharSequence text) {
        super.replaceText(text);
    }

}
