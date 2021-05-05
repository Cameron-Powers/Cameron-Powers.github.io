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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectServiceActivity extends AppCompatActivity {

    private ListView editlist;
    DatabaseHelper mDatabaseHelper;
    private Button done;
    private TextView title;
    public SelectServiceActivity.ViewHolder mainViewholder;
    private ArrayList<SelectServiceActivity.ViewHolder> allViews;
    private ArrayList<Service> ss;
    private ArrayList<Service> empSS;
    private ArrayList<String> infoName;
    SelectServiceActivity.MyListAdaper adaper;
    private Boolean[] added;
    Clinic c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
        mDatabaseHelper = new DatabaseHelper(this);

        title = (TextView)findViewById(R.id.selectServiceTitle);
        done = (Button)findViewById(R.id.btnDoneSelectService);
        editlist = (ListView)findViewById(R.id.lvSelectService);

        c = mDatabaseHelper.getClinicToEdit();
        empSS = mDatabaseHelper.e.getServices();
        ss = c.getServices();
        infoName = new ArrayList<String>();
        added = new Boolean[ss.size()];

        //Toast.makeText(getApplicationContext(),  mDatabaseHelper.e.services.size() + "!" , Toast.LENGTH_LONG).show();

        if(ss.size() > 0) {
            title.setText("All services for "+ c.getName() +"!");
            for (int i = 0; i < ss.size(); i++) {
                infoName.add(ss.get(i).getName());
                if(contain(empSS, ss.get(i))){
                    added[i] = true;
                } else {
                    added[i] = false;
                }
            }
        }else{
            title.setText("No services for "+ c.getName() +"!");
            ss = new ArrayList<Service>();
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empSS = new ArrayList<Service>();
                Employee e = mDatabaseHelper.e;
                mDatabaseHelper.deleteUser(mDatabaseHelper.e);
                if(ss.size() > 0) {
                    for (int i = 0; i<ss.size(); i++) {
                       if(added[i]){
                           empSS.add(ss.get(i));
                       }
                    }
                }
                e.setServices(empSS);
                mDatabaseHelper.addEmployee(e);
                //Toast.makeText(getApplicationContext(),  added[0] + " : " + added[1] + " : " +added[2] + "-> " + empSS.size(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(SelectServiceActivity.this, EmployeeActivity.class));
            }
        });

        editlist.setAdapter(new SelectServiceActivity.MyListAdaper(this, R.layout.bullet_list_item, infoName));
    }

    private boolean contain(ArrayList<Service> ss, Service s){
        for(int i = 0; i<ss.size(); i++){
            if(ss.get(i).getName().equals(s.getName())){
                return true;
            }
        }
        return false;
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
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_Bullet_list);
                viewHolder.add = (Switch) convertView.findViewById(R.id.switch_bullet_list);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.title.setText(getItem(position));
            if(added[position])
                mainViewholder.add.setChecked(true);
            else
                mainViewholder.add.setChecked(false);
            
            mainViewholder.add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked == true) {
                        added[position] = true;
                    } else{
                        added[position] = false;
                    }
                }
            });
            return convertView;
        }
    }
    private class ViewHolder {
        TextView title;
        Switch add;
    }
}
