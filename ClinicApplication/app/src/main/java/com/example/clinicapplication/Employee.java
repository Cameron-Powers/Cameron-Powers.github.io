package com.example.clinicapplication;

import java.util.ArrayList;
import java.util.Iterator;

public class Employee extends User {

    String type;
    ArrayList<Service> services;
    ArrayList<String> workingHours;

    public Employee(){
        fname = "";
        lname = "";
        email = "";
        phoneNum = "";
        username = "";
        password = "";
        this.type = "Employee";
        services = new ArrayList<Service>();
        workingHours = new ArrayList<String>();
    }

    public Employee(String fname, String lname, String email, String phoneNum, String username, String password) {
        super(fname, lname, email, phoneNum, username, password);
        this.type = "Employee";
        services = new ArrayList<Service>();
        workingHours = new ArrayList<String>();
    }

    public static Employee toEmployee(User u) {
        return new Employee(u.fname, u.lname, u.email, u.phoneNum, u.username, u.password);
    }

    public String getType() {
        return type;
    }

    public String serviceToString(){
        String ret = "";
        Iterator<Service> it = services.iterator();
        while (it.hasNext()) {
            Service s = it.next();
            ret += s.name + " " + s.role + " " +  s.clinic + " ";
        }
        return ret;
    }

    public static ArrayList<String> workingHoursDecode(String s){
        if(s == null || s.equals("")) return new ArrayList<>();
        ArrayList<String> rep = new ArrayList<String>();
        String[] split = s.split("\\s+");
        if(split.length%2 == 0) {
            for (int i = 0; i < split.length; i += 2) {
                rep.add(split[i] + " " + split[i + 1]);
            }
        }
        return rep;
    }

    public String workingHoursToString(){
        String ret = "";
        Iterator<String> it = workingHours.iterator();
        while (it.hasNext()) {
            String s = it.next();
            ret += s + " ";
        }
        return ret;
    }

    public void createAccount() { // P: needed info

    }


    public void rateService() { // P: Service & rating
    }

    public void selectService() { // P: ?

    }

    public void enterHours() { // P: ?

    }

    public ArrayList<Service> getServices() {
        return services;
    }
    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<String> getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(ArrayList<String> workingHours) {
        this.workingHours = workingHours;
    }
}
