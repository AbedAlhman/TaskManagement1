package com.example.taskmanagement.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taskmanagement.FireeBase.FirebaseServices;
import com.example.taskmanagement.pages.Note;
import com.example.taskmanagement.pages.NoteItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.installations.Utils;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNoteFragment extends Fragment {

    private EditText titleInput, descriptionInput;
    private Button saveBtn;

    Spinner spnImp;
    ImageView img;

    private String imageStr;
    private FirebaseServices fbs;
    private Utils utils;


    private ArrayAdapter<CharSequence> colorAdapter;


    String[] Importance = {"Very Important", "Important", "Not Important"};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNoteFragment newInstance(String param1, String param2) {
        AddNoteFragment fragment = new AddNoteFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        // ---->    פרטי הוספת רכב    <----
        //editText
        fbs = FirebaseServices.getInstance();
        utils = Utils.getInstance();
        titleInput = getView().findViewById(R.id.titleinput);
        descriptionInput = getView().findViewById(R.id.descriptioninput);
        saveBtn = getView().findViewById(R.id.savebtn);
        img = getView().findViewById(R.id.ivCarAddCarFragment);

        //spinner for the color of car
        spnImp = getView().findViewById(R.id.spnImp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_file, Importance);
        adapter.setDropDownViewResource(R.layout.item_file);
        spnImp.setAdapter(adapter);

        spnImp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                //   Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        saveBtn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        // adding to firestore  'car' collection

        addToFirestore();
    }
    });
//////////////////////try spinner////////////////////////////////////////////////////////

    colorAdapter=ArrayAdapter.createFromResource(

    getActivity(),R.array.array_importance,R.layout.spinner_layout);
    ////////////////////////////////try new spinner ///////////////////////////
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnImp.setAdapter(colorAdapter);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        ((MainActivity)getActivity()).pushFragment(new AddNoteFragment());
    }
}

private void addToFirestore() {

    String title,description;
    String importance;
//get data from screen

    title= titleInput.getText().toString();
    description = descriptionInput.getText().toString();
    importance = spnImp.getSelectedItem().toString();

    //מספר טלפון לא חייב לבדוק
//        if(phone==null){
//            phone="-";
//            return;
//        }
    if (title.trim().isEmpty() || description.trim().isEmpty() || importance.trim().isEmpty()
            ) {
        Toast.makeText(getActivity(), "sorry some data missing incorrect !", Toast.LENGTH_SHORT).show();
        return;
    }

    Note note;
    NoteItem note2;
    if (fbs.getSelectedImageURL() == null) {
        note = new Note(title, description,importance, "");
        note2 = new NoteItem(UUID.randomUUID().toString(), title, description, importance, "");
    } else {
        note = new Note(title,description,importance, fbs.getSelectedImageURL().toString());
        note2 = new NoteItem(UUID.randomUUID().toString(), title,description,importance, fbs.getSelectedImageURL().toString());

    }

    fbs.getFire().collection("cars").add(car)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getActivity(), "ADD Car is Succesed ", Toast.LENGTH_SHORT).show();
                    Log.e("addToFirestore() - add to collection: ", "Successful!");
                    gotoCarList();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("addToFirestore() - add to collection: ", e.getMessage());
                }
            });

    try {
        fbs.getFire().collection("cars2").add(car2)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(getActivity(), "ADD Car is Succesed ", Toast.LENGTH_SHORT).show();
                        Log.e("addToFirestore() - add to collection: ", "Successful!");
                        //gotoCarList();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("addToFirestore() - add to collection: ", e.getMessage());
                    }
                });
    } catch (Exception ex) {
        Log.e("AddCarFragment: addToFirestore()", ex.getMessage());
    }
}


private void openGallery() {
    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
}

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
        Uri selectedImageUri = data.getData();
        img.setImageURI(selectedImageUri);
        utils.uploadImage(getActivity(), selectedImageUri);
    }
}

public void gotoCarList() {

    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.frameLayout, new CarListMapFragment());
    ft.commit();
}

public void toBigImg(View view) {
}

    /*
    public void uploadImage(Uri selectedImageUri) {
        if (selectedImageUri != null) {
            imageStr = "images/" + UUID.randomUUID() + ".jpg"; //+ selectedImageUri.getLastPathSegment();
            StorageReference imageRef = fbs.getStorage().getReference().child("images/" + selectedImageUri.getLastPathSegment());

            UploadTask uploadTask = imageRef.putFile(selectedImageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //selectedImageUri = uri;
                            fbs.setSelectedImageURL(uri);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    Toast.makeText(getActivity(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Please choose an image first", Toast.LENGTH_SHORT).show();
        }
    } */
}

        }