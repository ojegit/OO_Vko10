package com.example.oo_vko10;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

public class User extends UserStorage implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String degreeProgram;
    private ArrayList<String> degree;

    private String imageUri; //private Uri imageUri;

    private static final long serialVersionUID = 123456789L;

    //public User() {}
    public User(String firstName, String lastName, String email,
                String degreeProgram, ArrayList<String> degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.degreeProgram = degreeProgram;
        this.degree = degree;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() { return email; }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public ArrayList<String> getDegree() { return degree; }

    public String getImageUri() { return imageUri; }

    public void setImageUri(String uri) { this.imageUri = uri; }



}
