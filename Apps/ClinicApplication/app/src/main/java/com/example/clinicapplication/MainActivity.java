package com.example.clinicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private Button NewPatient;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);

        Username = (EditText) findViewById(R.id.etUsername);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        NewPatient = (Button) findViewById(R.id.btnNewPatient);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        NewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewPatientActivity.class);
                startActivity(i);
            }
        });
    }

    private void validate() { // P : String, String
        Intent i;
        mDatabaseHelper.setClinic(new Clinic());
        if (checkAdmin()) {
            mDatabaseHelper.user = new Admin();
            mDatabaseHelper.getUser().type = "Admin";
            i = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(i);
        } else {
            if (mDatabaseHelper.isUser(Username.getText().toString(), Password.getText().toString())) {
                mDatabaseHelper.setUser(Username.getText().toString(), Password.getText().toString());
                String type = mDatabaseHelper.getUser().type;
                if(type != null){
                    if (type.equals("Patient")) {
                        i = new Intent(MainActivity.this, PatientActivity.class);
                        startActivity(i);
                    } else if (type.equals("Employee")) {
                        i = new Intent(MainActivity.this, EmployeeActivity.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect username or password!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean checkAdmin() {
        Admin admin = new Admin();
        return admin.getUsername().equals(Username.getText().toString()) && admin.getPassword().equals(Password.getText().toString());
    }
}
