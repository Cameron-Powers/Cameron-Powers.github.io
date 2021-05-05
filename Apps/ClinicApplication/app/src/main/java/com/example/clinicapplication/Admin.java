package com.example.clinicapplication;

public class Admin extends Employee {
    String type;

    public Admin() {
        super("Admin", "", "", "", "admin", "5T5ptQ");
        this.type = "Admin";
    }

    public String getType() {
        return type;
    }

    public void createService(String name, String role, String clinic) { // P: needed info for new service
        Service service = new Service(name, role, clinic);
    }

    public void deleteUser(User user) { // P:  -DELETE FROM DB

    }

    public void deleteClinic(Clinic clinic) { // P: Clinic -DELETE FROM DB

    }

    public void deleteService(Clinic clinic, Service service) {

    }
}