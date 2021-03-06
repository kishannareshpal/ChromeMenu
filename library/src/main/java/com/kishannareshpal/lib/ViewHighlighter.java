package com.kishannareshpal.lib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;

class ViewHighlighter {
    /**
     * Create a highlight layer over the view.
     *
     * @param view     The view to be highlighted.
     * @param circular Whether the highlight should be a circle or rectangle.
     */
    static void turnOnHighlight(View view, boolean circular) {
        if (view == null) return;
        final Context context = view.getContext();

        boolean highlighted = view.getTag(R.id.cm_highlight_state) != null && (boolean) view.getTag(R.id.cm_highlight_state);
        if (highlighted) return;

        PulseDrawable pulseDrawable = circular
                ? PulseDrawable.createCircle(context)
                : PulseDrawable.createHighlight(context);

        Resources resources = context.getResources();
        Drawable background = view.getBackground();
        if (background != null) {
            Drawable.ConstantState state = background.getConstantState();
            if (state != null) {
                background = state.newDrawable(resources);
            }
        }

        LayerDrawable drawable = ApiCompatibilityUtils.createLayerDrawable(background == null
                ? new Drawable[]{pulseDrawable}
                : new Drawable[]{background, pulseDrawable});
        view.setBackground(drawable);
        view.setTag(R.id.cm_highlight_state, true);

        pulseDrawable.start();
    }

    /**
     * Turns off the highlight from the view. The original background of the view is restored.
     *
     * @param view The associated view.
     */
    static void turnOffHighlight(View view) {
        if (view == null) return;

        boolean highlighted = view.getTag(R.id.cm_highlight_state) != null && (boolean) view.getTag(R.id.cm_highlight_state);
        if (!highlighted) return;
        view.setTag(R.id.cm_highlight_state, false);

        Resources resources = view.getContext().getResources();
        Drawable existingBackground = view.getBackground();
        if (existingBackground instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) existingBackground;
            if (layerDrawable.getNumberOfLayers() >= 2) {
                Drawable.ConstantState state = layerDrawable.getDrawable(0).getConstantState();
                if (state == null) {
                    view.setBackground(null);
                    return;
                }
                view.setBackground(state.newDrawable(resources));
            } else {
                view.setBackground(null);
            }
        }
    }
}
