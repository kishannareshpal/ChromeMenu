package com.kishannareshpal.lib;

import android.view.animation.Interpolator;

/**
 * An {@link Interpolator} that pulses a value based on the passed in {@link Interpolator}.  The
 * pulse will fade in and out after a delay.
 */
class PulseInterpolator implements Interpolator {
    private final Interpolator mInterpolator;

    /**
     * Creates a new {@link PulseInterpolator} instance.
     *
     * @param interpolator The {@link Interpolator} responsible for handling the fade out and in.
     */
    PulseInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    @Override
    public float getInterpolation(float input) {
        if (input < 0.4f) return 0.f;
        if (input < 0.8f) return mInterpolator.getInterpolation((input - 0.4f) / 0.4f);
        return mInterpolator.getInterpolation(1.f - (input - 0.8f) / 0.2f);
    }
}