package com.example.nasa.viewModel;



import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nasa.network.ApodResponse;
import com.example.nasa.network.NasaRepository;
import com.example.nasa.ui.MainActivity;

import io.reactivex.disposables.CompositeDisposable;


public class APODViewModel extends AndroidViewModel {

    private NasaRepository nasaRepository;
    private MutableLiveData<ApodResponse> apodResponseMutableLiveData;
    private MutableLiveData<ApodResponse> todayResponseMutableLiveData;


    public APODViewModel(@NonNull Application application) {
        super(application);
    }


    public void init() {
        nasaRepository = new NasaRepository(getApplication().getApplicationContext());
        apodResponseMutableLiveData = nasaRepository.getApodResponseMutableLiveData();
        todayResponseMutableLiveData = nasaRepository.getMutableLiveData();


    }

    public void apodAPICall(){
        nasaRepository.apiCall();

    }

    public void todayAPODCall(String date){
        nasaRepository.todayAPICall(date);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MutableLiveData<ApodResponse> getApodResponseMutableLiveData() {
        return apodResponseMutableLiveData;
    }

    public MutableLiveData<ApodResponse> getTodayResponseMutableLiveData() {
        return todayResponseMutableLiveData;
    }
}
