package com.example.taskmanagement.FireeBase;

import android.os.Parcel;

import java.util.ArrayList;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String photo;
    private ArrayList<String> Notes;

    public User(String username, String etUsername) {
    }

    public User(String email,String firstName, String lastName, String username, String photo) {
        this.email=email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.photo = photo;
        this.Notes = new ArrayList<>();
    }

    public User(Parcel in) {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }





    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArrayList<String> getFavorites() {
        return Notes;
    }

    public void setFavorites(ArrayList<String> favourits) {
        this.Notes = favourits;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", Photo='" + photo + '\'' +
                '}';
    }
}
