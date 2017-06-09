package com.aditya.meditrack.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.aditya.meditrack.constants.Priority;

import java.util.Date;

public class Medicine implements Parcelable{

    private long id;
    private String name;
    private Date to;
    private Date from;
    private boolean isWeekly;
    private boolean isMonthly;
    private boolean isDaily;
    private boolean isTaken;
    private boolean isTakenToday;
    private String priority;
    private int dosage;

    public Medicine(){

    }

    protected Medicine(Parcel in) {
        id = in.readLong();
        name = in.readString();
        isWeekly = in.readByte() != 0;
        isMonthly = in.readByte() != 0;
        isDaily = in.readByte() != 0;
        isTaken = in.readByte() != 0;
        isTakenToday = in.readByte() != 0;
        priority = in.readString();
        dosage = in.readInt();
    }

    public static final Creator<Medicine> CREATOR = new Creator<Medicine>() {
        @Override
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in);
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public boolean isWeekly() {
        return isWeekly;
    }

    public void setWeekly(boolean weekly) {
        isWeekly = weekly;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public boolean isTakenToday() {
        return isTakenToday;
    }

    public void setTakenToday(boolean takenToday) {
        isTakenToday = takenToday;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeByte((byte) (isWeekly ? 1 : 0));
        dest.writeByte((byte) (isMonthly ? 1 : 0));
        dest.writeByte((byte) (isDaily ? 1 : 0));
        dest.writeByte((byte) (isTaken ? 1 : 0));
        dest.writeByte((byte) (isTakenToday ? 1 : 0));
        dest.writeString(priority);
        dest.writeInt(dosage);
    }
}
