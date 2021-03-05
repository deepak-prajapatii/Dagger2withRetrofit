package com.riseinsteps.daggerwithretrofit;

import com.riseinsteps.daggerwithretrofit.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoApi {

    @GET("api/users?page=1/")
    Call<Model> getModel();
}
