package com.example.uts_pbp_d_kelompok_3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrackingActivity extends AppCompatActivity {
    private TrackingPreferences trackingPreferences;
    private EditText NoResi;
    private Button btnCek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekresi);

        trackingPreferences = new TrackingPreferences(TrackingActivity.this);
        NoResi = findViewById(R.id.NoResi);
        btnCek = findViewById(R.id.btnCek);
        checkTracking();

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()){
                    if (NoResi.getText().toString().trim().equals("190710411")){
                        trackingPreferences.setTracking(NoResi.getText().toString().trim());
                        checkTracking();
                    }else {
                        Toast.makeText(TrackingActivity.this, "Resi Tidak Tersedia", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateForm(){
        if (NoResi.getText().toString().trim().isEmpty()){
            Toast.makeText(TrackingActivity.this, "Resi Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkTracking(){
        if (trackingPreferences.checkTracking()){
            startActivity(new Intent(TrackingActivity.this, TampilTracking.class));
            finish();
        }
    }
}
