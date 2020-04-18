package com.example.medicalapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicalapp.Home.HomeFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class page2 extends Fragment implements View.OnClickListener {

    TextView next,skip;

    public page2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_page2, container, false);
        next=view.findViewById(R.id.next2);
        skip=view.findViewById(R.id.skip2);
        next.setOnClickListener(this);
        skip.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next2:
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new page3()).commit();
                break;
            case R.id.skip2:
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

                break;
        }
    }
}
