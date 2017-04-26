package com.database.view;

/**
 * Created by Tory on 4/25/17.
 */
public class User {
    private final String userName;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String hometown;
    private final String phone;
    private final String pwd;

    public User(String userName, String email, String firstName, String lastName, String hometown, String phone, String pwd) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hometown = hometown;
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHometown() {
        return hometown;
    }

    public String getPhone() {
        return phone;
    }

    public String getPwd() {
        return pwd;
    }
}
