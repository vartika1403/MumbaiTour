package com.example.vartikasharma.mumbaitour;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
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

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentWeatherDisplay extends Fragment {
    private static final String LOG_TAG = FragmentWeatherDisplay.class.getSimpleName();
    private final static String API_KEY = "9351aee12441dbae1f55fb5ac1de496b";
    private JSONObject jsonObject;

    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.city_temp)
    TextView cityTemp;
    @BindView(R.id.city_humidity)
    TextView cityHumidity;
    @BindView(R.id.city_pressure)
    TextView cityPressure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_display, container, false);
        ButterKnife.bind(this, view);
        try {
            getWeatherData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void getWeatherData() throws JSONException {
        Log.i(LOG_TAG, "open weather api");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<JsonElement> call = apiService.getCityWeather("Mumbai", API_KEY, "metric");

        Log.i(LOG_TAG, "call url, " + call.request().url().toString());

        call.enqueue(new Callback<JsonElement>() {
          /*  @Override
            public void onResponse(Response<JsonElement> response) {
                Log.i(LOG_TAG, "response, " + response.body());
            }*/

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.i(LOG_TAG, "response, " + response.body().getAsJsonObject().get("main"));
                JsonElement jsonElement = response.body().getAsJsonObject().get("main");
                Gson gson = new Gson();
               final WeatherResponse weatherResponse =  gson.fromJson(jsonElement, WeatherResponse.class);
                Log.i(LOG_TAG, "temp, " + weatherResponse.getTemp());
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

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.i(LOG_TAG, "failure");
            }

        });
    }

    private void updateViewWithData(WeatherResponse weatherObject) throws JSONException {
        String cityNameText = "Mumbai";
        cityName.setText(cityNameText);
       // JSONObject mainObject = (JSONObject) jsonObject.get("main");
       // Log.i(LOG_TAG, "mainObject, " + mainObject);
        double tempValue = weatherObject.getTemp();
        double pressureValue = weatherObject.getPressure();
        double humidityValue = weatherObject.getHumidity();
        Log.i(LOG_TAG, "tempValue, " +tempValue);
        cityTemp.setText("TEMPERATURE: " + tempValue);
        cityPressure.setText("PRESSURE: " + pressureValue);
        cityHumidity.setText("HUMIDITY: " + humidityValue);
    }
}
