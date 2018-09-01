package com.android_mvvm_sample_app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android_mvvm_sample_app.model.BookResponseModel;
import com.android_mvvm_sample_app.repository.Repository;

/**
 * Created by kaushik on 21-Aug-18.
 */


public class BookViewModel extends ViewModel {

    private LiveData<BookResponseModel> modelLiveData = null;
    private Repository repository;

    public BookViewModel() {

        repository = new Repository();
        modelLiveData = repository.getBooksDetails();
    }

    public LiveData<BookResponseModel> getModelLiveData() {
        return modelLiveData;
    }
}
