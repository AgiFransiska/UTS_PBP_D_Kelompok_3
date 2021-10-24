package com.example.uts_pbp_d_kelompok_3;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.security.AccessController.getContext;

import static com.example.uts_pbp_d_kelompok_3.PN.MyApplication.CHANNEL_1_ID;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

//import com.example.uts_pbp_d_kelompok_3.Databinding.TampilDataKita;
import com.example.uts_pbp_d_kelompok_3.Preferences.UserPreferences;
import com.example.uts_pbp_d_kelompok_3.ui.auth.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private MediaSessionCompat mediaSession;
    private Button btnTracking;
    private Button btnShippingRate;
    private BottomNavigationView btnBottom;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.string.ngirim);
        editTextMessage = findViewById(R.string.reg_berhasil);

        mediaSession = new MediaSessionCompat(this, "tag");

        setContentView(R.layout.activity_main);
        setTitle("Home");

        btnBottom = findViewById(R.id.bottom_navigation);
        btnBottom.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    setTitle("Home");
                }else if(item.getItemId() == R.id.shippingRate){
                    changeFragment(new RateFragment());
                    setTitle("Shipping Rate");
                }else if(item.getItemId() == R.id.tracking){
                    startActivity(new Intent(MainActivity.this, TrackingActivity.class));
                    setTitle("Tracking");
                }else if(item.getItemId() == R.id.menu_logout){
                    onOptionsItemSelected(item);
                }
                return true;
            }
        });
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment, fragment)
                .commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Disini kita menghubungkan menu yang telah kita buat dengan activity ini
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.home_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakah mau melanjutkan logout?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finishAndRemoveTask();
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

//    public View.OnClickListener btnAboutUs = new View.OnClickListener(){
//        @Override
//        public void onClick(View view){
//            Intent mainActivity = new Intent(MainActivity.this, TampilDataKita.class);
//            startActivity(mainActivity);
//        }
//    };

}
