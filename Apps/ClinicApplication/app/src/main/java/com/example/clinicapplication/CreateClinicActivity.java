package com.example.clinicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateClinicActivity extends AppCompatActivity {

    private TextView tvCreateClinicTitle;
    private EditText name;
    private EditText address;
    private EditText paymentType, workingHours;
    private EditText phoneNum;
    private Button create;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_clinic);

        tvCreateClinicTitle = (TextView) findViewById(R.id.tvCreateClinicTitle);
        name = (EditText) findViewById(R.id.etClinicName);
        address = (EditText) findViewById(R.id.etClinicAddress);
        phoneNum = (EditText) findViewById(R.id.etClinicPhone);
        paymentType = (EditText) findViewById(R.id.etClinicPaymentType);
        workingHours = (EditText)findViewById(R.id.etClinicWorkingHours);
        create = (Button) findViewById(R.id.btnClinicCreate);

        mDatabaseHelper = new DatabaseHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String inputName = name.getText().toString();
                    if (!mDatabaseHelper.isClinic(inputName)) {
                        //Toast.makeText(getApplicationContext(), "Clinic " + name.getText().toString() + address.getText().toString()+ phoneNum.getText().toString() +"", Toast.LENGTH_LONG).show();
                        Clinic c = new Clinic(inputName, address.getText().toString(), phoneNum.getText().toString(), workingHours.getText().toString(), paymentType.getText().toString());
                        mDatabaseHelper.addClinic(c);
                        startActivity(new Intent(CreateClinicActivity.this, AdminActivity.class));
                        Toast.makeText(getApplicationContext(), "Clinic added", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Clinic Name already exists!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Empty Input Fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate() {
        return (name.getText().length() != 0 &&
                address.getText().length() != 0 &&
                workingHours.getText().length() != 0 &&
                phoneNum.getText().length() != 0 &&
                paymentType.getText().length() != 0);}

}
