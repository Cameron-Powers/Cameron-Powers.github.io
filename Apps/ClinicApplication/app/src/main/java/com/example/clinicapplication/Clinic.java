package com.example.clinicapplication;

import java.util.ArrayList;
import java.util.Iterator;

public class Clinic {

    String address, phoneNum, name, paymentType, workingHours;
    double rate;
    int numRatings;
    ArrayList<Service> services;

    public Clinic(){
        this.name = "";
        this.address = "";
        this.phoneNum = "";
        this.workingHours = "";
        rate = 0;
        numRatings = 0;
        services = new ArrayList<>();
        paymentType = "";
    }

    public Clinic(String name, String address, String phoneNum, String workingHours,String paymentType) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.workingHours = workingHours;
        rate = 0;
        numRatings = 0;
        services = new ArrayList<>();
        this.paymentType = paymentType;
    }

    public double updateRating(double rate) { // P: double
        double i = rate * numRatings;
        numRatings++;
        return Math.round(((i + rate) / numRatings) * 10) / 10.0;
    }

    public void addService(Service s) {
        services.add(s);
    }

    public void removeService(Service s) {
        services.remove(s);
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

    public static ArrayList<Service> serviceDeCode(String s){
        if(s == null || s.equals("")) return new ArrayList<>();
        Service service = new Service();
        ArrayList<Service> rep = new ArrayList<>();
        String[] split = s.split("\\s+");
        if(split.length%3 != 0){return rep;}
        for(int i = 0; i<split.length; i+=3){
            service = new Service();
            service.setName(split[i]);
            service.setRole(split[i+1]);
            service.setClinic(split[i+2]);
            rep.add(service);
        }
        return rep;
    }

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public int getNumRatings() {
        return numRatings;
    }
    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getRate() {
        return rate;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
    public ArrayList<Service> getServices() {
        return services;
    }

    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
