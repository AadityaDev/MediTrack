package com.aditya.meditrack.constants;

public class AppAPI {

    public static final String API_KEY="4d1733205d0ea62211dd15ae1c1cc559";
    public static final String GET_VIDEO_PREFIX = "https://api.themoviedb.org/3/movie/";
    public static final String GET_VIDEO_SUFFIX="/videos?api_key="+API_KEY+"&language=en-US";
    public static final String PRODUCT_URL = "http://starlord.hackerearth.com/kickstarter";
    public static final String MOVIES_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US&page=1";
    public static final String PAGE="page=";
    public static final String PROFILE_DETAIL_URL = "https://quarkbackend.com/getfile/adityakumarverma1993/profile";
    public static final String IMAGE_SIZE="w160";
    public static final String IMAGE_URL="https://image.tmdb.org/t/p/"+IMAGE_SIZE;
    public static final String VIDEO_URL="https://youtu.be/";
    public static final String MOVIE_DETAIL_PREFIX="https://api.themoviedb.org/3/movie/";
    public static final String MOVIE_DETAIL_SUFFIX="?api_key="+ API_KEY+"&language=en-US";
    public static final String AND = "&";

    public class Headers {
        public static final String ACCEPT_KEY = "Accept";
        public static final String ACCEPT_JSON = "application/json";
        public static final String AUTHORIZATION_KEY = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Request-Headers";
        public static final String ALLOW_HEADERS = "Origin, X-Requested-With, Content-Type, Accept, Authorization";
        public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Request-Method";
        public static final String ALLOW_METHODS = "GET, POST, PUT, DELETE";
        public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        public static final String ALLOW_ORIGIN = "*";
        public static final String SERVER = "server";
        public static final String CLOUDFLARE_NGINX = "cloudflare-nginx";
    }

}
