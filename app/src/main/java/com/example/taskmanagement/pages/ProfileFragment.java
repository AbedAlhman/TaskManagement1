package com.example.taskmanagement.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taskmanagement.FireeBase.FirebaseServices;
import com.example.taskmanagement.R;
import com.example.taskmanagement.Utilites.Utilss;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText FName, LName;

    private Button Update;

    ImageView Profile;

    private FirebaseServices fbs;

    private Utilss utils;

    private String imageS;




    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onStart(){
        super.onStart();
        init();

    }
    private void init(){
        fbs=FirebaseServices.getInstance();
        FName=getView().findViewById(R.id.etFnameP);
        LName=getView().findViewById(R.id.etLNameP);
        Profile=getView().findViewById(R.id.ivPimage);
        Update=getView().findViewById(R.id.btnUpdate);
        utils=Utilss.getInstance();
        if(imageS==null){

        }




        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname=FName.getText().toString();
                String lastname=LName.getText().toString();

                if (firstname.trim().isEmpty() || lastname.trim().isEmpty())
                    Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                return;

            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}