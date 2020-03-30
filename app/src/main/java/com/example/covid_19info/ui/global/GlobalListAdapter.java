package com.example.covid_19info.ui.global;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19info.R;
import com.example.covid_19info.data.model.GlobalData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

class GlobalListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private final ArrayList<GlobalData> data;
    private ArrayList<GlobalData> filterData;

    GlobalListAdapter(ArrayList<GlobalData> data) {

        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);

        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DataViewHolder viewHolder = (DataViewHolder) holder;

        GlobalData dt = data.get(position);

        if (dt != null) {
            viewHolder.countryName.setText(dt.getCountry());
            viewHolder.totalCases.setText(String.valueOf(dt.getCases()));
            viewHolder.totalDeaths.setText(String.valueOf(dt.getDeaths()));
            viewHolder.todayCases.setText(String.valueOf(dt.getTodayCases()));
            viewHolder.todayDeaths.setText(String.valueOf(dt.getTodayDeaths()));
        }
    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String filterString = charSequence.toString();
                if (filterString.isEmpty()) {
                    filterData = data;
                } else {
                    ArrayList<GlobalData> filteredList = new ArrayList<>();
                    for (GlobalData dt : data) {

                        if (dt.getCountry().toLowerCase().contains(filterString.toLowerCase())) {
                            filteredList.add(dt);
                        }
                    }
                    filterData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterData = (ArrayList<GlobalData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_country_name)
        TextView countryName;
        @BindView(R.id.text_total_case)
        TextView totalCases;
        @BindView(R.id.text_total_death)
        TextView totalDeaths;
        @BindView(R.id.text_total_today_case)
        TextView todayCases;
        @BindView(R.id.text_total_today_death)
        TextView todayDeaths;

        DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}