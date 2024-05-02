package com.example.mathprojectshaharlt;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mathprojectshaharlt.UserAdapter.OnItemClickListener;

import java.util.ArrayList;

public class ShowUsersFragment extends Fragment {
    private MainViewMoudle mainViewMoudle;
    private Button picture;
    private ImageView image;
    private Button addUser;
    private User UserSelected;
    Uri uri;
    private RecyclerView rcShowUsers1;
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
        setHasOptionsMenu(true);
        View view =inflater.inflate(R.layout.fragment_show_users, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        picture=view.findViewById(R.id.picture);
        image = view.findViewById(R.id.image);
        addUser = view.findViewById(R.id.addUser);
        rcShowUsers1 = view.findViewById(R.id.rcShowUsers1);
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
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewMoudle.setUserImg(uri);
                mainViewMoudle.addUserDatabase(requireActivity());
            }
        });

        mainViewMoudle.users.observe(requireActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
            ShowList(users);

            }
        });


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.showusers,menu);
        MenuItem itemDelete = menu.findItem(R.id.delete);
        MenuItem itemEdit = menu.findItem(R.id.edit);
        super.onCreateOptionsMenu(menu, inflater);

    }



    public void ShowList(ArrayList<User>users){
        UserAdapter uAdepter = new UserAdapter(users, new UserAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(User item) {

            }

        });
        rcShowUsers1.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rcShowUsers1.setAdapter(uAdepter);
        rcShowUsers1.setHasFixedSize(true);

    }





}

