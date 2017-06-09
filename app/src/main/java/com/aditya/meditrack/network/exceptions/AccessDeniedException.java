package com.aditya.meditrack.network.exceptions;


import com.aditya.meditrack.constants.AppConstant;

public class AccessDeniedException extends HttpException {
    public AccessDeniedException() {
        super(AppConstant.EXCEPTION.ACCESS_DENIED);
    }
}
