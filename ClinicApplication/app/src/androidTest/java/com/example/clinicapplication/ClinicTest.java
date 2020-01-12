package com.example.clinicapplication;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClinicTest {

    //Test Clinic
    Clinic testClinic = new Clinic("name","address","phoneNum","workingHours","paymentType");
    @Test
    public void clinicObj_isCorrect() {
        assertEquals("name", testClinic.getName());
        assertEquals("address", testClinic.getAddress());
        assertEquals("phoneNum", testClinic.getPhoneNum());
        assertEquals("workingHours", testClinic.getWorkingHours());
        assertEquals(0, testClinic.getNumRatings());
    }

    ArrayList<Service> testServices = new ArrayList<Service>();
    @Test
    public void serviceToString(){
        Clinic c = new Clinic();
        testServices.add(new Service("name01","role01","clinic01"));
        testServices.add(new Service("name02","role02","clinic02"));
        c.setServices(testServices);
        assertEquals("name01 role01 clinic01 name02 role02 clinic02 ", c.serviceToString());
    }

    @Test
    public void decodeService(){
        Clinic c = new Clinic();
        testServices.add(new Service("name01","role01","clinic01"));
        testServices.add(new Service("name02","role02","clinic02"));
        c.setServices(testServices);
        assertEquals(testServices.get(0).getName(), Clinic.serviceDeCode("name01 role01 clinic01 name02 role02 clinic02 ").get(0).getName());
        assertEquals(testServices.get(1).getName(), Clinic.serviceDeCode("name01 role01 clinic01 name02 role02 clinic02 ").get(1).getName());
        assertEquals(testServices.get(0).getRole(), Clinic.serviceDeCode("name01 role01 clinic01 name02 role02 clinic02 ").get(0).getRole());
        assertEquals(testServices.get(1).getRole(), Clinic.serviceDeCode("name01 role01 clinic01 name02 role02 clinic02 ").get(1).getRole());
        assertEquals(testServices.get(0).getClinic(), Clinic.serviceDeCode("name01 role01 clinic01 name02 role02 clinic02 ").get(0).getClinic());
        assertEquals(testServices.get(1).getClinic(), Clinic.serviceDeCode("name01 role01 clinic01 name02 role02 clinic02 ").get(1).getClinic());
    }
}