package com.example.clinicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {

    private TextView outPut, outPutWork;
    private Button btnMenu, btnEditClinic, btnChooseService, btnChooseClinic, btnEditEmployee, btnEditWorkingHours;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        mDatabaseHelper = new DatabaseHelper(this);

        mDatabaseHelper.e = mDatabaseHelper.getEmployee(mDatabaseHelper.getUser());
        if(mDatabaseHelper.e.getServices().size() > 0){
            mDatabaseHelper.setClinic(new Clinic());
            mDatabaseHelper.getClinic().setName(mDatabaseHelper.e.getServices().get(0).getClinic());
        }
        Clinic c = mDatabaseHelper.getClinicToEdit();
        mDatabaseHelper.setClinic(c);

        outPut = (TextView) findViewById(R.id.tvEmployeeTitle);
        outPutWork = (TextView) findViewById(R.id.tvEmployeeWork);
        btnMenu = (Button) findViewById(R.id.mainMenuEmployee);
        btnChooseClinic = (Button) findViewById(R.id.btnChooseClinic);
        btnEditClinic = (Button) findViewById(R.id.btnEditClinic);
        btnChooseService= (Button) findViewById(R.id.btnChooseService);
        btnEditEmployee= (Button) findViewById(R.id.btnEditEmployee);
        btnEditWorkingHours= (Button) findViewById(R.id.btnEditWorkingHours);

        outPut.setText("Welcome " + mDatabaseHelper.getUser().fname + "! You are logged in as " + mDatabaseHelper.getUser().type + "!");
        if(c.getName().equals("")) {
            outPutWork.setText("Currently Working at: NONE");
            btnEditClinic.setEnabled(false);
            btnChooseService.setEnabled(false);
        }else{
            outPutWork.setText("Currently Working at: " + c.getName());
            btnEditClinic.setEnabled(true);
            btnChooseService.setEnabled(true);
        }

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeActivity.this, MainActivity.class));
            }
        });

        btnChooseClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Edit Clinic associated with Employee
                mDatabaseHelper.info = "cli";
                mDatabaseHelper.e.setServices(new ArrayList<Service>());
                startActivity(new Intent(EmployeeActivity.this, ViewClinicActivity.class));
            }
        });

        btnEditClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Edit Clinic associated with Employee
                mDatabaseHelper.info = "cli";
                startActivity(new Intent(EmployeeActivity.this, EditClinicActivity.class));
            }
        });

        btnChooseService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // view services
                startActivity(new Intent(EmployeeActivity.this, SelectServiceActivity.class));
            }
        });

        btnEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // edit obj
                mDatabaseHelper.info = "emp";
                startActivity(new Intent(EmployeeActivity.this, EditObjectActivity.class));
            }
        });

        btnEditWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // edit work hours, list of start [x] finish [y] -> "x-y" , "x2-y2" , ...
                mDatabaseHelper.info = "work";
                startActivity(new Intent(EmployeeActivity.this, ViewServiceActivity.class));
            }
        });

    }
}
