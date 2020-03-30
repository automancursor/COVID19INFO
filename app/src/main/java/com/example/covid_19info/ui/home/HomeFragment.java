package com.example.covid_19info.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19info.R;
import com.example.covid_19info.utils.AppUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    @BindView(R.id.text_updated)
    TextView textUpdated;
    @BindView(R.id.text_total_cases)
    TextView totalCases;
    @BindView(R.id.text_country_name)
    TextView countryName;
    @BindView(R.id.text_total_deaths)
    TextView totalDeaths;
    @BindView(R.id.text_total_today_cases)
    TextView todayCases;
    @BindView(R.id.text_total_today_deaths)
    TextView todayDeaths;
    @BindView(R.id.text_total_active_cases)
    TextView totalActive;
    @BindView(R.id.text_total_recovered)
    TextView totalRecovered;
    private Unbinder unbinder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, root);

        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getUpdated());

            totalCases.setText(AppUtils.formatText("Total", data.getCases()));
            totalActive.setText(AppUtils.formatText("Active", data.getActive()));
            totalDeaths.setText(AppUtils.formatText("Deaths", data.getDeaths()));

            countryName.setText(String.valueOf(data.getCountry()));

            todayCases.setText(AppUtils.formatText("Today's Cases", data.getTodayCases()));
            todayDeaths.setText(AppUtils.formatText("Today's Deaths", data.getTodayDeaths()));
            totalRecovered.setText(AppUtils.formatText("Recovered", data.getRecovered()));


            String sf = String.format("%s%s:%s %s/%s/%s", "Updated On :", cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.DATE),
                    (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.YEAR));
            textUpdated.setText(sf);

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
