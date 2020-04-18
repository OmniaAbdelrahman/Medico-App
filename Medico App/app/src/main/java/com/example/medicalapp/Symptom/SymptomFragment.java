package com.example.medicalapp.Symptom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicalapp.Base.BaseFragment;
import com.example.medicalapp.Diagnoses.DiagnosesFragment;
import com.example.medicalapp.Diagnoses.DiagnosisDialog;
import com.example.medicalapp.Home.HomeViewModel;
import com.example.medicalapp.R;

import java.util.List;

import com.example.medicalapp.API.SymptomModel.SymptomResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class SymptomFragment extends BaseFragment {

    SymptomAdapter adapter;
    SymptomViewModel symptomViewModel;
    HomeViewModel homeViewModel;
    RecyclerView recyclerView;

    public SymptomFragment() {
        // Required empty public constructor
    }

    View view;
    Button check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_symptom, container, false);
        check = view.findViewById(R.id.check_sdiagnoses_btn);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container,new DiagnosesFragment(adapter.checkedSymptoms)).addToBackStack(null).commit();

            }
        });
        symptomViewModel = ViewModelProviders.of(SymptomFragment.this).get(SymptomViewModel.class);
        symptomViewModel.loadSymptom();
        initRecyclerView(view);
        subscribeToLiveData();
        return view;
    }


    public void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.symptom_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new SymptomAdapter();


    }

    public void subscribeToLiveData() {
        symptomViewModel.symptom.observe(this, new Observer<List<SymptomResponse>>() {
            @Override
            public void onChanged(List<SymptomResponse> symptomResponses) {
                adapter.Changed(symptomResponses);
                recyclerView.setAdapter(adapter);
            }
        });
        symptomViewModel.showLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean show) {
                if(show)showProgressBar(R.string.loading);
                else
                    hideProgressDialog();

            }
        });

    }
}