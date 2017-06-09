package com.aditya.meditrack.constants;

import java.io.Serializable;

public enum Error implements Serializable{

    EXCEPTION(101,"Exception"),
    UNAUTHORIZED_ACCESS(102,"Unauthorized Access"),
    MESSAGE_UNSUPPORTED_FILE_TYPE(103,"Unsupported file type"),
    MESSAGE_RESOURCE_NOT_FOUND(104,"Message resource not found"),
    ACCESS_DENIED(105,"Access denied"),
    SOMETHING_WENT_WRONG(106,"Something went wrong"),
    IO_EXCEPTION(107,"IO Exception"),
    JSON_ERROR(108,"Json error"),
    HTTP_EXCEPTION(109,"HTTP Exception"),
    SERVER_ERROR(500,"Server error");

    private int code;
    private String message;

    Error(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
