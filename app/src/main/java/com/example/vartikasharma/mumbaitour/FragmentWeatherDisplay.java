package com.example.vartikasharma.mumbaitour;

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

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentWeatherDisplay extends Fragment {
    private static final String URL= "http://samples.openweathermap.org/data/2.5/weather?" +
            "q=Mumbai&appid=56235e515bfe83c18886a3cd09c9e086";
    private static final String LOG_TAG = FragmentWeatherDisplay.class.getSimpleName();
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
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "Can't fetch data", Toast.LENGTH_LONG).show();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response response) throws IOException {
                final String jsonData = response.body().string();
                Log.i(LOG_TAG, "json, "+ jsonData);
                try {
                    jsonObject = new JSONObject(jsonData);
                    Log.i(LOG_TAG, "jsonArray, "+ jsonObject);

                    (getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                updateViewWithData(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        if (jsonObject != null) {
            updateViewWithData(jsonObject);
        }
    }

    private void updateViewWithData(JSONObject jsonObject) throws JSONException {
        String cityNameText = (String) jsonObject.get("name");
        cityName.setText(cityNameText);
        JSONObject mainObject = (JSONObject) jsonObject.get("main");
        Log.i(LOG_TAG, "mainObject, " + mainObject);
        Double tempValue = (Double) mainObject.get("temp");
        Integer pressureValue = (Integer) mainObject.get("pressure");
        Integer humidityValue = (Integer) mainObject.get("humidity");
        Log.i(LOG_TAG, "tempValue, " +tempValue);
        cityTemp.setText(" " + tempValue);
        cityPressure.setText(" " + pressureValue);
        cityHumidity.setText(" " + humidityValue);
    }
}
