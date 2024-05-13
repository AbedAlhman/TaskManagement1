package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.taskmanagement.FireeBase.FirebaseServices;
import com.example.taskmanagement.pages.AddNoteFragment;
import com.example.taskmanagement.pages.LoginFragment;
import com.example.taskmanagement.pages.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private FirebaseServices fbs;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout fragmentContainer;
    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // gotoAddNoteFragment();
        gotoLoginFragment();
    }

        private void gotoLoginFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new LoginFragment());
        ft.commit();
    }

    public void pushFragment(Fragment fragment) {
        /*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit(); */
    }
    private void gotoAddNoteFragment() {
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new AddNoteFragment());
        ft.commit();
    }
}