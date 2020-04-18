package com.example.medicalapp.Drugs;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalapp.Base.BaseFragment;
import com.example.medicalapp.R;
import com.example.medicalapp.viewPager;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrugParentFragment extends BaseFragment {
    private ViewPager mViewPager;
    viewPager drugViewPager;
    Context context;
    int position;

    public DrugParentFragment() {
    }


    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drug, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.page_container);
        tabLayout = (TabLayout) view.findViewById(R.id.dr_tablayout);

        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    private void setupViewPager(ViewPager viewPager) {
        drugViewPager = new viewPager(getChildFragmentManager());
        for (int i = 1; i <= 20; i++) {
            drugViewPager.addFragment(new DrugFragment(String.valueOf(i)), " " + String.valueOf(i) + " ");
        }
        viewPager.setAdapter(drugViewPager);
    }




}
