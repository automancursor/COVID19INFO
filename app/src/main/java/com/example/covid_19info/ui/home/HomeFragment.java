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

       /* final TextView textUpdated = root.findViewById(R.id.text_updated);
        final TextView totalCases = root.findViewById(R.id.text_total_cases);
        final TextView countryName = root.findViewById(R.id.text_country_name);
        final TextView totalDeaths = root.findViewById(R.id.text_total_deaths);
        final TextView todayCases = root.findViewById(R.id.text_total_today_cases);
        final TextView todayDeaths = root.findViewById(R.id.text_total_today_deaths);
        final TextView totalActive = root.findViewById(R.id.text_total_active_cases);
        final TextView totalRecovered = root.findViewById(R.id.text_total_recovered);*/

        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getUpdated());

            totalCases.setText(formatText("Total", data.getCases()));
            totalActive.setText(formatText("Active", data.getActive()));
            totalDeaths.setText(formatText("Deaths", data.getDeaths()));

            countryName.setText(String.valueOf(data.getCountry()));

            todayCases.setText(formatText("Today's Cases", data.getTodayCases()));
            todayDeaths.setText(formatText("Today's Deaths", data.getTodayDeaths()));
            totalRecovered.setText(formatText("Recovered", data.getRecovered()));


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

    private String formatText(String title, int data) {
        return String.format("%s\n\n %s", title, data);
    }
}
