package com.example.medicalapp.Doctors;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.medicalapp.API.DoctorsModel.DataItem;

public class dr_viewpager extends FragmentPagerAdapter {
    DataItem dataItem;


    public dr_viewpager(FragmentManager fm, DataItem dataItem) {
        super(fm);
        this.dataItem = dataItem;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "     Doctor's details    ";
        else if(position==1)
            return "    Doctor's location    ";
        else
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0) return new dr_small_details(dataItem);
        else if(position==1)
            return new dr_map(dataItem);
        else return null ;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
