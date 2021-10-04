package com.example.nasa.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nasa.R;
import com.squareup.picasso.Picasso;

import static com.example.nasa.Constant.MY_SHAREPREF;

public class ViewFavorite  extends AppCompatActivity {

    private TextView dateTv, titleTv, explanationTV;
    private ImageView nasaImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_favorite_activity);
        dateTv = findViewById(R.id.date_show);
        titleTv = findViewById(R.id.title);
        nasaImageView = findViewById(R.id.image_view);
        explanationTV = findViewById(R.id.explanation);

        SharedPreferences sh = getSharedPreferences(MY_SHAREPREF, MODE_PRIVATE);

        String date = sh.getString("date", "");
        String title = sh.getString("title", "");
        String nasaImage = sh.getString("nasaImage", "");
        String explanation = sh.getString("explanation", "");

        dateTv.setText(date);
        titleTv.setText(title);
        Glide.with(this).load(nasaImage).into(nasaImageView);
        explanationTV.setText(explanation);


    }
}
