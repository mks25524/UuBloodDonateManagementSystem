package com.example.mks.uublooddonatemanagementsystem.model;

/**
 * Created by mks on 7/24/2017.
 */

public class User {
    private int id;
    private String name;
    private  String email;
    private String password;
    private String gender;
    private String bloodGroup;
    private String city;
    private String contactno;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getCity() {
        return city;
    }

    public String getContactno() {
        return contactno;
    }

    public User(int id, String name, String email, String password, String gender, String bloodGroup, String city, String contactno) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.contactno = contactno;
    }

    public User(int id, String name, String email, String gender, String bloodGroup, String city, String contactno) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.contactno = contactno;
    }

    public User(String name, String email, String password, String gender, String bloodGroup, String city, String contactno) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.contactno = contactno;
    }
}
