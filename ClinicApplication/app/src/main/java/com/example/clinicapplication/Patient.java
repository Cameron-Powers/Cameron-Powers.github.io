package com.example.clinicapplication;

public class Patient extends User {
    String type;

    public Patient(String fname, String lname, String email, String phoneNum, String username, String password) {
        super(fname, lname, email, phoneNum, username, password);
        this.type = "Patient";
    }

    public static Patient toPatient(User u) {
        return new Patient(u.fname, u.lname, u.email, u.phoneNum, u.username, u.password);
    }

    public String getType() {
        return type;
    }

    public void searchClinic(Service s) {

    }

    public void searchClinic(String address) {

    }

    public void searchClinic(String[] workingHours) {

    }

    public void checkInAndBook() { // P: ? -two methods?

    }

    public void rateClinic(Clinic clinic, Integer rate) {  // P: Clinic, Int
        clinic.updateRating(rate);
    }
}