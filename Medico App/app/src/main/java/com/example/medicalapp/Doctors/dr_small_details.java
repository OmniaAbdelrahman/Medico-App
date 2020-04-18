package com.example.medicalapp.Doctors;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicalapp.API.DoctorsModel.DataItem;
import com.example.medicalapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class dr_small_details extends Fragment {

    TextView phone,hours,gender,city,bio,language,slug,actor,cat,desc,edu;
    DataItem dataItem;
    public dr_small_details(DataItem dataItem) {
        this.dataItem=dataItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dr_small_details, container, false);
        init(view);
        return view;
    }
void init(View view){
    phone =view.findViewById(R.id.dr_phone);
    phone.setText("Phone number : "+dataItem.getPractices().get(0).getPhones().get(0).getNumber());
    language =view.findViewById(R.id.dr_language);
    language.setText("Language : "+dataItem.getPractices().get(0).getLanguages().get(0).getName());
    city =view.findViewById(R.id.dr_city);
    city.setText("City : "+dataItem.getPractices().get(0).getVisitAddress().getCity());
    gender =view.findViewById(R.id.dr_gender);
    gender.setText("Gender : "+dataItem.getProfile().getGender());
    hours =view.findViewById(R.id.dr_hours);
    hours.setText("Work hours : From 4 to 10");
    bio =view.findViewById(R.id.dr_prof_bio);
    bio.setText("Bio : "+dataItem.getProfile().getBio());

    slug =view.findViewById(R.id.dr_prof_slug);
    slug.setText("Full name : "+dataItem.getProfile().getFirstName() +" "+dataItem.getProfile().getLastName());
    actor =view.findViewById(R.id.dr_prof_slug);
    actor.setText("Actor : "+dataItem.getSpecialties().get(0).getActor());
    cat =view.findViewById(R.id.dr_cat);
    cat.setText("Category : "+dataItem.getSpecialties().get(0).getCategory());
    desc =view.findViewById(R.id.dr_description);
    desc.setText("Description : "+dataItem.getSpecialties().get(0).getDescription());
    edu =view.findViewById(R.id.dr_edu);
    edu.setText(dataItem.getEducations().get(0).toString());

}
}
