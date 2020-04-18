package com.example.medicalapp.Drugs;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.medicalapp.API.DrugModel.DrugResponse;
import com.example.medicalapp.Base.BaseFragment;
import com.example.medicalapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class DrugFragment extends BaseFragment {

    TabLayout tabLayout;
    RecyclerView recyclerView;
    DrugsAdapter Adapter;
    SearchView searchview;
    Context context;
    DrugsViewModel drugsViewModel;
    public DrugFragment(){}

    String pageNumber;

    public DrugFragment(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drug_small, container, false);
        //Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        //context.setSupportActionBar(toolbar);
        //initialize view Model
        searchview=view.findViewById(R.id.drug_searchView);

        tabLayout = view.findViewById(R.id.dr_tablayout);
        drugsViewModel = ViewModelProviders.of(this).get(DrugsViewModel.class);
        drugsViewModel.loadDrugs(pageNumber);
        initRecyclerView(view);
        subscribeToLiveData();
        return view;
    }


    public void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);


    }

    public void subscribeToLiveData() {

        drugsViewModel.Drug.observe(this, new Observer<DrugResponse>() {
            @Override
            public void onChanged(DrugResponse drugResponse) {
                Adapter = new DrugsAdapter(drugResponse.getRecords(), getContext());
                recyclerView.setAdapter(Adapter);
                //setTabLayoutWithNewsSources();

            }
        });
        drugsViewModel.showLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean show) {
                if(show)showProgressBar(R.string.loading);
                else hideProgressDialog();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();

        searchview.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                return false;
            }
        });
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}
