package com.example.clinicapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewUserActivity extends AppCompatActivity {

    private ListView listView;
    private TextView title;
    private ArrayAdapter adapter;
    private Button back;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        listView = (ListView) findViewById(R.id.listView);
        title = (TextView)findViewById(R.id.UserViewTitle);
        back = (Button)findViewById(R.id.backViewUser);
        mDatabaseHelper = new DatabaseHelper(this);

        ArrayList<String> users = mDatabaseHelper.getUsers();
        if (users.size() == 0) {
            title.setText("NO USERS!");
        }
        else {
            title.setText("All Users");
            Iterator<String> it = users.iterator();

            listView.setAdapter(new MyListAdaper(this, R.layout.list_item, users));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.user = (new Admin());
                startActivity(new Intent(ViewUserActivity.this, AdminActivity.class));

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
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userToEdit = getItem(position);
                    mDatabaseHelper.user = new User();
                    mDatabaseHelper.user.username = userToEdit;
                    startActivity(new Intent(ViewUserActivity.this, EditObjectActivity.class));
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
