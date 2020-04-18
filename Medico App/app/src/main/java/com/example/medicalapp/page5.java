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
public class page5 extends Fragment  implements View.OnClickListener {

    TextView next,skip;

    public page5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_page5, container, false);
        next=view.findViewById(R.id.next5);
        skip=view.findViewById(R.id.skip5);
        next.setOnClickListener(this);
        skip.setOnClickListener(this);
        return view;    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next5:
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new page6()).commit();
                break;
            case R.id.skip5:
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

                break;
        }
    }
}
