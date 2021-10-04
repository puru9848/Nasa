package com.example.nasa.network;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.nasa.MBServices;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.nasa.Constant.API_KEY;

public class NasaRepository {

    private MBServices mbServices;
    private CompositeDisposable disposable = new CompositeDisposable();;
    private MutableLiveData<ApodResponse> apodResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ApodResponse> mutableLiveData = new MutableLiveData<>();



    public NasaRepository(Context applicationContext) {
        mbServices = ApodClient.getClient(applicationContext).create(MBServices.class);

    }

    public void apiCall(){

        disposable.add(mbServices.getApodTodaysData(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApodResponse>() {
                    @Override
                    public void onSuccess(ApodResponse apodResponse) {
                        apodResponseMutableLiveData.postValue(apodResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        apodResponseMutableLiveData.postValue(null);
                    }
                }));
    }

    public void todayAPICall(String todayDateM) {

        disposable.add(mbServices.getApodDataByDate(API_KEY, todayDateM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApodResponse>() {
                    @Override
                    public void onSuccess(ApodResponse apodResponse) {
                        mutableLiveData.postValue(apodResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mutableLiveData.postValue(null);
                    }
                }));

    }

    public MutableLiveData<ApodResponse> getApodResponseMutableLiveData() {
        return apodResponseMutableLiveData;
    }

    public MutableLiveData<ApodResponse> getMutableLiveData() {
        return mutableLiveData;
    }
}
