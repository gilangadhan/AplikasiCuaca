package com.dicoding.aplikasicuaca;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherItems> weatherItems = new ArrayList<>();

    public void setWeatherItems(List<WeatherItems> weatherItems) {
        if (weatherItems != null) {
            this.weatherItems.clear();
            this.weatherItems = weatherItems;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_items, viewGroup, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        weatherViewHolder.bind(weatherItems.get(i));
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaKota;
        TextView tvTemperature;
        TextView tvDescription;

        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaKota = itemView.findViewById(R.id.textKota);
            tvDescription = itemView.findViewById(R.id.textDesc);
            tvTemperature = itemView.findViewById(R.id.textTemp);
        }

        void bind(WeatherItems weatherItems) {
            tvNamaKota.setText(weatherItems.getName());
            tvDescription.setText(weatherItems.getDescription());
            tvTemperature.setText(weatherItems.getTemperature());
        }
    }
}
