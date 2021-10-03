package com.example.nasa;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.network.ApodResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.nasa.Constant.API_KEY;

public class APODActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private MBServices mbServices;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);

        mbServices = ApodClient.getClient(getApplicationContext()).create(MBServices.class);
        apiCall();

    }

    private void apiCall() {
        disposable.add(mbServices.getApodTodaysData(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApodResponse>() {
                    @Override
                    public void onSuccess(ApodResponse apodResponse) {
                        Log.i("Puru", "onSuccess: "+apodResponse.getDate());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Puru", "onSuccess: "+e);
                    }
                }));


    }
}
