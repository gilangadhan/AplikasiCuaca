package com.dicoding.aplikasicuaca;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WeatherViewModel viewModel;
    private RecyclerView recyclerView;
    private EditText editText;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Daftarin ViewModel dengan Activity
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        // Inisialisasi View
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editCity);
        Button button = findViewById(R.id.btnCity);

        // inisialisasi Adapter
        adapter = new WeatherAdapter();

        // untuk Observe getWeatherList()
        viewModel.getWeatherList().observe(this, observer);

        //populate RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Action to button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cities = editText.getText().toString();
                if (!cities.isEmpty()) {
                    viewModel.setWeatherList(cities);
                }
            }
        });
    }

    private Observer<List<WeatherItems>> observer = new Observer<List<WeatherItems>>() {
        @Override
        public void onChanged(@Nullable List<WeatherItems> weatherItems) {
            // udah dapet datanya
            if (weatherItems != null) {
                //set ke adapter
                adapter.setWeatherItems(weatherItems);
            }
        }
    };
}
