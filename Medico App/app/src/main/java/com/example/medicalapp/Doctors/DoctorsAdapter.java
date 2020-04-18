package com.example.medicalapp.Doctors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bumptech.glide.Glide;
import com.example.medicalapp.API.DoctorsModel.DataItem;
import com.example.medicalapp.API.DoctorsModel.LanguagesItem;
import com.example.medicalapp.API.DoctorsModel.PhonesItem;
import com.example.medicalapp.API.DoctorsModel.PracticesItem;
import com.example.medicalapp.API.DoctorsModel.SpecialtiesItem;
import com.example.medicalapp.API.DoctorsModel.VisitAddress;
import com.example.medicalapp.R;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {

    List<DataItem> doctors;
    static List<DataItem> sesrcDoctors;
    static int c=-1;
    Fragment f;

    public DoctorsAdapter(List<DataItem> doctors, Fragment f) {
        DoctorsViewModel.check(doctors);
        c++;
        this.doctors = new ArrayList<>(doctors);
        if(c==0) {
            sesrcDoctors = new ArrayList<>();
            this.sesrcDoctors.addAll(this.doctors);
        }
        System.out.println(this.sesrcDoctors.size()+"/0 construc"+c);
        this.f = f;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_view, parent, false);
        return new ViewHolder(view);
    }

    public List<DataItem> getDoctors() {

        return doctors;
    }

    public void setDoctors(List<DataItem> doctors) {
        System.out.println(this.sesrcDoctors.size()+"/1");

        this.doctors=new ArrayList<>(doctors);
        System.out.println(this.sesrcDoctors.size()+"/2");

        notifyDataSetChanged();
        System.out.println(this.doctors.size()+"---");
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(doctors == null || doctors.isEmpty()){

        }
        else {
            final DataItem doctor = doctors.get(position);

            holder.name.setText("Dr." + doctor.getProfile().getFirstName());
            holder.cat.setText(doctor.getSpecialties().get(0).getCategory() + " , " + doctor.getSpecialties().get(0).getName());
            holder.country.setText(doctor.getPractices().get(0).getVisitAddress().getCity());
            holder.actor.setText(doctor.getSpecialties().get(0).getActor());
            holder.rate.setText(doctor.getRatings().get(0).toString());
            Glide.with(holder.itemView)
                    .load(doctor.getProfile().getImageUrl())
                    .into(holder.icon);

            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f.getFragmentManager().beginTransaction().replace(R.id.frame_container, new doctor_details(doctor)).addToBackStack(null).commit();
                }
            });
        }

    }



    @Override
    public int getItemCount() {
        return doctors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, rate, cat, country, actor;
        Button details;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dr_prof_slug);
            cat = itemView.findViewById(R.id.dr_special_cat_name);
            country = itemView.findViewById(R.id.dr_practic_visited_state_long);
            actor = itemView.findViewById(R.id.dr_special_actor);
            details = itemView.findViewById(R.id.dr_details_btn);
            icon = itemView.findViewById(R.id.dr_prof_image);
            rate = itemView.findViewById(R.id.dr_rate);


        }
    }
}