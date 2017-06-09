package com.aditya.meditrack.preferences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.aditya.meditrack.models.Medicine;

import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "MediTrack.db";
    public static final String MEDICINE_NAME = "medicine";
    public static final String MEDICINE_COLUMN_ID = "id";
    public static final String MEDICINE_COLUMN_NAME = "name";
    public static final String MEDICINE_COLUMN_TO = "todate";
    public static final String MEDICINE_COLUMN_FROM = "fromdate";
    public static final String MEDICINE_COLUMN_IS_WEEKLY = "isWeekly";
    public static final String MEDICINE_COLUMN_IS_MONTHLY = "isMonthly";
    public static final String MEDICINE_COLUMN_IS_DAILY = "isDaily";
    public static final String MEDICINE_COLUMN_IS_TAKEN = "isTaken";
    public static final String MEDICINE_COLUMN_IS_TAKEN_TODAY = "isTakenToday";
    public static final String MEDICINE_COLUMN_PRIORITY = "medicine_priority";
    public static final String MEDICINE_DOSAGE = "medicine_dosage";

    public DBHelper(@NonNull Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table medicine "+"( id long primary key, name text , todate text, fromdate text, medicine_priority text,"+
                " isWeekly integer, isMonthly integer, isDaily integer, isTaken integer, isTakenToday integer, medicine_dosage integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS medicine");
        onCreate(db);
    }

    public boolean insertMedicine(@NonNull Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MEDICINE_COLUMN_NAME,medicine.getName());
        contentValues.put(MEDICINE_COLUMN_TO,medicine.getTo().toString());
        contentValues.put(MEDICINE_COLUMN_FROM,medicine.getFrom().toString());
        contentValues.put(MEDICINE_COLUMN_IS_WEEKLY,medicine.isWeekly()?1:0);
        contentValues.put(MEDICINE_COLUMN_IS_DAILY,medicine.isDaily()?1:0);
        contentValues.put(MEDICINE_COLUMN_IS_MONTHLY,medicine.isMonthly()?1:0);
        contentValues.put(MEDICINE_COLUMN_IS_TAKEN,medicine.isTaken()?1:0);
        contentValues.put(MEDICINE_COLUMN_IS_TAKEN_TODAY,medicine.isTakenToday()?1:0);
        contentValues.put(MEDICINE_COLUMN_PRIORITY,medicine.getPriority());
        contentValues.put(MEDICINE_DOSAGE,medicine.getDosage());
        db.insert(MEDICINE_NAME,null,contentValues);
        db.close();
        return true;
    }

    public Integer deleteMedicine (long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MEDICINE_NAME,
                "id = ? ",
                new String[] { Long.toString(id) });
    }

    public ArrayList<Medicine> getAllMedicine(){
        ArrayList<Medicine> medicines=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+MEDICINE_NAME, null );
        if(res.moveToFirst()){
        do{
            Medicine medicine=new Medicine();
            medicine.setId(res.getLong(0));
            medicine.setName(res.getString(1));
            medicine.setTo(new Date(res.getString(2)));
            medicine.setFrom(new Date(res.getString(3)));
            medicine.setPriority(res.getString(4));
            medicine.setWeekly(res.getInt(5)==0?Boolean.TRUE:Boolean.FALSE);
            medicine.setMonthly(res.getInt(6)==0?Boolean.TRUE:Boolean.FALSE);
            medicine.setDaily(res.getInt(7)==0?Boolean.TRUE:Boolean.FALSE);
            medicine.setDaily(res.getInt(8)==0?Boolean.TRUE:Boolean.FALSE);
            medicine.setDaily(res.getInt(9)==0?Boolean.TRUE:Boolean.FALSE);
            medicine.setDosage(res.getInt(10));
            medicines.add(medicine);
        }while (res.moveToNext());
        }
        return medicines;
    }


    public ArrayList<Medicine> getMedicinesForToday(){
        Date today=new Date();
        ArrayList<Medicine> medicines=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+MEDICINE_NAME, null );
        if(res.moveToFirst()){
            do{
                Medicine medicine=new Medicine();
                medicine.setId(res.getLong(0));
                medicine.setName(res.getString(1));
                medicine.setTo(new Date(res.getString(2)));
                medicine.setFrom(new Date(res.getString(3)));
                medicine.setPriority(res.getString(4));
                medicine.setWeekly(res.getInt(5)==0?Boolean.TRUE:Boolean.FALSE);
                medicine.setMonthly(res.getInt(6)==0?Boolean.TRUE:Boolean.FALSE);
                medicine.setDaily(res.getInt(7)==0?Boolean.TRUE:Boolean.FALSE);
                medicine.setTaken(res.getInt(8)==0?Boolean.TRUE:Boolean.FALSE);
                medicine.setTakenToday(res.getInt(9)==0?Boolean.TRUE:Boolean.FALSE);
                medicine.setDosage(res.getInt(10));
                if((medicine.getFrom().getTime()<=today.getTime())||(today.getTime()<=medicine.getTo().getTime())){
                    medicine.setTakenToday(true);
                    medicines.add(medicine);
                }
            }while (res.moveToNext());
        }
        return medicines;
    }


}
