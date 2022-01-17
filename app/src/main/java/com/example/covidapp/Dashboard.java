package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void ViewReport(View view) {
        Intent intent=new Intent(Dashboard.this,ViewReport.class);
        startActivity(intent);
    }

    public void viewByDate(View view) {
        Intent intent=new Intent(Dashboard.this,ViewReportByDate.class);
        startActivity(intent);
    }
}