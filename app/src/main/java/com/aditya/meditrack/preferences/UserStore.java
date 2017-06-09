package com.aditya.meditrack.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.ArraySet;

import com.aditya.meditrack.constants.AppAPI;
import com.aditya.meditrack.constants.AppConstant;
import com.aditya.meditrack.models.Medicine;
import com.aditya.meditrack.models.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserStore {

    private Context context;
    private static final String TAG = "UserStore";
    private static final String PREFERENCE_NAME = "UserStore";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_MOBILE = "mobile";
    public static final String USER_TOKEN = "authToken";
    public static final String USER_AGE = "age";
    public static final String USER_MEDICINE = "medicine";
    public static final String USER_PROFILE_PIC = "profilePic";
    public static final String SOS_USER_NAME = "sos_name";
    public static final String SOS_USER_MOBILE = "sos_mobile";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public UserStore(@NonNull Context context) {
        this.context=context;
        preferences=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private User getUserInfo(){
        User user=new User();
        user.setName(preferences.getString(USER_NAME, AppConstant.EMPTY_STRING));
        user.setEmail(preferences.getString(USER_EMAIL,AppConstant.EMPTY_STRING));
        user.setAuthToken(preferences.getString(USER_TOKEN,AppConstant.EMPTY_STRING));
        user.setAge(preferences.getInt(USER_AGE,0));
        user.setProfilePic(preferences.getString(USER_PROFILE_PIC,AppConstant.EMPTY_STRING));
        return user;
    }

    public static void setUserName(@NonNull String name) {
        editor.putString(USER_NAME, name).apply();
    }

    public static String getUserName() {
        return preferences.getString(USER_NAME, "");
    }

    public static void setUserMobile(@NonNull String userMobile) {
        editor.putString(USER_MOBILE, userMobile).apply();
    }

    public static String getUserMobile() {
        return preferences.getString(USER_MOBILE, "");
    }

    public static void setUserToken(@NonNull String userToken) {
        editor.putString(USER_TOKEN, userToken).apply();
    }

    public static String getUserToken() {
        return preferences.getString(USER_TOKEN,"");
    }

    public static void setAge(int age){
        editor.putInt(USER_AGE,age).apply();
    }

    public static int getAge(){
        return preferences.getInt(USER_AGE, 0);
    }

    public static void setImage(@NonNull String imagePath){
        editor.putString(USER_PROFILE_PIC,imagePath).apply();
    }

    public static String getImage(){
        return preferences.getString(USER_PROFILE_PIC,AppConstant.EMPTY_STRING);
    }

    public static void setUserEmail(@NonNull String imagePath){
        editor.putString(USER_EMAIL,imagePath).apply();
    }

    public static String getUserEmail(){
        return preferences.getString(USER_EMAIL,AppConstant.EMPTY_STRING);
    }

    public static void setSOSUserName(@NonNull String sosUserName){
        editor.putString(SOS_USER_NAME,sosUserName).apply();
    }

    public static String getSOSUserName(){
        return preferences.getString(SOS_USER_NAME,AppConstant.EMPTY_STRING);
    }


    public static void setSOSUserMobile(@NonNull String sosUserMobile){
        editor.putString(SOS_USER_MOBILE,sosUserMobile).apply();
    }

    public static String getSOSUserMobile(){
        return preferences.getString(SOS_USER_MOBILE,AppConstant.EMPTY_STRING);
    }


}
