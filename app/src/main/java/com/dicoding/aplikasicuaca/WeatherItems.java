package com.dicoding.aplikasicuaca;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherItems {

    private int id;
    private String name;
    private String currentWheater;
    private String description;
    private String temperature;

    // getter and setter -> alt + insert / control + enter
    // https://pastebin.com/jP4BmvVu
    WeatherItems(JSONObject jsonObject) {
        try {
            // get by id at JSON file
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String currentWeather = jsonObject.getJSONArray("weather")
                    .getJSONObject(0).getString("main");
            String description = jsonObject.getJSONArray("weather")
                    .getJSONObject(0).getString("description");
            Double temp = jsonObject.getJSONObject("main")
                    .getDouble("temp");
            String temperature = new DecimalFormat("##.##")
                    .format(temp - 273);

            // set to WeatherItems
            this.id = id;
            this.name = name;
            this.currentWheater = currentWeather;
            this.description = description;
            this.temperature = temperature;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentWheater() {
        return currentWheater;
    }

    public void setCurrentWheater(String cirrentWheater) {
        this.currentWheater = cirrentWheater;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
