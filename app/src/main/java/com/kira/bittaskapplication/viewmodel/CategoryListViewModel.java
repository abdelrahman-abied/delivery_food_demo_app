package com.kira.bittaskapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kira.bittaskapplication.api.ApiClient;
import com.kira.bittaskapplication.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListViewModel extends ViewModel {
    public MutableLiveData<List<Category>> categoryMutableLiveData = new MutableLiveData<>();


    public void getMediaData() {
        Call<List<Category>> categoryDataCall = ApiClient
                .getInstance().getApi().getCategoryData();
        categoryDataCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });


    }
}
