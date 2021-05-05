package com.example.clinicapplication;

public class User {

    String fname, lname, email, phoneNum, type;
    String username;
    String password; // NOT STRING **!!CHANGE!!**\

    public User() {
    }

    public User(String fname, String lname, String email, String phoneNum, String username, String password) { // ADD PASSWORD
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFnameAndType(String fname, String type) {
        this.fname = fname;
        this.type = type;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } // !!NOT STRING!!

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid(String username, String password) {
        // CHECKS DB IF USER IS VALID
        return true;
    }
}
