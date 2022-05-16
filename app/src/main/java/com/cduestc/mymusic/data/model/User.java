package com.cduestc.mymusic.data.model;

public class User {
    private double id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String prefers;
    private String birth;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public User(String username, String password, String gender, String email, String prefers, String birth) {
//        this.id = Math.random()*10000;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.prefers = prefers;
        this.birth = birth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefers() {
        return prefers;
    }

    public void setPrefers(String prefers) {
        this.prefers = prefers;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
