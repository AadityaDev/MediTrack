package com.aditya.meditrack.network.exceptions;

import com.aditya.meditrack.constants.AppConstant;

public class ServerErrorException extends HttpException {

    public ServerErrorException() {
        super(AppConstant.MESSAGE_SERVER_ERROR);
    }
}
