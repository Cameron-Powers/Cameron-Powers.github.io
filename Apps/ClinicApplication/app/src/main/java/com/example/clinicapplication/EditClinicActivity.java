package com.example.clinicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditClinicActivity extends AppCompatActivity {

    private ListView editlistclinic;
    DatabaseHelper mDatabaseHelper;
    private Button delete, done, services;

    private TextView title;
    private EditClinicActivity.ViewHolder mainViewholder;

    private ArrayList<EditClinicActivity.ViewHolder> allViews;

    private ArrayList<String> rows = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clinic);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        delete = (Button)findViewById(R.id.btnEditDeleteClinic);
        done = (Button)findViewById(R.id.btnEditDoneClinic);
        editlistclinic = (ListView) findViewById(R.id.EditListClinic);
        title = (TextView)findViewById(R.id.edit_clinic_title);
        mDatabaseHelper = new DatabaseHelper(this);
        services = (Button)findViewById(R.id.btnEditServices);
        allViews = new ArrayList<EditClinicActivity.ViewHolder>();

        Clinic clinic = mDatabaseHelper.getClinicToEdit();
        //Toast.makeText(getApplicationContext(), ""+clinic.serviceToString()+"", Toast.LENGTH_LONG).show();

        ArrayList<String> clinicInfo = new ArrayList<>();
        clinicInfo.add(clinic.getName());
        clinicInfo.add(clinic.getAddress());
        clinicInfo.add(clinic.getPhoneNum());
        clinicInfo.add(clinic.getRate()+"");
        clinicInfo.add(clinic.getPaymentType());
        clinicInfo.add(clinic.getWorkingHours());

        if(mDatabaseHelper.info.equals("cli")) {
            services.setEnabled(false);
            delete.setEnabled(false);
        }else{
            services.setEnabled(true);
            delete.setEnabled(true);
        }

        if (clinic == null) {
            if(mDatabaseHelper.info.equals("cli")) {
                startActivity(new Intent(EditClinicActivity.this, EmployeeActivity.class));
            }else{
                startActivity(new Intent(EditClinicActivity.this, ViewClinicActivity.class));
        }}
        rows.add("Name:");
        rows.add("Address:");
        rows.add("PhoneNum:");
        rows.add("Rate");
        rows.add("PaymentType");
        rows.add("WorkingHours");
        editlistclinic.setAdapter(new EditClinicActivity.MyListAdaper(this, R.layout.edit_list_item, rows, clinicInfo));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteClinic(mDatabaseHelper.getClinic());
                Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(EditClinicActivity.this,ViewClinicActivity.class));
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String inputName = allViews.get(0).input.getText().toString();
                    if (!mDatabaseHelper.isClinic(inputName) || inputName.equals(mDatabaseHelper.getClinic().name)) {
                        update();
                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG).show();
                        //mDatabaseHelper.getClinic().services.add(new Service("Name", "Role", "Clinic"));
                        //mDatabaseHelper.setService(new Service("Name", "Role", "Clinic"));
                        if(mDatabaseHelper.info.equals("cli")){
                            startActivity(new Intent(EditClinicActivity.this, EmployeeActivity.class));
                        }else {
                            startActivity(new Intent(EditClinicActivity.this, ViewClinicActivity.class));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Clinic Name already exists!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Empty Input Fields!", Toast.LENGTH_LONG).show();
                }
            }
            }
        );

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                startActivity(new Intent(EditClinicActivity.this, ViewServiceActivity.class));
            }
        });

    }

    private boolean validate() {
        return (allViews.get(0).input.getText().toString().length() != 0 &&
                allViews.get(1).input.getText().toString().length() != 0  &&
                allViews.get(2).input.getText().toString().length() != 0  &&
                allViews.get(3).input.getText().toString().length() != 0  &&
                allViews.get(4).input.getText().toString().length() != 0  &&
                allViews.get(5).input.getText().toString().length() != 0 );
    }

    public void update(){
        mDatabaseHelper.deleteClinic(mDatabaseHelper.getClinic());
        Clinic c = new Clinic();
        c.setName(allViews.get(0).input.getText().toString());
        c.setAddress(allViews.get(1).input.getText().toString());
        c.setPhoneNum(allViews.get(2).input.getText().toString());
        try {
            c.setRate((Double.parseDouble(allViews.get(3).input.getText().toString())));
        } catch (Exception i) {
            c.setRate(0.0);
        }
        c.setServices(mDatabaseHelper.getClinic().getServices());
        c.setPaymentType(allViews.get(4).input.getText().toString());
        c.setWorkingHours(allViews.get(5).input.getText().toString());
        mDatabaseHelper.addClinic(c);
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> titles;
        ArrayList<String> clinicInfo;
        private MyListAdaper(Context context, int resource, List<String> titles, ArrayList<String> clinicInfo) {
            super(context, resource, titles);
            this.titles = titles;
            layout = resource;
            this.clinicInfo = clinicInfo;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                EditClinicActivity.ViewHolder viewHolder = new EditClinicActivity.ViewHolder();
                 viewHolder.row = (TextView) convertView.findViewById(R.id.textViewEdit);
                viewHolder.input = (EditText) convertView.findViewById(R.id.Edit_item_etInput);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.row.setText(this.titles.get(position));
            mainViewholder.input.setText(this.clinicInfo.get(position));
            allViews.add(mainViewholder);
            return convertView;
        }
    }
    private class ViewHolder {
        TextView row;
        EditText input;
    }

}