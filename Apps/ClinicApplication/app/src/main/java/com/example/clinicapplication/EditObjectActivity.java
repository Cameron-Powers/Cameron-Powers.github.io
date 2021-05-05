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

public class EditObjectActivity extends AppCompatActivity {

    private ListView editlistView;
    DatabaseHelper mDatabaseHelper;
    private Button delete, done;

    private TextView title;
    private ViewHolder mainViewholder;

    private ArrayList<ViewHolder> allViews;

    private ArrayList<String> rows = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_object);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        delete = (Button)findViewById(R.id.btnEditDelete);
        done = (Button)findViewById(R.id.btnEditDone);
        editlistView = (ListView) findViewById(R.id.EditListView);
        title = (TextView)findViewById(R.id.edit_obj_title);
        mDatabaseHelper = new DatabaseHelper(this);
        allViews = new ArrayList<ViewHolder>();

        if(mDatabaseHelper.info.equals("emp")){
            delete.setEnabled(false);
        }else{
            delete.setEnabled(true);
        }

        if(mDatabaseHelper.getUser() != null) {
            User user = mDatabaseHelper.getUserToEdit();
            ArrayList<String> userInfo = new ArrayList<>();
            userInfo.add(user.getFname());
            userInfo.add(user.getLname());
            userInfo.add(user.getUsername());
            userInfo.add(user.getPassword());
            userInfo.add(user.getEmail());
            userInfo.add(user.getPhoneNum());
            userInfo.add(user.getType());

            //User user = new User();
            if (user == null) {
                startActivity(new Intent(EditObjectActivity.this, ViewUserActivity.class));
            }
            rows.add("first name");
            rows.add("last name:");
            rows.add("username:");
            rows.add("password:");
            rows.add("email:");
            rows.add("phoneNum:");
            rows.add("type:");
            editlistView.setAdapter(new EditObjectActivity.MyListAdaper(this, R.layout.edit_list_item, rows, userInfo));
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteUser(mDatabaseHelper.getUser());
                Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(EditObjectActivity.this,ViewUserActivity.class));
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.fname = allViews.get(0).input.getText().toString();
                u.lname = allViews.get(1).input.getText().toString();
                u.username = allViews.get(2).input.getText().toString();
                u.password = allViews.get(3).input.getText().toString();
                u.email = allViews.get(4).input.getText().toString();
                u.phoneNum = allViews.get(5).input.getText().toString();
                u.type = allViews.get(6).input.getText().toString();
                if (validate(u)) {
                    if(checkType(u)) {
                        if (mDatabaseHelper.canCreate(u.getUsername())) {
                            mDatabaseHelper.deleteUser(mDatabaseHelper.getUser());
                            mDatabaseHelper.addUser(u);
                            if (mDatabaseHelper.info.equals("emp"))
                                startActivity(new Intent(EditObjectActivity.this, EmployeeActivity.class));
                            else
                                startActivity(new Intent(EditObjectActivity.this, ViewUserActivity.class));

                            Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG).show();
                        } else if ( mDatabaseHelper.getUser().getUsername().equals(u.getUsername())){
                            User us = mDatabaseHelper.getUser();
                            if (us.getType().equals("Employee")) {
                                Employee e = mDatabaseHelper.getEmployee(mDatabaseHelper.getUser());
                                e = userInfoTransfer(e,u);
                                mDatabaseHelper.deleteUser(us);
                                mDatabaseHelper.addEmployee(e);
                            } else {
                                mDatabaseHelper.deleteUser(us);
                                mDatabaseHelper.addUser(u);
                            }
                            if (mDatabaseHelper.info.equals("emp"))
                                startActivity(new Intent(EditObjectActivity.this, EmployeeActivity.class));
                            else
                                startActivity(new Intent(EditObjectActivity.this, ViewUserActivity.class));

                            //Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Incorrect Type! Employee or Patient!", Toast.LENGTH_LONG).show();
                    }
                    } else {
                    Toast.makeText(getApplicationContext(), "Empty Input Fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Employee userInfoTransfer(Employee e, User u){
        e.fname = u.getFname();
        e.lname = u.getLname();
        e.username = u.getUsername();
        e.password = u.getPassword();
        e.email = u.getEmail();
        e.phoneNum = u.getPhoneNum();
        e.type = "Employee";
        return e;
    }


    private boolean checkType(User u){
        return (u.getType().equals("Patient") || u.getType().equals("Employee"));
    }

    private boolean validate(User u) {
        return (u.getFname().length() != 0 &&
                u.getLname().length() != 0 &&
                u.getUsername().length() != 0 &&
                u.getPassword().length() != 0 &&
                u.getPhoneNum().length() != 0 &&
                u.getEmail().length() != 0 &&
                u.getType().length() != 0);
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> titles;
        ArrayList<String> userInfo;
        private MyListAdaper(Context context, int resource, List<String> titles, ArrayList<String> userInfo) {
            super(context, resource, titles);
            this.titles = titles;
            layout = resource;
            this.userInfo = userInfo;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.row = (TextView) convertView.findViewById(R.id.textViewEdit);
                viewHolder.input = (EditText) convertView.findViewById(R.id.Edit_item_etInput);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.row.setText(this.titles.get(position));
            mainViewholder.input.setText(this.userInfo.get(position));
            allViews.add(mainViewholder);
            return convertView;
        }
    }
    private class ViewHolder {
        TextView row;
        EditText input;
    }

}