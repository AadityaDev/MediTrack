package com.aditya.meditrack.constants;

public class AppConstant {

    public static final String EMPTY_STRING = "";
    public static final int DEFAULT_INT = 0;
    public static final int DEFAULT_DOUBLE = 0;
    public static final boolean DEFAULT_BOOLEAN = false;
    public static final String BAD_REQUEST = "bad request try again later";
    public static final String COULDNT_REACH_OUR_SERVERS = "could not reach our servers";
    public static final String MESSAGE_SERVER_ERROR = "server error";
    public static final String RESULTS ="results";
    public static final String MEDICINE_SENT="medicine_sent";

    public class EXCEPTION {
        public static final String EXCEPTION = "Exception";
        public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";
        public static final String MESSAGE_UNSUPPORTED_FILE_TYPE = "Unsupported file type";
        public static final String MESSAGE_RESOURCE_NOT_FOUND = "Message resource not found";
        public static final String ACCESS_DENIED = "Access denied";
        public static final String SOMETHING_WENT_WRONG = "Something went wrong";
        public static final String JSON_EXCEPTION = "Json Exception";
        public static final String IO_EXCEPTION = "IO Exception";
        public static final String HTTP_EXCEPTION = "HTTP Exception";

    }

    public class LOCAL_STORE_NAMES {

        public static final String USER_STORE = "UserStore";

    }

    public class USER_STORE {

        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PROFILE_PIC = "profilePic";
        public static final String AUTH_TOKEN = "authToken";
        public static final String IS_LOGGED_IN = "isLoggedIn";

    }

    public static class NotificationConstant {
        //generic constants
        public static final String STATUS = "status";
        public static final String ERRORS = "errors";
        public static final String RESULT = "result";
        public static final String HAS_MORE = "hasMore";
        public static final String MESSAGE = "message";
        public static final String HEADER = "header";
        public static final String SUCCESS = "0";
        // signup constants
        public static final String ERRS = "errs";
        public static final String MSG = "msg";
        //AppIterate
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String CP_ID = "cpId";
    }

}
