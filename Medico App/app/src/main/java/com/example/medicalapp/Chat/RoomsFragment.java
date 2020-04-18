package com.example.medicalapp.Chat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.medicalapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsFragment extends Fragment {
    private RecyclerView mRoom;
    private RecyclerView.Adapter mRoomAdapter;
    private RecyclerView.LayoutManager mRoomLayoutManager;
    ArrayList<RoomInfo> RoomList;
    String name;

    public RoomsFragment(String name) {
        this.name=name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rooms, container, false);
        initializeMedia(view);




        return view;
    }
    private void initializeMedia(View view) {
        RoomList=new ArrayList<>();
        RoomInfo roomInfo1= new RoomInfo("Orthopedic",R.drawable.orthopedics);
        RoomInfo roomInfo2= new RoomInfo("Pediatric",R.drawable.pediatric);
        RoomInfo roomInfo3= new RoomInfo("Ophthalmology",R.drawable.ophthalmology);
        RoomInfo roomInfo4= new RoomInfo("Urology",R.drawable.urology);
        RoomInfo roomInfo5= new RoomInfo("Radiology",R.drawable.radiology);
        RoomInfo roomInfo6= new RoomInfo("cardiology",R.drawable.cardiology);
        RoomInfo roomInfo7= new RoomInfo("endoscopy",R.drawable.endoscopy);
        RoomInfo roomInfo8= new RoomInfo("neurology",R.drawable.neurology);
        RoomInfo roomInfo9= new RoomInfo("psychiatry",R.drawable.psychiatry);
        RoomInfo roomInfo10= new RoomInfo("surgery",R.drawable.surgery);


        int e=R.drawable.orthopedics;
        RoomList.add(roomInfo1);
        RoomList.add(roomInfo2);
        RoomList.add(roomInfo3);
        RoomList.add(roomInfo4);
        RoomList.add(roomInfo5);
        RoomList.add(roomInfo6);
        RoomList.add(roomInfo7);
        RoomList.add(roomInfo8);
        RoomList.add(roomInfo9);
        RoomList.add(roomInfo10);

        mRoom= view.findViewById(R.id.room_recycleview);
        mRoom.setNestedScrollingEnabled(false);
        mRoom.setHasFixedSize(false);
        mRoomLayoutManager = new LinearLayoutManager(getContext());
        mRoom.setLayoutManager(mRoomLayoutManager);
        mRoomAdapter = new RoomAdapter(RoomList,RoomsFragment.this,name);
        mRoom.setAdapter(mRoomAdapter);
    }

}
