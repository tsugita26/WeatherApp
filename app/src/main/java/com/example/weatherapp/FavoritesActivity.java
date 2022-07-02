package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private static final String INTENT_CITY_NAMES = "city_names";
    private static final String INTENT_RESULT = "result";
    private static final String INTENT_REMOVED = "removed";
    private ArrayList<String> removedCities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        findViewById(R.id.app_bar_layout).setOutlineProvider(null);

        Intent intent = getIntent();
        ArrayList<String> cityNames = intent.getStringArrayListExtra(INTENT_CITY_NAMES);

        RecyclerView rvFavoritesList = findViewById(R.id.favorites_list);
        FavoritesListAdapter favoritesListAdapter = new FavoritesListAdapter(this, cityNames);
        rvFavoritesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvFavoritesList.setAdapter(favoritesListAdapter);
    }

    public void onFavoriteItemClick(String cityName) {
        Intent intent = new Intent();
        intent.putExtra(INTENT_RESULT, cityName);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onRemoveBtnClick(String cityName) {
        removedCities.add(cityName);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        for (String removedCity : removedCities) {
            Log.d("WeatherApp", removedCity);
        }
        intent.putExtra(INTENT_REMOVED, removedCities);
        setResult(RESULT_OK, intent);
        finish();
    }
}