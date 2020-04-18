package com.example.medicalapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.API.DiagnosesModel.DiagnosesResponse;
import com.example.medicalapp.API.DiagnosesModel.Issue;
import com.example.medicalapp.Base.BaseFragment;
import com.example.medicalapp.Diagnoses.DiagnosesAdapter;
import com.example.medicalapp.database.AppDataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileUserFragment extends BaseFragment {

    private Uri imageUri;
    private CircleImageView profileimg;
    private DatabaseReference RootRef;
    private StorageReference userprofileimgRef;
    private static final String TAG = "ProfileFragment";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView recyclerView;
    String email, name, gender, password, birhyear, image;
    DiagnosesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ImageView exit;

    public ProfileUserFragment(String email, String name, String gender, String birhyear, String image) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birhyear = birhyear;
        this.image = image;


    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile_user, container, false);

        //Getting Data

        //Getting IDs
        TextView emailtext = (TextView) view.findViewById(R.id.txt_email);
        TextView nametext = (TextView) view.findViewById(R.id.txt_name);
        TextView gendertext = (TextView) view.findViewById(R.id.txt_gender);
        TextView birthyeartext = (TextView) view.findViewById(R.id.txt_birthyear);
        profileimg = (CircleImageView) view.findViewById(R.id.profile_image);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_profile);
        exit = (ImageView) view.findViewById(R.id.exitapp);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(view.getContext(),LogInActivity.class));

                getActivity().finish();
            }
        });

        //Setting
        emailtext.setText(email);
        nametext.setText(name);
        gendertext.setText(gender);
        birthyeartext.setText(birhyear);
        if (!image.equals("DEFAULT")) {
            //Glide.with(this).load(image).into(profile_img);
            Picasso.with(getActivity()).load(image).into(profileimg);
        }
        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        RootRef = FirebaseDatabase.getInstance().getReference();

        adapter = new DiagnosesAdapter(converter());
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        userprofileimgRef = FirebaseStorage.getInstance().getReference().child("image profile");
        if(gender.equals("Male")){
            profileimg.setImageResource(R.drawable.male);
        }
        else if(gender.equals("Female")){
            profileimg.setImageResource(R.drawable.female);
        }


        return view;
    }


    int PICK_IMAGE_INTENT = 1;
    ArrayList<String> imgUriList = new ArrayList<>();

    void openGallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityForResult(Intent.createChooser(gallery, "sellect picture"), PICK_IMAGE_INTENT);
        Toast.makeText(getActivity(), "Selected Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_INTENT) {

            if (resultCode == RESULT_OK) {
                imageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), imageUri);
                    profileimg.setImageBitmap(bitmap);
                    upload_img();

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    private void upload_img() {

        if (imageUri != null) {
            final StorageReference filepath = userprofileimgRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid() + ".jpg");
            UploadTask uploadTask = filepath.putFile(imageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            RootRef.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("image")
                                    .setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Image saved successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String message = task.getException().toString();
                                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            });

        }

    }

    public List<DiagnosesResponse> converter() {
        List<Issue> issues = AppDataBase.getInstance(view.getContext()).diagnosesDao().ISSUES();
        List<DiagnosesResponse> diagnosesResponse = new ArrayList<>();
        for (Issue i : issues) {
            DiagnosesResponse diagnosesResponse1 = new DiagnosesResponse();
            diagnosesResponse1.setIssue(i);
            diagnosesResponse1.setSpecialisation(null);
            diagnosesResponse.add(diagnosesResponse1);

        }
        return diagnosesResponse;
    }
}