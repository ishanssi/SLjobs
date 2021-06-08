package com.slapps.sljobs.ui.jobactivity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://slapphub99.xyz/aSL_Jobs/";
    String BASE_URL2 = "https://api.androidhive.info/contacts";
//    @GET("contacts")
//    Call<Results> getsuperHeroes();

    @GET("jobs")
    Call<String> getStringResponse();
}
