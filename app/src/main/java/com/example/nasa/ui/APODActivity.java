package com.example.nasa.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.ApodClient;
import com.example.nasa.MBServices;
import com.example.nasa.R;
import com.example.nasa.network.ApodResponse;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.nasa.Constant.API_KEY;
import static com.example.nasa.Constant.DATE;
import static com.example.nasa.Constant.EXPLANATION;
import static com.example.nasa.Constant.MY_SHAREPREF;
import static com.example.nasa.Constant.NASA_IMAGE;
import static com.example.nasa.Constant.TITLE;

public class APODActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView date, explanationTV, titleTV;
    private String apodResponseDate, nasaImage, explanation, title;
    private Button addToFavorite;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);
        Intent intent = getIntent();
        if (intent != null){
            apodResponseDate =  intent.getStringExtra(DATE);
            nasaImage =  intent.getStringExtra(NASA_IMAGE);
            explanation =  intent.getStringExtra(EXPLANATION);
            title =  intent.getStringExtra(TITLE);
        }

        initUI(apodResponseDate);

    }

    private void initUI(String apodResponseDateR) {
        imageView = findViewById(R.id.image_view);
        date = findViewById(R.id.date_show);
        explanationTV = findViewById(R.id.explanation);
        titleTV = findViewById(R.id.title);
        addToFavorite = findViewById(R.id.add_to_favorite);
        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(MY_SHAREPREF, MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("date", apodResponseDate);
                myEdit.putString("title", title);
                myEdit.putString("nasaImage", nasaImage);
                myEdit.putString("explanation", explanation);
                myEdit.commit();
            }
        });
        showDataOnUI(apodResponseDateR);
    }

    private void showDataOnUI(String apodResponseDate) {
        date.setText(apodResponseDate);
        titleTV.setText(title);
        Picasso.with(this).load(nasaImage).into(imageView);
        explanationTV.setText(explanation);
    }
}
