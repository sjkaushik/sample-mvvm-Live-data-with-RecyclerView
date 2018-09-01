package com.android_mvvm_sample_app.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.android_mvvm_sample_app.model.BookResponseModel;
import com.android_mvvm_sample_app.webservice.ApiClient;
import com.android_mvvm_sample_app.webservice.ApiInterface;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaushik on 22-Aug-18.
 */


public class Repository {

    private static String TAG = Repository.class.getSimpleName();
    private ApiInterface apiInterface;

    public Repository() {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<BookResponseModel> getBooksDetails() {

        final MutableLiveData<BookResponseModel> mData =
                new MutableLiveData<>();

        apiInterface.getBooksInfo("love").enqueue(new retrofit2.Callback<BookResponseModel>() {
            @Override
            public void onResponse(Call<BookResponseModel> call, Response<BookResponseModel> response) {

                mData.setValue(response.body());
                Log.e(TAG, "onResponse: " + response.body());


            }

            @Override
            public void onFailure(Call<BookResponseModel> call, Throwable t) {

                Log.e(TAG, "onFailure: something went wrong: " + t.toString());
            }
        });

        return mData;
    }

}
