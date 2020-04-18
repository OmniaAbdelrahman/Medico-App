package com.example.medicalapp.Symptom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.API.SymptomModel.SymptomResponse;
import com.example.medicalapp.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.ViewHolder> {
    List<SymptomResponse> symptoms = new ArrayList<>();
    public  List<String> checkedSymptoms = new ArrayList<>();
    View view;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        SymptomResponse item = symptoms.get(position);
        holder.symptom.setText(item.getName());
        holder.symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.symptom.isChecked())
                {
                    System.out.println("****"+String.valueOf(symptoms.get(position).getID()));
                    checkedSymptoms.add(String.valueOf(symptoms.get(position).getID()));
                }
                else {
                    for (int i = 0; i < checkedSymptoms.size(); )
                        if (checkedSymptoms.get(i).trim().contains(String.valueOf(symptoms.get(position).getID()))) {
                            checkedSymptoms.remove(i);
                            break;
                        }
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        if (symptoms == null) return 0;
        return symptoms.size();
    }

    public void Changed(List<SymptomResponse> symptoms) {
        this.symptoms = symptoms;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox symptom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptom = itemView.findViewById(R.id.symptom_radio);
        }
    }
}