package com.example.mathprojectshaharlt;

import static android.app.Activity.RESULT_OK;

import android.app.Instrumentation;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ShowUsers extends Fragment {
    private MainViewMoudle mainViewMoudle;
    private Button picture;
    private ImageView image;
    Uri uri;
    Intent intent = new Intent(Intent.ACTION_SEND);

    ActivityResultLauncher<Intent> startCamera =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() ==  RESULT_OK){
                image.setImageURI(uri);
                //showPic.setImageURI(uri);
            }
        }
    });



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewMoudle = new ViewModelProvider(requireActivity()).get(MainViewMoudle.class);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_show_users, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        picture=view.findViewById(R.id.picture);
        image = view.findViewById(R.id.image);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION,"from Camera");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startCamera.launch(cameraIntent);

            }
        });


    }

}

