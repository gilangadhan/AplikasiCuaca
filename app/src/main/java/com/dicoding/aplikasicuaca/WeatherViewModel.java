package com.dicoding.aplikasicuaca;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class WeatherViewModel extends ViewModel {
    private static String API_KEY = "7592812e318281651b0d3507480c1adf";
    //https://pastebin.com/N4SKE8Ez

    private MutableLiveData<List<WeatherItems>> weatherList = new MutableLiveData<>();

    public LiveData<List<WeatherItems>> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(String cities) {
        // request API
        AsyncHttpClient client = new AsyncHttpClient();

        //https://api.openweathermap.org/data/2.5/group?id=1642911,1650357,1627896
        // &units=metric&appid=7592812e318281651b0d3507480c1adf

        String url = "https://api.openweathermap.org/data/2.5/group?id=" + cities +
                "&units=metric&appid=" + API_KEY;

        final ArrayList<WeatherItems> items = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray responseArray = responseObject.getJSONArray("list");
                    for (int i = 0; i < responseArray.length(); i++) {
                        JSONObject object = responseArray.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(object);
                        items.add(weatherItems);
                    }

                    weatherList.postValue(items);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Error", error.getMessage());
            }
        });

    }
}
