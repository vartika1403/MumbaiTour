package com.mumbaitourist.tourist.mumbaitour;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    private static final String LOG_TAG = WeatherViewModel.class.getSimpleName();
    private final static String API_KEY = "9351aee12441dbae1f55fb5ac1de496b";
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Double> weatherTemp = new MutableLiveData<>();

    public LiveData<Double> getWeatherData() throws JSONException {
        Log.i(LOG_TAG, "open weather api");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<JsonElement> call = apiService.getCityWeather("Mumbai", API_KEY, "metric");

       // Log.i(LOG_TAG, "call url, " + call.request().url().toString());

       /* call.enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.body() != null) {
                 //   Log.i(LOG_TAG, "response, " + response.body().getAsJsonObject().get("main"));
                   // JsonElement jsonElement = response.body().getAsJsonObject().get("main");
                    Gson gson = new Gson();
                  *//*  final WeatherResponse weatherResponse = gson.fromJson(jsonElement, WeatherResponse.class);
                    Log.i(LOG_TAG, "temp, " + weatherResponse.getTemp());
                    weatherTemp.setValue(weatherResponse.getTemp());*//*
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }

        });*/
        return weatherTemp;
    }

    @Override
    public void onCleared() {
        super.onCleared();;
    }


}
