package com.example.uts_pbp_d_kelompok_3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TampilTracking extends AppCompatActivity {
    private TrackingPreferences trackingPreferences;
    private Button btnlogout, btnGeolocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking_layout);

        btnlogout = findViewById(R.id.btnlogout);
        btnGeolocation = findViewById(R.id.btnGeolocation);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TampilTracking.this, TrackingActivity.class));
            }
        });

        btnGeolocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TampilTracking.this, MapsActivity.class));
            }
        });
    }

}