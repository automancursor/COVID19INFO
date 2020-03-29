package com.example.covid_19info.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19info.R;
import com.example.covid_19info.data.model.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment {

    private SettingViewModel mViewModel;

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, root);
      /*  final TextView textView = root.findViewById(R.id.text_setting);
        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        List<Country> countryList = mViewModel.getCountries();
        ArrayAdapter<Country> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, countryList);
        autoCompleteTextView.setAdapter(dataAdapter);

        return root;
    }


}
