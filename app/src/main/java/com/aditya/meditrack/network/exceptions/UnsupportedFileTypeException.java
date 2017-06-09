package com.aditya.meditrack.network.exceptions;

import com.aditya.meditrack.constants.AppConstant;

public class UnsupportedFileTypeException extends HttpException {

    public UnsupportedFileTypeException() {
        super(AppConstant.EXCEPTION.MESSAGE_UNSUPPORTED_FILE_TYPE);
    }

}
