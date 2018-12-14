package com.kishannareshpal.lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


/**
 * A subclass of AppCompatImageView to add workarounds for bugs in Android Framework and Support
 * Library.
 */
class ChromeImageView extends AppCompatImageView {
    public ChromeImageView(Context context) {
        super(context);
    }

    public ChromeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChromeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void drawableStateChanged() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            // Pre-N ImageView doesn't correctly invalidate drawables, see https://crbug.com/894770.
            Drawable drawable = getDrawable();
            if (drawable != null && drawable.isStateful()
                    && drawable.setState(getDrawableState())) {
                invalidateDrawable(drawable);
            }
        }
        super.drawableStateChanged();
    }
}