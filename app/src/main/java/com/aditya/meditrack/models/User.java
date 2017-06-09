package com.aditya.meditrack.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class User implements Parcelable{

    private long id;
    private int age;
    private String name;
    private String email;
    private String mobile;
    private String authToken;
    private String profilePic;
    private String password;
    private User sosContact;
    private List<Medicine> medicines;
    private String address;

    public User(){

    }

    protected User(Parcel in) {
        id = in.readLong();
        age = in.readInt();
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        authToken = in.readString();
        profilePic = in.readString();
        password = in.readString();
        sosContact = in.readParcelable(User.class.getClassLoader());
        medicines = in.createTypedArrayList(Medicine.CREATOR);
        address = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getSosContact() {
        return sosContact;
    }

    public void setSosContact(User sosContact) {
        this.sosContact = sosContact;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeString(authToken);
        dest.writeString(profilePic);
        dest.writeString(password);
        dest.writeParcelable(sosContact, flags);
        dest.writeTypedList(medicines);
        dest.writeString(address);
    }
}
