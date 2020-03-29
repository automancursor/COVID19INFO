package com.example.covid_19info.ui.global;

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

public class GlobalFragment extends Fragment {

    private GlobalViewModel globalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        globalViewModel =
                ViewModelProviders.of(this).get(GlobalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_global, container, false);


        final TextView textUpdated = root.findViewById(R.id.text_updated);
        final TextView totalCases = root.findViewById(R.id.text_total_cases);
        final TextView totalDeaths = root.findViewById(R.id.text_total_deaths);
        final TextView totalActive = root.findViewById(R.id.text_total_active_cases);
        final TextView totalRecovered = root.findViewById(R.id.text_total_recovered);

        globalViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getUpdated());

            totalCases.setText("Total\n\n" + data.getCases());
            totalActive.setText("Active\n\n" + data.getActive());
            totalDeaths.setText("Deaths\n\n" + data.getDeaths());
            totalRecovered.setText("Recovered\n\n" + data.getRecovered());
            textUpdated.setText("Updated On : " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " "
                    + cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));
        });


        return root;
    }
}
