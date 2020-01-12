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

public class EditServiceActivity extends AppCompatActivity {


    private ListView editlistservice;
    DatabaseHelper mDatabaseHelper;
    private Button delete, done;

    private TextView title;
    private EditServiceActivity.ViewHolder mainViewholder;
    private ArrayList<EditServiceActivity.ViewHolder> allViews;
    private ArrayList<String> contentInfo = new ArrayList<String>();
    private ArrayList<String> rows = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        editlistservice = (ListView) findViewById(R.id.EditListService);
        title = (TextView) findViewById(R.id.tvEditServiceTitle);
        delete = (Button) findViewById(R.id.btnDeleteService);
        done = (Button) findViewById(R.id.btnDoneEditService);
        allViews = new ArrayList<EditServiceActivity.ViewHolder>();

        mDatabaseHelper = new DatabaseHelper(this);
        Service service = mDatabaseHelper.getService();
        if(mDatabaseHelper.info.equals("work")){
            String shift = mDatabaseHelper.shift;
            String[] split = shift.split("\\s");
            if (shift == null || shift.equals("") || split.length%2 != 0) {
                title.setText("New Shift");
                contentInfo.add("");
                contentInfo.add("");
            } else {
                title.setText("Edit Sift");
                contentInfo.add(split[0]);
                contentInfo.add(split[1]);
            }
            rows.add("startTime");
            rows.add("finishTime");
        }else {
            if (service == null || service.equals(new Service())) {
                title.setText("New Service");
                contentInfo.add("");
                contentInfo.add("");
            } else {
                title.setText("Edit Service");
                contentInfo.add(service.getName());
                contentInfo.add(service.getRole());
            }
            rows.add("Name");
            rows.add("Role");
        }


        editlistservice.setAdapter(new EditServiceActivity.MyListAdaper(this, R.layout.edit_list_item, rows, contentInfo));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatabaseHelper.info.equals("work")){
                    mDatabaseHelper.e.workingHours.remove(mDatabaseHelper.shift);
                }else{
                    mDatabaseHelper.getClinic().services.remove(mDatabaseHelper.getService());
                }
                startActivity(new Intent(EditServiceActivity.this,ViewServiceActivity.class));
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = (allViews.get(0).input.getText().toString());
                String y = (allViews.get(1).input.getText().toString());
                x.trim();
                y.trim();
                if(!x.equals("") && !y.equals("")){
                    if(mDatabaseHelper.info.equals("work")){
                        String wh = x + " " + y + " ";
                        mDatabaseHelper.e.workingHours.remove(mDatabaseHelper.shift);
                        mDatabaseHelper.e.workingHours.add(wh);
                    }else {
                        Service s = new Service(x, y, mDatabaseHelper.getClinic().getName());
                        mDatabaseHelper.getClinic().services.remove(mDatabaseHelper.getService());
                        mDatabaseHelper.getClinic().services.add(s);
                    }
                }
                startActivity(new Intent(EditServiceActivity.this,ViewServiceActivity.class));
            }
        });
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> titles;
        ArrayList<String> contentInfo;
        private MyListAdaper(Context context, int resource, List<String> titles, ArrayList<String> contentInfo) {
            super(context, resource, titles);
            this.titles = titles;
            layout = resource;
            this.contentInfo = contentInfo;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                EditServiceActivity.ViewHolder viewHolder = new EditServiceActivity.ViewHolder();
                viewHolder.row = (TextView) convertView.findViewById(R.id.textViewEdit);
                viewHolder.input = (EditText) convertView.findViewById(R.id.Edit_item_etInput);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.row.setText(this.titles.get(position));
            mainViewholder.input.setText(this.contentInfo.get(position));
            allViews.add(mainViewholder);
            return convertView;
        }
    }
    private class ViewHolder {
        TextView row;
        EditText input;
    }

}
