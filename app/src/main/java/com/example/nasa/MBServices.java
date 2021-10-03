package com.example.nasa;

import com.example.nasa.network.ApodResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MBServices {

    @GET("planetary/apod")
    Single<ApodResponse> getApodTodaysData(@Query("api_key") String apiKey);


    @GET("planetary/apod")
    Single<ApodResponse> getApodDataByDate(@Query("api_key") String apiKey, @Query("date") String date);
}
