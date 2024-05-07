package com.example.taskmanagement.pages;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteItem implements Parcelable {
    private String title;
    private String description;
    private  String importance;//כוח סוס

    private String photo;

    public NoteItem(String string, String title, String description, String importance, String s) {
    }

    public NoteItem(String title,String description,String importance, String photo) {
        this.title = title;
        this.description = description;
        this.importance = importance;
        this.photo = photo;
    }

    protected NoteItem(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.importance = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.importance);
        dest.writeString(this.photo);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                "description='" + description + '\'' +
                ", importance='" + importance + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

}

