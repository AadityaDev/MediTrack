package com.aditya.meditrack.utils;

public class StringUtils {

    public static boolean isNotEmptyOrNull(String string){
        boolean isNotEmptyOrNull = false;
        if(string==null||string.isEmpty()||string.trim().equals("")||string.equals("null")){
            isNotEmptyOrNull=false;
        }else {
            isNotEmptyOrNull=true;
        }
        return isNotEmptyOrNull;
    }

}
