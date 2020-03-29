package com.example.covid_19info.ui.countrychoose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19info.MainActivity;
import com.example.covid_19info.R;
import com.example.covid_19info.data.model.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCountryActivity extends AppCompatActivity {

    private List<Country> countryList;
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
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

        ArrayAdapter<Country> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryList);
        autoCompleteTextView.setAdapter(dataAdapter);

    }

    @OnClick(R.id.btnContinue)
    void onClick(View view) {
        String country = autoCompleteTextView.getText().toString();
        mViewModel.saveCountry(country, true);
        Intent intent = new Intent(ChooseCountryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
