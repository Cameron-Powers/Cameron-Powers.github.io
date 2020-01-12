package com.example.clinicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewClinicActivity extends AppCompatActivity {

    private ListView listView;
    private TextView title;
    private Button back;
    private ArrayAdapter adapter;
    DatabaseHelper mDatabaseHelper;
    private ArrayList<String> clinics;
    Clinic c = new Clinic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic);

        listView = (ListView) findViewById(R.id.listViewClinic);
        title = (TextView)findViewById(R.id.clinicViewTitle);
        back = (Button)findViewById(R.id.backViewClinic);
        mDatabaseHelper = new DatabaseHelper(this);
        if(mDatabaseHelper.getClinic() != null)
            c = mDatabaseHelper.getClinic();
        mDatabaseHelper.setClinic(new Clinic());

        clinics = mDatabaseHelper.getClinics();
        if (clinics.size() == 0) {
            title.setText("NO CLINICS!");
        } else {
            title.setText("All Clinics");
            listView.setAdapter(new ViewClinicActivity.MyListAdaper(this, R.layout.list_item, clinics));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatabaseHelper.info.equals("cli")){
                    mDatabaseHelper.setClinic(c);
                    startActivity(new Intent(ViewClinicActivity.this, EmployeeActivity.class));
                }else {
                    startActivity(new Intent(ViewClinicActivity.this, AdminActivity.class));
                }
            }
        });
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                if(mDatabaseHelper.info.equals("cli")){
                    viewHolder.button.setText("Choose");
                }
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatabaseHelper.setClinic(new Clinic());
                    mDatabaseHelper.getClinic().name = clinics.get(position);
                    if(mDatabaseHelper.info.equals("cli")){
                        startActivity(new Intent(ViewClinicActivity.this, EmployeeActivity.class));
                    } else {
                        startActivity(new Intent(ViewClinicActivity.this, EditClinicActivity.class));
                    }
                }
            });
            mainViewholder.title.setText(getItem(position));
            return convertView;
        }
    }
    private class ViewHolder {
        ImageView thumbnail;
        TextView title;
        Button button;
    }
}
