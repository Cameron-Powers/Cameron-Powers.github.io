package com.example.clinicapplication;

public class Service {

    String name;
    String role;
    String clinic;

    public Service(){
        this.name = "";
        this.role = "";
        this.clinic = "";
    }

    public Service(String name, String role, String clinic) {
        this.name = name;
        this.role = role;
        this.clinic = clinic;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getClinic() {
        return clinic;
    }
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

}
