package com.example.reapdemo;

public class User {
    private String Name;
    private String Email;
    private String Uid;

    public User() {
    }

    public User(String name, String email, String uid) {
        Name = name;
        Email = email;
        Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
