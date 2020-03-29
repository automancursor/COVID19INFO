package com.example.covid_19info.ui.countrychoose;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19info.R;
import com.example.covid_19info.data.model.Jhucsse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseCountryActivity extends AppCompatActivity {

    private List<Jhucsse> countryList;
    @BindView(R.id.spinner)
    Spinner spinner;
    private ChooseCountryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(ChooseCountryViewModel.class);

        countryList = mViewModel.getCountries();
        // Creating adapter for spinner
        ArrayAdapter<Jhucsse> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryList);
        spinner.setAdapter(dataAdapter);


    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
