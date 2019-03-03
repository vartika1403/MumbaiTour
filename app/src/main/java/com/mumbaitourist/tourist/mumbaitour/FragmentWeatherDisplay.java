package com.mumbaitourist.tourist.mumbaitour;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableStringBuilder;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentWeatherDisplay extends Fragment implements LifecycleOwner {
    private static final String LOG_TAG = FragmentWeatherDisplay.class.getSimpleName();
    private final static String API_KEY = "9351aee12441dbae1f55fb5ac1de496b";
    private SpannableStringBuilder stringBuilder = null;
    private LifecycleRegistry lifecycleRegistry;

    @BindView(R.id.city_name_text)
    TextView cityName;
    @BindView(R.id.temp_text)
    TextView cityTemp;
    @BindView(R.id.humidity_text)
    TextView cityHumidity;
    @BindView(R.id.pressure_text)
    TextView cityPressure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringBuilder = new SpannableStringBuilder();
        lifecycleRegistry = new LifecycleRegistry((LifecycleOwner) getActivity());
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.weather_ui, container, false);
        ButterKnife.bind(this, view);
        getLifecycle().addObserver(new WeatherObserver());

        subscribeWithTempObserver();
        return view;
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        super.onPause();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        super.onStop();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    public void subscribeWithTempObserver() {
        try {
            ViewModelProviders.of((FragmentActivity) getActivity()).
                    get(WeatherViewModel.class).getWeatherData().observe((LifecycleOwner) getActivity(),
                    new Observer<WeatherResponse>() {
                @Override
                public void onChanged(@Nullable WeatherResponse weatherResponse) {
                    Log.i(LOG_TAG, "weather temp observed, " + weatherResponse.getTemp());
                    try {
                        updateViewWithData(weatherResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void updateViewWithData(WeatherResponse weatherObject) throws JSONException {
        String cityNameText = "Mumbai";
        cityName.setText(cityNameText);
        double tempValue = weatherObject.getTemp();
        double pressureValue = weatherObject.getPressure();
        double humidityValue = weatherObject.getHumidity();
        Log.i(LOG_TAG, "tempValue, " +tempValue);
        cityTemp.setText((tempValue) + "\u2103");
        cityPressure.setText("PRESSURE: " + pressureValue);
        cityHumidity.setText("HUMIDITY: " + humidityValue);
    }
}
