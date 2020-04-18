package com.example.medicalapp.Diagnoses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.API.DiagnosesModel.DiagnosesResponse;
import com.example.medicalapp.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

public class DiagnosesAdapter  extends RecyclerView.Adapter<DiagnosesAdapter.ViewHolder> {


    public DiagnosesAdapter(List<DiagnosesResponse> diagnoses) {
        this.diagnoses = new ArrayList<>();
        this.diagnoses.addAll(diagnoses);
    }
    public  int size=0;
    List<DiagnosesResponse> diagnoses;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.diagnosis_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        DiagnosesResponse item=diagnoses.get(position);
        holder.name.setText(item.getIssue().getName());
        holder.name2.setText(item.getIssue().getName());
        holder.accur.setText(String.valueOf("Accuracy : "+item.getIssue().getAccuracy()));
        holder.rank.setText(String.valueOf("Ranking : "+item.getIssue().getRanking()));
        holder.icd.setText("IcdName : "+item.getIssue().getIcdName());
        holder.prof.setText("ProfName :" +item.getIssue().getProfName());
        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.folding_cell_diagnosis.toggle(false);
            }
        });
        holder.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.folding_cell_diagnosis.toggle(false);
            }
        });


    }


    @Override
    public int getItemCount() {
//        if(diagnoses.size()==0||diagnoses==null)
//        return 0;
//        else
        size=diagnoses.size();
//        System.out.println(size+"*/*/");
        return diagnoses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,name2,accur,icd,prof,rank;
        FoldingCell folding_cell_diagnosis;
        ConstraintLayout layout1,layout2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folding_cell_diagnosis=(FoldingCell)itemView.findViewById(R.id.folding);
              name=itemView.findViewById(R.id.too);
            name2=itemView.findViewById(R.id.diagnosis_name2);
            accur=itemView.findViewById(R.id.diagnosis_Accuracy);
            layout1=itemView.findViewById(R.id.viewdiagnosis);
            layout2=itemView.findViewById(R.id.diagnosis_detail);

            rank=itemView.findViewById(R.id.diagnosis_Ranking);
            prof=itemView.findViewById(R.id.diagnosis_ProfName);
            icd=itemView.findViewById(R.id.diagnosis_IcdName);

        }
    }
}
