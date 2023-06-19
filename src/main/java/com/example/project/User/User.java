package com.example.project;
public class User {
    private String username;
    private String email;
    private String password;
    int id;

    public User(int userId, String username, String password, String email) {
        this.id = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {return email;}


}