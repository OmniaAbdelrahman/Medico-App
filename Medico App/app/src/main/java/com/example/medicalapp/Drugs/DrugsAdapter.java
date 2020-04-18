package com.example.medicalapp.Drugs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.API.APIManager;
import com.example.medicalapp.API.DrugModel.DrugInfoModel.DrugInfoResponse;
import com.example.medicalapp.API.DrugModel.RecordsItem;
import com.example.medicalapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.ViewHolder>  implements Filterable {
    List<RecordsItem> drugs;
    List<RecordsItem> searchdrugs;

    Dialog drugDetails;
    Context context;
    public DrugsAdapter(List<RecordsItem> drugs, Context context) {
        this.drugs =drugs;
        this.searchdrugs =new ArrayList<>(drugs);
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.drugs_item_view,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);

        drugDetails=new Dialog(context);
        drugDetails.setContentView(R.layout.drugs_details);
        drugDetails.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewHolder.druglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                APIManager.getDrugApi().getDrugInfo(drugs.get(viewHolder.getAdapterPosition()).getChemblId()).enqueue(new Callback<DrugInfoResponse>() {
                    @Override
                    public void onResponse(Call<DrugInfoResponse> call, Response<DrugInfoResponse> response) {
                        if(!response.isSuccessful()){
                            System.out.println("a7aaaaaaaaaaaaaa");
                            return;
                        }

                        TextView name=(TextView)drugDetails.findViewById(R.id.name_view);
                        TextView fda_approved_view=(TextView)drugDetails.findViewById(R.id.fda_approved_view);
                        TextView antiNeoplastic_view=(TextView)drugDetails.findViewById(R.id.antiNeoplastic_view);
                        TextView immunotherapy_view=(TextView)drugDetails.findViewById(R.id.immunotherapy_view);
                        name.setText(response.body().getName());
                        antiNeoplastic_view.setText("anti Neoplastic : "+String.valueOf(response.body().isAntiNeoplastic()));
                        immunotherapy_view.setText("immunotherapy : "+String.valueOf(response.body().isImmunotherapy()));
                        fda_approved_view.setText("fda approved : "+String.valueOf(response.body().isFdaApproved()));
                    }

                    @Override
                    public void onFailure(Call<DrugInfoResponse> call, Throwable t) {

                        System.out.println("yallll"+t.getLocalizedMessage());

                    }
                });
                drugDetails.show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        RecordsItem item=drugs.get(position);

        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    @Override
    public Filter getFilter() {
        return drugfilter;
    }

    private Filter drugfilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RecordsItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(searchdrugs);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (RecordsItem item : searchdrugs) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            drugs.clear();
            drugs.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    //drugs.get(viewHolder.getAdapterPosition()).getChemblId()
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,druginfo;
        ConstraintLayout druglayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            druglayout=itemView.findViewById(R.id.druglayout);
            name=itemView.findViewById(R.id.drug_name);


        }
    }
}
