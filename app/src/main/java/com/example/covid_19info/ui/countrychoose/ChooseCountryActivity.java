package com.example.covid_19info.ui.countrychoose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19info.MainActivity;
import com.example.covid_19info.R;
import com.example.covid_19info.data.model.Jhucsse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCountryActivity extends AppCompatActivity {

    private List<Jhucsse> countryList;
    @BindView(R.id.spinner)
    Spinner spinner;
    private ChooseCountryViewModel mViewModel;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(ChooseCountryViewModel.class);

        countryList = mViewModel.getCountries();

        if (mViewModel.isCountrySelected()) {
            Intent intent = new Intent(ChooseCountryActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        ArrayAdapter<Jhucsse> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryList);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btnContinue)
    void onClick(View view) {
        mViewModel.saveCountry(country, true);
        Intent intent = new Intent(ChooseCountryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
