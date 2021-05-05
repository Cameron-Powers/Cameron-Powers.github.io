package com.example.clinicapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Clinic.db";
    public static final String TABLE_USERS = "Users";
    public static final String U_COL_01 = "FirstName";
    public static final String U_COL_02 = "LastName";
    public static final String U_COL_03 = "UserName";
    public static final String U_COL_04 = "Password";
    public static final String U_COL_05 = "Email";
    public static final String U_COL_06 = "PhoneNumber";
    public static final String U_COL_07 = "Type";
    public static final String U_COL_08 = "Services";
    public static final String U_COL_09 = "WorkingHours";


    public static final String TABLE_CLINICS = "Clinics";
    public static final String C_COL_01 = "Name";
    public static final String C_COL_02 = "Address";
    public static final String C_COL_03 = "PhoneNumber";
    public static final String C_COL_04 = "Rate";
    public static final String C_COL_05 = "Services";
    public static final String C_COL_06 = "PaymentType";
    public static final String C_COL_07 = "WorkingHours";


    public static final String TABLE_SERVICES = "Services";
    public static final String S_COL_01 = "Name";
    public static final String S_COL_02 = "Role";
    public static final String S_COL_03 = "Clinic";

    public static Clinic clinic = new Clinic();
    public static User user = new User();
    public static Service service = new Service();

    public static String info = "";
    public static Employee e = new Employee();
    public static String shift = "";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (\n" +
                "    " + U_COL_01 + " varchar(200) NOT NULL,\n" +
                "    " + U_COL_02 + " varchar(200) NOT NULL,\n" +
                "    " + U_COL_03 + " varchar(200) PRIMARY KEY,\n" +
                "    " + U_COL_04 + " varchar(200) NOT NULL,\n" +
                "    " + U_COL_05 + " varchar(200) NOT NULL,\n" +
                "    " + U_COL_06 + " varchar(200) NOT NULL,\n" +
                "    " + U_COL_07 + " varchar(200) NOT NULL,\n" +
                "    " + U_COL_08 + " varchar(2000) NOT NULL,\n" +
                "    " + U_COL_09 + " varchar(200) NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_CLINICS + " (\n" +
                "    " + C_COL_01 + " varchar(200) NOT NULL,\n" +
                "    " + C_COL_02 + " varchar(200) PRIMARY KEY,\n" +
                "    " + C_COL_03 + " varchar(200) NOT NULL,\n" +
                "    " + C_COL_04 + " REAL NOT NULL,\n" +
                "    " + C_COL_05 + " varchar(2000) NOT NULL,\n" +
                "    " + C_COL_06 + " varchar(200) NOT NULL,\n" +
                "    " + C_COL_07 + " varchar(200) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLINICS);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    // User Functions
    public void setUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        this.user = null;
        try {
            Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + " where username=? and password=?", new String[]{username, password});
            cursor.moveToFirst();
            this.user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(4), cursor.getString(5), cursor.getString(2), cursor.getString(3));
            String type = cursor.getString(6);
            if (type.equals("Patient")) {
                //this.user = Patient.toPatient(this.user);
                this.user.type = "Patient";
            } else if (type.equals("Employee")) {
                //this.user = Employee.toEmployee(this.user);
                this.user.type = "Employee";
            }
        }catch(Exception e){
            this.user = new User();
        }
    }

    public User getUser() {
        return user;
    }

    public boolean canCreate(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + " where username=?", new String[]{username});
        if (cursor.getCount() > 0) return false;
        return true;
    }

    public boolean isUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + " where " + U_COL_03 + "=? and " + U_COL_04 + "=?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public boolean addEmployee(Employee e) {
        String s = e.serviceToString();
        String w = e.workingHoursToString();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(U_COL_01, e.fname);
        contentValues.put(U_COL_02, e.lname);
        contentValues.put(U_COL_03, e.username);
        contentValues.put(U_COL_04, e.password);
        contentValues.put(U_COL_05, e.email);
        contentValues.put(U_COL_06, e.phoneNum);
        contentValues.put(U_COL_07, "Employee");
        contentValues.put(U_COL_08, s);
        contentValues.put(U_COL_09, w);

        long i = db.insert(TABLE_USERS, null, contentValues);
        if (i == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addPatient(Patient p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(U_COL_01, p.fname);
        contentValues.put(U_COL_02, p.lname);
        contentValues.put(U_COL_03, p.username);
        contentValues.put(U_COL_04, p.password);
        contentValues.put(U_COL_05, p.email);
        contentValues.put(U_COL_06, p.phoneNum);
        contentValues.put(U_COL_07, "Patient");
        long i = db.insert(TABLE_USERS, null, contentValues);
        if (i == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(U_COL_01, u.getFname());
        contentValues.put(U_COL_02, u.getLname());
        contentValues.put(U_COL_03, u.getUsername());
        contentValues.put(U_COL_04, u.getPassword());
        contentValues.put(U_COL_05, u.getEmail());
        contentValues.put(U_COL_06, u.getPhoneNum());
        contentValues.put(U_COL_07, u.getType());
        long i = db.insert(TABLE_USERS, null, contentValues);
        if (i == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addClinic(Clinic c) {
        String s = c.serviceToString();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_COL_01, c.getName());
        contentValues.put(C_COL_02, c.getAddress());
        contentValues.put(C_COL_03, c.getPhoneNum());
        contentValues.put(C_COL_04, c.getRate());
        contentValues.put(C_COL_05, s);
        contentValues.put(C_COL_06, c.getPaymentType());
        contentValues.put(C_COL_07, c.getWorkingHours());
        long i = db.insert(TABLE_CLINICS, null, contentValues);
        if (i == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_USERS, "username =?", new String[]{user.username});
        return i > 1;
    }


    public boolean deleteClinic(Clinic c){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_CLINICS, ""+C_COL_01+" =?", new String[]{c.name});
        return i > 1;
    }

    public ArrayList<String> getUsers(){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + "", new String[]{});
            cursor.moveToFirst();
            //cursor.moveToNext();
            int i = 0;
            if (cursor != null) {
                i = cursor.getColumnIndex(U_COL_03);
                do {
                    ret.add(cursor.getString(i));
                } while (cursor.moveToNext());
            }
            return ret;
        }catch(Exception e){
            return new ArrayList<String>();
        }
    }

    public ArrayList<String> getClinics(){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from " + TABLE_CLINICS + "",new String[]{});
            cursor.moveToFirst();
            //cursor.moveToNext();
            if(cursor != null) {
                do {
                    int i = cursor.getColumnIndex(C_COL_01);
                    ret.add(cursor.getString(i));
                } while (cursor.moveToNext());
            }
            return ret;
        }catch(Exception e) {
            return new ArrayList<String>();
        }
    }

    public User getUserToEdit(){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + " where username=?", new String[]{getUser().username});
            cursor.moveToFirst();
            //cursor.moveToNext();
            getUser().fname = cursor.getString(cursor.getColumnIndex(U_COL_01));
            getUser().lname = cursor.getString(cursor.getColumnIndex(U_COL_02));
            getUser().username = cursor.getString(cursor.getColumnIndex(U_COL_03));
            getUser().password = cursor.getString(cursor.getColumnIndex(U_COL_04));
            getUser().email = cursor.getString(cursor.getColumnIndex(U_COL_05));
            getUser().phoneNum = cursor.getString(cursor.getColumnIndex(U_COL_06));
            getUser().type = cursor.getString(cursor.getColumnIndex(U_COL_07));
        }catch(Exception e){
            this.setUser(new User());
        }finally {
            return this.getUser();
        }
    }

    public Employee getEmployee(User u){
        ArrayList<String> ret = new ArrayList<String>();
        Employee e = new Employee();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + " where " + U_COL_03 + "=?", new String[]{getUser().username});
            cursor.moveToFirst();
            //cursor.moveToNext();
            e.fname = cursor.getString(cursor.getColumnIndex(U_COL_01));
            e.lname = cursor.getString(cursor.getColumnIndex(U_COL_02));
            e.username = cursor.getString(cursor.getColumnIndex(U_COL_03));
            e.password = cursor.getString(cursor.getColumnIndex(U_COL_04));
            e.email = cursor.getString(cursor.getColumnIndex(U_COL_05));
            e.phoneNum = cursor.getString(cursor.getColumnIndex(U_COL_06));
            e.type = cursor.getString(cursor.getColumnIndex(U_COL_07));
            e.services = Clinic.serviceDeCode(cursor.getString(cursor.getColumnIndex(U_COL_08)));
            e.workingHours = Employee.workingHoursDecode(cursor.getString(cursor.getColumnIndex(U_COL_09)));
        }catch(Exception xe){
            e = new Employee();
        }finally {
            return e;
        }
    }

    public Clinic getClinicToEdit(){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from " + TABLE_CLINICS + " where " + C_COL_01 + "=?", new String[]{getClinic().getName()});
            cursor.moveToFirst();
            //cursor.moveToNext();
            this.clinic.setName(cursor.getString(cursor.getColumnIndex(C_COL_01)));
            this.clinic.setAddress(cursor.getString(cursor.getColumnIndex(C_COL_02)));
            this.clinic.setPhoneNum(cursor.getString(cursor.getColumnIndex(C_COL_03)));
            this.clinic.setRate(cursor.getDouble(cursor.getColumnIndex(C_COL_04)));
            this.clinic.setServices(Clinic.serviceDeCode(cursor.getString(cursor.getColumnIndex(C_COL_05))));
            this.clinic.setPaymentType(cursor.getString(cursor.getColumnIndex(C_COL_06)));
            this.clinic.setWorkingHours(cursor.getString(cursor.getColumnIndex(C_COL_07)));
        }catch(Exception CursorIndexOutOfBoundsException){
            this.clinic = new Clinic();
        }finally {
            return this.clinic;
        }
    }

    public int getNumClinics(){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select "+C_COL_01+" from " + TABLE_CLINICS + "",new String[]{});
        return cursor.getCount();
    }

    public boolean isClinic(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_CLINICS + " where " + C_COL_01 + "=?", new String[]{name});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public String getServices(Clinic c){
        String ret = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_CLINICS + " where "+C_COL_01+" =?",new String[]{c.name});
        cursor.moveToFirst();
        do {
            int i = cursor.getColumnIndex(C_COL_05);
            ret += (cursor.getString(i));
        }while(cursor.moveToNext());
        return ret;
    }

    public boolean checkUserNum(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USERS + "", new String[]{});
        if (cursor.getCount() != 0) {return true;}
        return false;
    }
    public boolean checkClinicNum(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + C_COL_01 + " from " + TABLE_CLINICS + "", new String[]{});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public void setClinic(Clinic clinic) {
        DatabaseHelper.clinic = clinic;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public static void setUser(User user) {
        DatabaseHelper.user = user;
    }

    public static void setService(Service service) {
        DatabaseHelper.service = service;
    }

    public static Service getService() {
        return service;
    }
}