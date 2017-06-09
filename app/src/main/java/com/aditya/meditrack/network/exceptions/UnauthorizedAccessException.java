package com.aditya.meditrack.network.exceptions;

import com.aditya.meditrack.constants.AppConstant;

public class UnauthorizedAccessException extends HttpException {

    public UnauthorizedAccessException() {
        super(AppConstant.EXCEPTION.UNAUTHORIZED_ACCESS);
    }
}
