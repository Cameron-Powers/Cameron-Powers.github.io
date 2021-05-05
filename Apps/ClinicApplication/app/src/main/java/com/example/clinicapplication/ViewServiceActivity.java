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
public class ViewServiceActivity extends AppCompatActivity {

    private ListView editlist;
    DatabaseHelper mDatabaseHelper;
    private Button done, newS;
    private TextView title;
    public ViewServiceActivity.ViewHolder mainViewholder;
    private ArrayList<ViewServiceActivity.ViewHolder> allViews;
    private ArrayList<String> infoName;
    private ArrayList<Service> ss;
    private ArrayList<String> workhours;
    ViewServiceActivity.MyListAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        done = (Button)findViewById(R.id.btnDoneService);
        newS = (Button)findViewById(R.id.btnNewService);
        title = (TextView)findViewById(R.id.ViewServiceTitle);
        editlist = (ListView)findViewById(R.id.EditServiceList);
        mDatabaseHelper = new DatabaseHelper(this);
        allViews = new ArrayList<ViewServiceActivity.ViewHolder>();
        infoName = new ArrayList<String>();

        if(mDatabaseHelper.info.equals("work")){
            title.setText("Editing Daily WorkHours");
            workhours = mDatabaseHelper.e.getWorkingHours();
            String[] spl;
            for (int i = 0; i < workhours.size(); i++) {
                spl = workhours.get(i).split("\\s");
                if(spl.length%2 == 0) {
                    infoName.add(spl[0] + " -> " + spl[1]);
                }
            }
        }else {
            title.setText("Editing Services");
            mDatabaseHelper.setService(new Service());
            ss = mDatabaseHelper.getClinic().getServices();
            if (ss != null) {
                for (int i = 0; i < ss.size(); i++) {
                    infoName.add(ss.get(i).getName());
                }
            }
        }

        newS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.shift = "";
                startActivity(new Intent(ViewServiceActivity.this,EditServiceActivity.class));
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatabaseHelper.info.equals("work")){
                    // update working hours in employee
                    Employee e = mDatabaseHelper.e;
                    mDatabaseHelper.deleteUser(e);
                    mDatabaseHelper.addEmployee(e);
                    mDatabaseHelper.e = e;
                    startActivity(new Intent(ViewServiceActivity.this, EmployeeActivity.class));
                }else {
                    Clinic c = mDatabaseHelper.getClinic();
                    mDatabaseHelper.deleteClinic(mDatabaseHelper.getClinic());
                    mDatabaseHelper.addClinic(c);
                    mDatabaseHelper.setClinic(c);
                    startActivity(new Intent(ViewServiceActivity.this, EditClinicActivity.class));
                }
            }
        });

        adaper = new ViewServiceActivity.MyListAdaper(this, R.layout.list_item, infoName);
        editlist.setAdapter(adaper);
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        ArrayList<String> infoName;
        private MyListAdaper(Context context, int resource, ArrayList<String> infoName) {
            super(context, resource, infoName);
            layout = resource;
            this.infoName = infoName;
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
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDatabaseHelper.info.equals("work")) {
                        mDatabaseHelper.shift = workhours.get(position);
                    } else {
                        mDatabaseHelper.setService(ss.get(position));
                    }
                    startActivity(new Intent(ViewServiceActivity.this, EditServiceActivity.class));
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