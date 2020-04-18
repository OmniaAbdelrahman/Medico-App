package com.example.medicalapp.Chat;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.net.Uri;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalapp.Home.HomeFragment;
import com.example.medicalapp.HomeActivity;
import com.example.medicalapp.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    DatabaseReference root;
    String temp_key, currenttime, RoomName, UserName;
    EditText message_edittexxt;
    ImageButton send_text, send_image, back;
    ArrayList<String> arrayList = new ArrayList();
    ArrayAdapter<String> arrayAdapter;
    TextView border_txt;
    ImageView border_img;
    private RecyclerView mChat, mMedia;
    private RecyclerView.Adapter mChatAdapter, mMediaAdapter;
    private RecyclerView.LayoutManager mChatLayoutManager, mMediaLayoutManager;
    private FirebaseAuth mAuth;
    ArrayList<MessageObject> messageList;
    ArrayList<String> mediaIdList = new ArrayList<>();
    ArrayList<String> mediaUriList = new ArrayList<>();
    int photo;


    public ChatFragment(String RoomName, int photo, String name) {
        this.RoomName = RoomName;
        this.photo = photo;
        this.UserName = name;

    }


    public ChatFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        border_txt = (TextView) view.findViewById(R.id.border_txt);
        border_txt.setText(RoomName);

        border_img = (ImageView) view.findViewById(R.id.border_img);
        border_img.setImageResource(0);
        border_img.setImageResource(photo);


        Fresco.initialize(getContext());

        send_image = (ImageButton) view.findViewById(R.id.addMedia);
        root = FirebaseDatabase.getInstance().getReference().child(RoomName);
        root.keepSynced(true);
        message_edittexxt = (EditText) view.findViewById(R.id.messageInput);
        initializeMedia(view);
        initializeMessage(view);
        send_text = (ImageButton) view.findViewById(R.id.send);
        send_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!message_edittexxt.getText().equals("")) {
                    int hours = new Time(System.currentTimeMillis()).getHours();
                    int min = new Time(System.currentTimeMillis()).getMinutes();
                    currenttime = String.valueOf(hours) + ":" + String.valueOf(min);
                    send_text.setVisibility(View.GONE);
                    Map<String, Object> map = new HashMap<>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map);
                    final DatabaseReference massage_root = root.child(temp_key);
                    final Map<String, Object> map2 = new HashMap<>();
                    if (mediaUriList.isEmpty()) {
                        map2.put("name", UserName);
                        map2.put("msg", message_edittexxt.getText().toString());
                        map2.put("Time", currenttime);
                        massage_root.updateChildren(map2);
                        send_text.setVisibility(View.VISIBLE);
                        message_edittexxt.setText(null);

                    } else if (!mediaUriList.isEmpty()) {
                        for (String mediaUri : mediaUriList) {
                            String mediaId = massage_root.child("media").push().getKey();
                            mediaIdList.add(mediaId);
                            final StorageReference filePath = FirebaseStorage.getInstance().getReference().child(RoomName).child(temp_key).child(mediaId);
                            UploadTask uploadTask = filePath.putFile(Uri.parse(mediaUri));
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            map2.put("/media/" + mediaIdList.get(totalMediaUploaded) + "/", uri.toString());
                                            map2.put("name", UserName);
                                            map2.put("msg", message_edittexxt.getText().toString());
                                            map2.put("Time", currenttime);
                                            totalMediaUploaded++;
                                            if (totalMediaUploaded == mediaUriList.size())
                                                updateDatabaseWithNewMessage(massage_root, map2);

                                        }
                                    });
                                }
                            });


                        }
                    } else {
                        updateDatabaseWithNewMessage(massage_root, map2);

                    }


                }
            }
        });
        send_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        back = (ImageButton) view.findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new RoomsFragment(UserName)).commit();

            }
        });
        Add_chat();
        return view;
    }

    int PICK_IMAGE_INTENT = 1;

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture(s)"), PICK_IMAGE_INTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_INTENT) {
                if (data.getClipData() == null) {
                    mediaUriList.add(data.getData().toString());
                } else {
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        mediaUriList.add(data.getClipData().getItemAt(i).getUri().toString());
                    }
                }

                mMediaAdapter.notifyDataSetChanged();
            }
        }
    }

    int totalMediaUploaded = 0;

    private void updateDatabaseWithNewMessage(DatabaseReference newMessageDb, Map newMessageMap) {
        newMessageDb.updateChildren(newMessageMap);
        message_edittexxt.setText(null);
        mediaUriList.clear();
        mediaIdList.clear();
        totalMediaUploaded = 0;
        send_text.setVisibility(View.VISIBLE);
        mMediaAdapter.notifyDataSetChanged();

    }

    private String chat_msg, chat_user_name, Time;

    private void Add_chat() {

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    ArrayList<String> mediaUrlList = new ArrayList<>();
                    chat_msg = dataSnapshot.child("msg").getValue().toString();
                    chat_user_name = dataSnapshot.child("name").getValue().toString();
                    Time = dataSnapshot.child("Time").getValue().toString();

                    if (dataSnapshot.child("media").getChildrenCount() > 0) {
                        for (DataSnapshot mediaSnapshot : dataSnapshot.child("media").getChildren()) {
                            mediaUrlList.add(mediaSnapshot.getValue().toString());
                        }
                    }
                    if (mediaUrlList.isEmpty()) {

                        MessageObject mMessage = new MessageObject(dataSnapshot.getKey(), chat_user_name, chat_msg, mediaUrlList, "false", Time);
                        messageList.add(mMessage);
                        mChatLayoutManager.scrollToPosition(messageList.size() - 1);
                        mChatAdapter.notifyDataSetChanged();
                        System.out.println("hereeeeeeeee");

                    } else {

                        MessageObject mMessage = new MessageObject(dataSnapshot.getKey(), chat_user_name, chat_msg, mediaUrlList, "true", Time);
                        messageList.add(mMessage);
                        mChatLayoutManager.scrollToPosition(messageList.size() - 1);
                        mChatAdapter.notifyDataSetChanged();

                    }


                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void initializeMedia(View view) {
        mediaUriList = new ArrayList<>();
        mMedia = view.findViewById(R.id.mediaList);
        mMedia.setNestedScrollingEnabled(false);
        mMedia.setHasFixedSize(false);
        mMediaLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);
        mMedia.setLayoutManager(mMediaLayoutManager);
        mMediaAdapter = new MediaAdapter(getContext(), mediaUriList);
        mMedia.setAdapter(mMediaAdapter);
    }

    private void initializeMessage(View view) {
        messageList = new ArrayList<>();
        mChat = view.findViewById(R.id.messageList);
        mChat.setNestedScrollingEnabled(false);
        mChat.setHasFixedSize(false);
        mChatLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        mChat.setLayoutManager(mChatLayoutManager);
        mChatAdapter = new MessageAdapter(messageList, UserName);
        mChat.setAdapter(mChatAdapter);
    }
}
