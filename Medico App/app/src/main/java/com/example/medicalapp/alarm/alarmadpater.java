package com.example.medicalapp.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.example.medicalapp.R;
import com.example.medicalapp.database.alarmmodel;

import java.util.List;

public class alarmadpater extends RecyclerView.Adapter<alarmadpater.viewholder> {
List<alarmmodel>alarms;

    public alarmadpater(List<alarmmodel> alarms) {
        this.alarms = alarms;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alarmitem,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder viewholder, final int i) {
 viewholder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewholder.swipeLayout.addSwipeListener( new SimpleSwipeListener(){
           @Override
           public void onOpen(SwipeLayout layout){
               super.onOpen(layout);
           }
        });
        int hours =0;

        if(alarms.get(i).getHours() >=12){
            if(alarms.get(i).getHours() == 12){
                if(alarms.get(i).getMinutes()<10){
               viewholder.hourmintes.setText("12 : 0"+alarms.get(i).getMinutes() +" pm");}
                else{
                    viewholder.hourmintes.setText("12 : "+alarms.get(i).getMinutes() +" pm");}
          }
            else {
                hours = alarms.get(i).getHours()-12;
                if(alarms.get(i).getMinutes()<10)
                    viewholder.hourmintes.setText(hours+" : 0"+alarms.get(i).getMinutes() +" pm");
                else
                viewholder.hourmintes.setText(hours +" : "+alarms.get(i).getMinutes()+" pm");
            }
        }
        else if (alarms.get(i).getHours() < 12){
            if(alarms.get(i).getHours() == 0) {
                if(alarms.get(i).getMinutes()<10)
                    viewholder.hourmintes.setText("12 : 0"+alarms.get(i).getMinutes() +" am");
                else
                viewholder.hourmintes.setText("12 : " + alarms.get(i).getMinutes()+" am");

            }else{
                if(alarms.get(i).getMinutes()<10)
                    viewholder.hourmintes.setText(alarms.get(i).getHours()+" : 0"+alarms.get(i).getMinutes()
                            +" am");
                else
                viewholder.hourmintes.setText(alarms.get(i).getHours() +" : "+alarms.get(i).getMinutes()
                        +" am");

            }}
       viewholder.title.setText(alarms.get(i).getTitle());
        viewholder.aSwitch.setChecked(alarms.get(i).getIsworking());
viewholder.aSwitch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        onclickalarmswitch.onclickswitch(i,viewholder.aSwitch.isChecked());
    }
});
viewholder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        deleteclickaram.onclick(i);
    }
});
viewholder.edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        updatealarm.onclick(i);
    }
});

    }
    public void  onchange(List<alarmmodel>list){
        this.alarms= list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       if(alarms==null) return 0;
    return  alarms.size();
    }
    public onclickalarm deleteclickaram;
    public onclickalarm updatealarm;
    public onclickalarmswitch onclickalarmswitch;

    public void setOnclickalarmswitch(alarmadpater.onclickalarmswitch onclickalarmswitch) {
        this.onclickalarmswitch = onclickalarmswitch;
    }

    public void setDeleteclickaram(onclickalarm deleteclickaram) {
        this.deleteclickaram = deleteclickaram;
    }

    public void setUpdatealarm(onclickalarm updatealarm) {
        this.updatealarm = updatealarm;
    }


    public interface onclickalarm{
        public void onclick(int pos);
    }
    public interface onclickalarmswitch{
        public void onclickswitch(int pos, boolean type);
    }
    class viewholder extends RecyclerView.ViewHolder{
        SwipeLayout swipeLayout;
        TextView hourmintes,title;
        ImageView delete,edit;
        Switch aSwitch;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            swipeLayout =itemView.findViewById(R.id.swipe);
            hourmintes =itemView.findViewById(R.id.hoursminutes);
            title =itemView.findViewById(R.id.showtitlee);
            delete =itemView.findViewById(R.id.tvDelete);
            edit =itemView.findViewById(R.id.tvedit);
            aSwitch = itemView.findViewById(R.id.simpleSwitch);
        }
    }

}
