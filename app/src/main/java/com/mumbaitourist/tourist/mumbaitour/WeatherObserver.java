package com.mumbaitourist.tourist.mumbaitour;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class WeatherObserver implements LifecycleObserver {
    private static final String LOG_TAG = WeatherObserver.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(LOG_TAG, "resumed observing lifecycle.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(LOG_TAG, "paused observing lifecycle.");
    }

}
