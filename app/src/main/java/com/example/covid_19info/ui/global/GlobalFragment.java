package com.example.covid_19info.ui.global;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19info.R;
import com.example.covid_19info.data.model.GlobalData;
import com.example.covid_19info.utils.AppUtils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GlobalFragment extends Fragment {

    private GlobalViewModel globalViewModel;

    @BindView(R.id.text_updated)
    TextView textUpdated;
    @BindView(R.id.text_total_cases)
    TextView totalCases;
    @BindView(R.id.text_total_deaths)
    TextView totalDeaths;
    @BindView(R.id.text_total_active_cases)
    TextView totalActive;
    @BindView(R.id.text_total_recovered)
    TextView totalRecovered;
    @BindView(R.id.ll)
    LinearLayout linearLayout;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private GlobalListAdapter adapter;
    private ArrayList<GlobalData> allData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        globalViewModel =
                ViewModelProviders.of(this).get(GlobalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_global, container, false);
        unbinder = ButterKnife.bind(this, root);

        adapter = new GlobalListAdapter(allData);
        recyclerView.setAdapter(adapter);

        globalViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getUpdated());

            totalCases.setVisibility(View.VISIBLE);
            totalActive.setVisibility(View.VISIBLE);
            totalDeaths.setVisibility(View.VISIBLE);
            totalRecovered.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);

            totalCases.setText(AppUtils.formatText("Total Cases", data.getCases()));
            totalActive.setText(AppUtils.formatText("Active Cases", data.getActive()));
            totalDeaths.setText(AppUtils.formatText("Total Deaths", data.getDeaths()));
            totalRecovered.setText(AppUtils.formatText("Total Recovered", data.getRecovered()));

            String sf = String.format("%s%s:%s %s/%s/%s", "Updated On => ", cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.DATE),
                    (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.YEAR));
            textUpdated.setText(sf);
        });

        globalViewModel.getAllData().observe(getViewLifecycleOwner(), allData -> {
            this.allData.clear();
            this.allData.addAll(allData);
            adapter.notifyDataSetChanged();
        });


        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
