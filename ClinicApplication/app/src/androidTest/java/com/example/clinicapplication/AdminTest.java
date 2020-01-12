package com.example.clinicapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {

    Admin a = new Admin();
    @Test
    public void employeeObj_isCorrect() {
        assertEquals("Admin", a.getFname());
        assertEquals("", a.getLname());
        assertEquals("", a.getEmail());
        assertEquals("", a.getPhoneNum());
        assertEquals("admin", a.getUsername());
        assertEquals("5T5ptQ", a.getPassword());
        assertEquals("Admin", a.getType());
    }

}