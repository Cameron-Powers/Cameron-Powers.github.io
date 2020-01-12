package com.example.clinicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientActivity extends AppCompatActivity {

    private TextView patientTitle;
    private Button MainMenu;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        mDatabaseHelper = new DatabaseHelper(this);

        patientTitle = (TextView) findViewById(R.id.patientTitle);
        MainMenu = (Button) findViewById(R.id.MainMenu2);

        patientTitle.setText("Welcome " + mDatabaseHelper.getUser().fname + "! You are logged in as " + mDatabaseHelper.getUser().type + "!");

        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, MainActivity.class));
            }
        });
    }


}
