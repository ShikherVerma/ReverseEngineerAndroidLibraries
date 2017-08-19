package com.shikherverma.sampleapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiInterface {
    @GET("users/{user}/repos")
    Call<ResponseBody> loadRepos(
            @Path("user") String user
    );
}
