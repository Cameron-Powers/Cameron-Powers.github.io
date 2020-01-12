package com.example.clinicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPatientActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText phoneNum;
    private Button create;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        fName = (EditText) findViewById(R.id.etPatientFirstName);
        lName = (EditText) findViewById(R.id.etPatientLastName);
        username = (EditText) findViewById(R.id.etPatientUserName);
        password = (EditText) findViewById(R.id.etPatientPassword);
        email = (EditText) findViewById(R.id.etPatientEmail);
        phoneNum = (EditText) findViewById(R.id.etPatientPhoneNumber);
        create = (Button) findViewById(R.id.btnCreate);

        mDatabaseHelper = new DatabaseHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (mDatabaseHelper.canCreate(username.getText().toString())) {
                        Patient p = new Patient(fName.getText().toString(),
                                lName.getText().toString(),
                                email.getText().toString(),
                                phoneNum.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString());
                        p.type = "Patient";
                        mDatabaseHelper.addPatient(p);
                        mDatabaseHelper.user = p;
                        mDatabaseHelper.getUser().type = "Patient";
                        startActivity(new Intent(NewPatientActivity.this, PatientActivity.class));
                        Toast.makeText(getApplicationContext(), "Patient added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Empty Input Fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate() {
        return (fName.getText().length() != 0 &&
                lName.getText().length() != 0 &&
                username.getText().length() != 0 &&
                password.getText().length() != 0 &&
                email.getText().length() != 0 &&
                phoneNum.getText().length() != 0);
    }
}