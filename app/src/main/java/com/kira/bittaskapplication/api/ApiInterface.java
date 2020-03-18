package com.kira.bittaskapplication.api;

import com.kira.bittaskapplication.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("categories")
    Call<List<Category>> getCategoryData();
}
