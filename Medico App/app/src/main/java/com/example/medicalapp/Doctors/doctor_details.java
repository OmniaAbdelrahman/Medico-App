package com.example.medicalapp.Doctors;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medicalapp.API.DoctorsModel.DataItem;
import com.example.medicalapp.R;
import com.example.medicalapp.viewPager;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class doctor_details extends Fragment {

    dr_viewpager dr_viewpager;
    TextView name,cat_name,rate ;
    ImageView imageView;
    private ViewPager mViewPager;
    viewPager drugViewPager;
    DataItem  dataItem;
    public doctor_details(DataItem  dataItem) {
        this.dataItem=dataItem;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doctor_details, container, false);
        name=view.findViewById(R.id.dr_name2);
        cat_name=view.findViewById(R.id.dr_cat_name2);
        rate=view.findViewById(R.id.dr_rate2);
        imageView=view.findViewById(R.id.imagedrdetails);

        mViewPager = (ViewPager) view.findViewById(R.id.page_container);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.dr_tablayout);

        name.setText("Dr."+dataItem.getProfile().getFirstName());
        rate.setText(dataItem.getRatings().get(0).toString());
        cat_name.setText(dataItem.getSpecialties().get(0).getCategory() + " , " + dataItem.getSpecialties().get(0).getName());
        Glide.with(view)
                .load(dataItem.getProfile().getImageUrl())
                .into(imageView);
        dr_viewpager=new dr_viewpager(getChildFragmentManager(),dataItem);
        mViewPager.setAdapter(dr_viewpager);
        tabLayout.setupWithViewPager(mViewPager);
        return  view;
    }

}
