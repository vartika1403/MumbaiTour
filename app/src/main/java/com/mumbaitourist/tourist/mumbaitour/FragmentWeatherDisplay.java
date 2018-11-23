package com.mumbaitourist.tourist.mumbaitour;

import android.app.Notification;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentWeatherDisplay extends Fragment implements LifecycleOwner {
    private static final String LOG_TAG = FragmentWeatherDisplay.class.getSimpleName();
    private final static String API_KEY = "9351aee12441dbae1f55fb5ac1de496b";
    private JSONObject jsonObject;
    private WeatherViewModel weatherViewModel;
    private LifecycleRegistry lifecycleRegistry;

    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.city_temp)
    TextView cityTemp;
    @BindView(R.id.city_humidity)
    TextView cityHumidity;
    @BindView(R.id.city_pressure)
    TextView cityPressure;

    @Override
    //@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleRegistry = new LifecycleRegistry((LifecycleOwner) getActivity());
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_display, container, false);
        ButterKnife.bind(this, view);
        getLifecycle().addObserver(new WeatherObserver());

        subscribeWithTempObserver();
       /* try {
            getWeatherData();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
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
                    new Observer<Double>() {
                @Override
                public void onChanged(@Nullable Double temp) {
                   Log.i(LOG_TAG, "weather temp observed, " + temp);
                   updateViewWithTemp(temp);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateViewWithTemp(Double temp) {
        cityTemp.setText("Temperature:" + temp);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getWeatherData() throws JSONException {
        Log.i(LOG_TAG, "open weather api");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

       // Call<JsonElement> call = apiService.getCityWeather("Mumbai", API_KEY, "metric");

        //Log.i(LOG_TAG, "call url, " + call.request().url().toString());

       /* call.enqueue(new Callback<JsonElement>() {

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.i(LOG_TAG, "response, " + response.body().getAsJsonObject().get("main"));
                JsonElement jsonElement = response.body().getAsJsonObject().get("main");
                Gson gson = new Gson();
                final WeatherResponse weatherResponse = gson.fromJson(jsonElement, WeatherResponse.class);
                Log.i(LOG_TAG, "temp, " + weatherResponse.getTemp());
                if (getActivity() != null) {
                    (getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                updateViewWithData(weatherResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.i(LOG_TAG, "failure");
            }
        });*/
    }

    private void updateViewWithData(WeatherResponse weatherObject) throws JSONException {
        String cityNameText = "Mumbai";
        cityName.setText(cityNameText);
        double tempValue = weatherObject.getTemp();
        double pressureValue = weatherObject.getPressure();
        double humidityValue = weatherObject.getHumidity();
        Log.i(LOG_TAG, "tempValue, " +tempValue);
        cityTemp.setText("TEMPERATURE: " + tempValue);
        cityPressure.setText("PRESSURE: " + pressureValue);
        cityHumidity.setText("HUMIDITY: " + humidityValue);
    }
}
