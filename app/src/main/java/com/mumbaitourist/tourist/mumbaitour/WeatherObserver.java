package com.mumbaitourist.tourist.mumbaitour;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
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
