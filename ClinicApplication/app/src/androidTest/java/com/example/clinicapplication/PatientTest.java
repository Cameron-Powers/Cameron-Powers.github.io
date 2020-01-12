package com.example.clinicapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class PatientTest {

    //Test Patient
    Patient testPatient = new Patient("fname","lname","email","phoneNum","username","password");
    @Test
    public void patientObj_isCorrect() {
        assertEquals("fname", testPatient.getFname());
        assertEquals("lname", testPatient.getLname());
        assertEquals("email", testPatient.getEmail());
        assertEquals("phoneNum", testPatient.getPhoneNum());
        assertEquals("username", testPatient.getUsername());
        assertEquals("password", testPatient.getPassword());
        assertEquals("Patient", testPatient.getType());
    }
}