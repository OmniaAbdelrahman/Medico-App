package com.example.medicalapp.Home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicalapp.API.NewsModel.ArticlesItem;
import com.example.medicalapp.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.ViewHolder> {
    public HomeNewsAdapter(Context context, List<ArticlesItem> articlesItems) {
        this.context = context;
        this.articlesItems = articlesItems;
    }

    Context context;
    List<ArticlesItem> articlesItems = new ArrayList<>();
    FoldingCell fc;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ArticlesItem item = articlesItems.get(position);
        holder.desc.setText(item.getDescription());
        holder.publishedAt.setText(item.getPublishedAt());
        holder.title.setText(item.getTitle());
        Glide.with(holder.itemView)
                .load(item.getUrlToImage())
                .into(holder.imageView1);
        Glide.with(holder.itemView)
                .load(item.getUrlToImage())
                .into(holder.imageView2);


        holder.view2.bringToFront();
        holder.view1.bringToFront();
        holder.view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fcc.toggle(false);
                holder.view2.bringToFront();

            }
        });
        holder.view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fcc.toggle(false);
                holder.view1.bringToFront();

            }
        });
        holder.readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seearticleonsite(item.getUrl().toString());
            }
        });
    }

    public void seearticleonsite(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    @Override
    public int getItemCount() {
        if (articlesItems == null)
            return 0;
        else return articlesItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView1, imageView2;
        private View view1, view2;
        FoldingCell fcc;
        private TextView title, desc, publishedAt, readmore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.news_imageView1);
            imageView2 = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.news_titleView1);
            view1 = itemView.findViewById(R.id.view_title);
            readmore = itemView.findViewById(R.id.diagnosis_IcdName);
            view2 = itemView.findViewById(R.id.view_content);
            fcc = (FoldingCell) itemView.findViewById(R.id.folding_cell);
            desc = itemView.findViewById(R.id.diagnosis_name2);
            publishedAt = itemView.findViewById(R.id.diagnosis_Accuracy);


        }
    }
}

