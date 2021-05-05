package com.example.clinicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private Button btnCreateEmployee, mainMenu, btnCreateClinic, btnViewClinic, btnViewUser;
    private TextView tvAdminTitle;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tvAdminTitle = (TextView) findViewById(R.id.tvAdminTitle);
        btnCreateEmployee = (Button) findViewById(R.id.btnCreateEmployee);
        btnCreateClinic = (Button) findViewById(R.id.btnCreateClinic);
        btnViewClinic = (Button) findViewById(R.id.btnAddService);
        btnViewUser = (Button) findViewById(R.id.btnViewUser);
        mainMenu = (Button) findViewById(R.id.mainMenu);

        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseHelper.user = new Admin();
        mDatabaseHelper.info = "";
        mDatabaseHelper.getUser().type = "Admin";
        tvAdminTitle.setText("Welcome " + mDatabaseHelper.getUser().fname + "! You are logged in as " + mDatabaseHelper.getUser().type + "!");

        btnCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, NewEmployeeActivity.class));
            }
        });

        btnCreateClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, CreateClinicActivity.class));
            }
        });

        btnViewClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ViewClinicActivity.class));
            }
        });

        btnViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ViewUserActivity.class));
            }
        });


        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });


    }


}
