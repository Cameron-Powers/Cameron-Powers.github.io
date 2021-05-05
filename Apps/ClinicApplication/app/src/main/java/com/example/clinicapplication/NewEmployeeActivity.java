package com.example.clinicapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewEmployeeActivity extends AppCompatActivity {
    private EditText fName;
    private EditText lName;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText phoneNum;
    private Button create;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        mDatabaseHelper = new DatabaseHelper(this);

        fName = (EditText) findViewById(R.id.etEmployeeFirstName);
        lName = (EditText) findViewById(R.id.etEmployeeLastName);
        username = (EditText) findViewById(R.id.etEmployeeUserName);
        password = (EditText) findViewById(R.id.etEmployeePassword);
        email = (EditText) findViewById(R.id.etEmployeeEmail);
        phoneNum = (EditText) findViewById(R.id.etEmployeePhoneNumber);
        create = (Button) findViewById(R.id.btnCreate2);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (mDatabaseHelper.canCreate(username.getText().toString())) {
                        Employee e = new Employee(fName.getText().toString(),
                                lName.getText().toString(),
                                email.getText().toString(),
                                phoneNum.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString());
                        e.type = "Employee";
                        mDatabaseHelper.addEmployee(e);
                        mDatabaseHelper.user = e;
                        mDatabaseHelper.getUser().setType("Employee");
                        startActivity(new Intent(NewEmployeeActivity.this, EmployeeActivity.class));
                        Toast.makeText(getApplicationContext(), "Employee added", Toast.LENGTH_LONG).show();
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