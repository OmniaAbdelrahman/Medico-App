package com.example.medicalapp.Diagnoses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.API.DiagnosesModel.DiagnosesResponse;
import com.example.medicalapp.R;

import java.util.List;

public class DiagnosisDialog extends DialogFragment {
    Fragment f;

    public DiagnosisDialog(Fragment f) {
        this.f = f;
    }

    View view;
    DiagnosesAdapter adapter;
    DiagnosesViewModel diagnosesViewModel;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.doctor_view, container, false);
        //getDialog().setCanceledOnTouchOutside(false);
        diagnosesViewModel = ViewModelProviders.of(f).get(DiagnosesViewModel.class);

        //diagnosesViewModel.loadDiadnoses();
        //System.out.println("a7a ///"+diagnosesViewModel.Diagnoses.getValue());

        //initRecyclerView(view);
        //subscribeToLiveData();
        return view;
    }



    public void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


    }

    public void subscribeToLiveData() {
        diagnosesViewModel.Diagnoses.observe(this, new Observer<List<DiagnosesResponse>>() {
            @Override
            public void onChanged(List<DiagnosesResponse> diagnosesResponses) {
                adapter = new DiagnosesAdapter(diagnosesResponses);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}


