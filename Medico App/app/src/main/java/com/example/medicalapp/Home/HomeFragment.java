package com.example.medicalapp.Home;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicalapp.API.NewsModel.ArticlesItem;
import com.example.medicalapp.Base.BaseFragment;
import com.example.medicalapp.Doctors.DoctorsFragment;
import com.example.medicalapp.Drugs.DrugParentFragment;
import com.example.medicalapp.R;
import com.example.medicalapp.Symptom.SymptomFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    View view;
    HomeNewsAdapter adapter;
    HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    Button drugs,symptom,doctors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        drugs=(Button)view.findViewById(R.id.drugs_btn);
        doctors=(Button)view.findViewById(R.id.doctors_btn);
        symptom=(Button)view.findViewById(R.id.symptom_btn);
        drugs.setOnClickListener(this);
        symptom.setOnClickListener(this);
        doctors.setOnClickListener(this);

        //initialize view Model

        homeViewModel = ViewModelProviders.of(HomeFragment.this).get(HomeViewModel.class);

        initRecyclerView(view);
        homeViewModel.loadNews();
        subscribeToLiveData();
        return view;
    }


    public void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);



    }

    public void subscribeToLiveData() {
        homeViewModel.articles.observe(this, new Observer<List<ArticlesItem>>() {
            @Override
            public void onChanged(List<ArticlesItem> articlesItems) {
                adapter = new HomeNewsAdapter(getContext(),articlesItems);
                recyclerView.setAdapter(adapter);
            }
        });

        homeViewModel.showLoading.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean show) {
                if (show)
                    showProgressBar(R.string.loading);
                else
                hideProgressDialog();
            }
        });

        homeViewModel.alertMessage.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Fragment fragment=null;
        switch (v.getId()){
            case R.id.drugs_btn:{
                fragment=new DrugParentFragment();
                break;
            }
            case R.id.doctors_btn: {
                fragment=new DoctorsFragment();
                break;
            }
            case R.id.symptom_btn:{
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                fragment=new SymptomFragment();
                break;}
        }
        getFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).addToBackStack(null).commit();
    }

}




