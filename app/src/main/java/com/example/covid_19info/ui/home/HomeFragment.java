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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView updated = root.findViewById(R.id.text_updated);
        final TextView totalCases = root.findViewById(R.id.text_total_cases);
        final TextView totalDeaths = root.findViewById(R.id.text_total_deaths);
        final TextView totalActive = root.findViewById(R.id.text_total_active_cases);
        final TextView totalRecovered = root.findViewById(R.id.text_total_recovered);

        homeViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getUpdated());

            totalCases.setText("Total\n\n" + data.getCases());
            totalActive.setText("Active\n\n" + data.getActive());
            totalDeaths.setText("Deaths\n\n" + data.getDeaths());
            updated.setText("Updated On : " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " "
                    + cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));
            totalRecovered.setText("Recovered\n\n" + data.getRecovered());

        });
        return root;
    }
}
