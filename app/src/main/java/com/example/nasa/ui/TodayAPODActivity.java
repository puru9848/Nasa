package com.example.nasa.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.R;
import com.squareup.picasso.Picasso;

import static com.example.nasa.Constant.DATE;
import static com.example.nasa.Constant.EXPLANATION;
import static com.example.nasa.Constant.MY_SHAREPREF;
import static com.example.nasa.Constant.NASA_IMAGE;
import static com.example.nasa.Constant.TITLE;

public class TodayAPODActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView date, explanationTV, titleTV;
    private String apodResponseDate, nasaImage, explanation, title;
    private Button addToFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_apod_acitivty);
        Intent intent = getIntent();
        if (intent != null) {
            apodResponseDate = intent.getStringExtra(DATE);
            nasaImage = intent.getStringExtra(NASA_IMAGE);
            explanation = intent.getStringExtra(EXPLANATION);
            title = intent.getStringExtra(TITLE);
        }

        initUI();

    }

    private void initUI() {
        imageView = findViewById(R.id.image_view);
        date = findViewById(R.id.date_show);
        explanationTV = findViewById(R.id.explanation);
        titleTV = findViewById(R.id.title);
        addToFavorite = findViewById(R.id.add_to_favorite);
        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(MY_SHAREPREF, MODE_PRIVATE);

                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                // Storing the key and its value as the data fetched from edittext
                myEdit.putString("date", apodResponseDate);
                myEdit.putString("title", title);
                myEdit.commit();
            }
        });

        showDataOnUI();
    }

    private void showDataOnUI() {
        date.setText(apodResponseDate);
        titleTV.setText(title);
        Picasso.with(this).load(nasaImage).into(imageView);
        explanationTV.setText(explanation);
    }
}