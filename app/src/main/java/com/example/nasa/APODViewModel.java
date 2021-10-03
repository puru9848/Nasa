package com.example.nasa;



import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nasa.network.ApodResponse;

import io.reactivex.disposables.CompositeDisposable;


public class APODViewModel extends ViewModel {


    protected CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<ApodResponse> apodResponseMutableLiveData = new MutableLiveData<>();
    private ApodRepo repo;




    public MutableLiveData<ApodResponse> getApodResponseMutableLiveData() {
        return apodResponseMutableLiveData;
    }

    public void setApodResponseMutableLiveData(MutableLiveData<ApodResponse> apodResponseMutableLiveData) {
        this.apodResponseMutableLiveData = apodResponseMutableLiveData;
    }
}
