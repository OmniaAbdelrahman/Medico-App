package com.example.medicalapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.Base.BaseFragment;
import com.example.medicalapp.alarm.alarmadpater;
import com.example.medicalapp.alarm.alarminfo;
import com.example.medicalapp.database.alarmmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends BaseFragment {
    FloatingActionButton fab;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    com.example.medicalapp.alarm.alarmadpater alarmadpater;
    com.example.medicalapp.alarm.alarmviewmodel alarmviewmodel;
    public static boolean checkupdate =false;
    public static alarmmodel alarmupdate;
    public AlarmFragment() {
        // Required empty public constructor
    }
    View view;
    public static boolean fristtime = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_alarm, container, false);
        checkupdate =false;
        initi();
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        alarmviewmodel.loadalramdb();
        checkupdate =false;
        subscribetolivedata();
    }

    public void initi(){
        fab = view.findViewById(R.id.fab);
        alarmviewmodel = ViewModelProviders.of(this).get(com.example.medicalapp.alarm.alarmviewmodel.class);
        alarmviewmodel.loadalramdb();
        recyclerView = view.findViewById(R.id.alarmrecycler);
        manager =new LinearLayoutManager(activity);
        alarmadpater = new alarmadpater(alarmviewmodel.alarms.getValue());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(alarmadpater);
        on_clicks();
        subscribetolivedata();
    }
    public void subscribetolivedata(){alarmviewmodel.alarms.observe(this, new Observer<List<alarmmodel>>() {
        @Override
        public void onChanged(List<alarmmodel> alarmmodels) {
            alarmadpater.onchange(alarmmodels);
        }
    });}

    public void on_clicks(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(view.getContext(), alarminfo.class);
                startActivity(intent);
            }
        });
        alarmadpater.setDeleteclickaram(new alarmadpater.onclickalarm() {
            @Override
            public void onclick(int pos) {
                alarmviewmodel.broadcastalarm(view.getContext(),
                        alarmviewmodel.alarms.getValue().get(pos).getId(),false);
                alarmviewmodel.deletealarm(alarmviewmodel.alarms.getValue().get(pos));

            }
        });

        alarmadpater.setOnclickalarmswitch(new alarmadpater.onclickalarmswitch() {
                                               @Override
                                               public void onclickswitch(int pos, boolean type) {

                                                   if(type){
                                                       alarmviewmodel.alarms.getValue().get(pos).setIsworking(type);
                                                       alarmviewmodel.updatealarm(alarmviewmodel.alarms.getValue().get(pos));
                                                       alarmviewmodel.broadcastalarm(view.getContext(),
                                                               -1,true);

                                                   }
                                                   else{
                                                       alarmviewmodel.broadcastalarm(view.getContext(),
                                                               alarmviewmodel.alarms.getValue().get(pos).getId(),false);
                                                       alarmviewmodel.alarms.getValue().get(pos).setIsworking(type);
                                                       alarmviewmodel.updatealarm(alarmviewmodel.alarms.getValue().get(pos));
                                                   }}
                                           }
        );
        alarmadpater.setUpdatealarm(new alarmadpater.onclickalarm() {
                                        @Override
                                        public void onclick(int pos) {
                                            alarmupdate = alarmviewmodel.alarms.getValue().get(pos);
                                            checkupdate =true;
                                            Intent intent =new Intent(view.getContext(),alarminfo.class);
                                            startActivity(intent);
                                        }
                                    }
        );
    }


}
