package com.example.vartikasharma.mumbaitour;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class FragmentWeatherDisplay extends Fragment {
    private static final String URL= "http://samples.openweathermap.org/data/2.5/weather?q=Mumbai&appid=56235e515bfe83c18886a3cd09c9e086";
    private static final String LOG_TAG = FragmentWeatherDisplay.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_display, container, false);
        getWeatherData();
        return view;
    }

    private void getWeatherData() {
        Log.i(LOG_TAG, "open weather api");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "Can't fetch data", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                Log.i(LOG_TAG, jsonData);
            }
        });
    }


}
