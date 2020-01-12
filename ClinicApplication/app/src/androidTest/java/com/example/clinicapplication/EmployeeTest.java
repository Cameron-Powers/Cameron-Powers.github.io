package com.example.clinicapplication;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeTest {

    //Test Employee
    Employee testEmployee = new Employee("fname","lname","email","phoneNum","username","password");
    @Test
    public void employeeObj_isCorrect() {
        assertEquals("fname", testEmployee.getFname());
        assertEquals("lname", testEmployee.getLname());
        assertEquals("email", testEmployee.getEmail());
        assertEquals("phoneNum", testEmployee.getPhoneNum());
        assertEquals("username", testEmployee.getUsername());
        assertEquals("password", testEmployee.getPassword());
        assertEquals("Employee", testEmployee.getType());
    }

    @Test
    public void workingHoursDecode_isCorrect(){
        Employee e = new Employee();
        e.workingHours.add("5 11");
        e.workingHours.add("4 21");
        e.workingHours.add("8 23");
        e.workingHours.add("12 53");
        String x = e.workingHoursToString();
        assertEquals("5 11 4 21 8 23 12 53 ",x);
    }

    ArrayList<Service> testServices = new ArrayList<Service>();
    @Test
    public void serviceToString_isCorrect(){
        Employee e = new Employee();
        testServices.add(new Service("name01","role01","clinic01"));
        testServices.add(new Service("name02","role02","clinic02"));
        e.setServices(testServices);
        assertEquals("name01 role01 clinic01 name02 role02 clinic02 ", e.serviceToString());
    }
}