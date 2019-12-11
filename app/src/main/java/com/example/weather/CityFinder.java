package com.example.weather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mancj.materialsearchbar.MaterialSearchBar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.Calendar;

public class CityFinder extends AppCompatActivity {
    private List<String> listCities;
    private MaterialSearchBar searchBar;
    ProgressDialog proDialog;
    Date prevTextChangeTime = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_finder);
        searchBar = findViewById(R.id.searchBar);
        searchBar.setEnabled(false);
        searchBar.setMaxSuggestionCount(10);
        new LoadCities().execute();
    }
    private class LoadCities extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proDialog = new ProgressDialog(CityFinder.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();

        }

        // This is run in a background thread
        @Override
        protected List<String> doInBackground(Void... args) {
            listCities = new ArrayList<>();
            try {
                StringBuilder builder = new StringBuilder();
                InputStream is = getResources().openRawResource(R.raw.city_list);
                GZIPInputStream gzipInputStream = new GZIPInputStream(is);
                InputStreamReader reader = new InputStreamReader(gzipInputStream);
                BufferedReader in = new BufferedReader(reader);
                String readed;
                while ((readed = in.readLine()) != null) {
                    builder.append(readed);
                }
                listCities = new Gson().fromJson(builder.toString(), new TypeToken<List<String>>() {
                }.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return listCities;
        }

        // This is called from background thread but runs in UI
        @Override
        protected void onProgressUpdate(Void... args) {
            super.onProgressUpdate(args);
        }

        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(final List<String> listcity) {
            super.onPostExecute(listcity);
            if (proDialog.isShowing())
                proDialog.dismiss();
            searchBar.setEnabled(true);
            searchBar.addTextChangeListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(Calendar.getInstance().getTime().getTime()-prevTextChangeTime.getTime()>1000) {
                        List<String> suggest = new ArrayList<>();
                        for (String search : listcity) {
                            if (suggest.size() < 10) {
                                if (search.toLowerCase().contains(searchBar.getText().toLowerCase())) {
                                    suggest.add(search);
                                }
                            }
                        }
                        searchBar.setLastSuggestions(suggest);
                    }
                    prevTextChangeTime = Calendar.getInstance().getTime();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
                @Override
                public void onSearchStateChanged(boolean enabled) {
                }

                @Override
                public void onSearchConfirmed(CharSequence text) {
                    Intent intent = new Intent(CityFinder.this, CurrentWeather.class);
                    intent.putExtra("message", searchBar.getText());
                    searchBar.disableSearch();
                    startActivity(intent);
                }

                @Override
                public void onButtonClicked(int buttonCode) {
                }
            });
            searchBar.setLastSuggestions(listcity);
        }
    }
}

