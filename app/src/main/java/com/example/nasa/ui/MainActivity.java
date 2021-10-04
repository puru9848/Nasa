package com.example.nasa.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.nasa.viewModel.APODViewModel;
import com.example.nasa.R;
import com.example.nasa.network.ApodResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.nasa.Constant.DATE;
import static com.example.nasa.Constant.EXPLANATION;
import static com.example.nasa.Constant.NASA_IMAGE;
import static com.example.nasa.Constant.TITLE;

public class MainActivity extends AppCompatActivity {

    private Button apodButton, chooseDate, todayApodButton, viewFavoriteBtn;
    Calendar myCalendar = null;
    DatePickerDialog.OnDateSetListener date;
    private String todayDate;
    private APODViewModel viewModel;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(APODViewModel.class);
        viewModel.init();
        viewModel.getApodResponseMutableLiveData().observe(this, new Observer<ApodResponse>() {
            @Override
            public void onChanged(ApodResponse apodResponse) {
                if (apodResponse != null) {
                    launchAPODAcitivity(apodResponse);
                    dismissLoadingDialog();
                }
            }
        });

        viewModel.getTodayResponseMutableLiveData().observe(this, new Observer<ApodResponse>() {
            @Override
            public void onChanged(ApodResponse apodResponse) {
                if (apodResponse != null) {
                    launchTodayAPODAcitivity(apodResponse);
                    dismissLoadingDialog();
                }

            }
        });

        apodButton = findViewById(R.id.apod_button);
        chooseDate = findViewById(R.id.choose_date);
        todayApodButton = findViewById(R.id.today_apod_button);
        viewFavoriteBtn = findViewById(R.id.view_favorite);

        viewFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewFavorite.class);
                startActivity(intent);
            }
        });


        apodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.apodAPICall();
                showLoadingDialog();


            }
        });


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog =  new DatePickerDialog(MainActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        todayApodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todayDate != null){
                    viewModel.todayAPODCall(todayDate);
                    showLoadingDialog();

                }
                else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Choose Date")
                            .setIcon(R.drawable.ic_baseline_close_24 )
                            .setMessage("Please choose search date from Calendar....")
                            .show();                }

            }
        });


    }

    private void launchTodayAPODAcitivity(ApodResponse apodResponse) {
        Intent intent = new Intent(MainActivity.this, TodayAPODActivity.class);
        intent.putExtra(DATE, apodResponse.getDate());
        intent.putExtra(NASA_IMAGE, apodResponse.getHdurl());
        intent.putExtra(EXPLANATION, apodResponse.getExplanation());
        intent.putExtra(TITLE, apodResponse.getTitle());
        startActivity(intent);
    }

    private void launchAPODAcitivity(ApodResponse apodResponse) {
        Intent intent = new Intent(MainActivity.this, APODActivity.class);
        intent.putExtra(DATE, apodResponse.getDate());
        intent.putExtra(NASA_IMAGE, apodResponse.getHdurl());
        intent.putExtra(EXPLANATION, apodResponse.getExplanation());
        intent.putExtra(TITLE, apodResponse.getTitle());
        startActivity(intent);
    }

    private void updateLabel() {
        String myFormat = "YYYY-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        todayDate = sdf.format(myCalendar.getTime());
        chooseDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle("Please wait");
            progress.setMessage("Please wait............................");
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

}