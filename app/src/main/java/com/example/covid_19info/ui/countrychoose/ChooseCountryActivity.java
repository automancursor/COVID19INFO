package com.example.covid_19info.ui.countrychoose;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

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

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.btnContinue)
    Button btnContinue;

    private List<Country> countryList;

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

        btnContinue.setEnabled(false);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnContinue.setEnabled(true);
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String txt = s.toString();

                if (TextUtils.isEmpty(txt) || !txt.contains("-") || !txt.contains("(") || !txt.contains(")"))
                    btnContinue.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick(R.id.btnContinue)
    void onClick(View view) {
        String country = autoCompleteTextView.getText().toString();
        mViewModel.saveCountry(country);

        Intent intent = new Intent(ChooseCountryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
