package com.example.clinicapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {
    //Testing Service
    @Test
    public void serviceObj_isCorrect(){
        Service testService = new Service("name","role","clinic");
        assertEquals("name", testService.getName());
        assertEquals("role", testService.getRole());
        assertEquals("clinic", testService.getClinic());
    }
}