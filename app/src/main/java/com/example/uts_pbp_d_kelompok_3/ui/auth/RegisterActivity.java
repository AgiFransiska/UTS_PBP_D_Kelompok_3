package com.example.uts_pbp_d_kelompok_3.ui.auth;

import static com.example.uts_pbp_d_kelompok_3.PN.MyApplication.CHANNEL_1_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.uts_pbp_d_kelompok_3.Database.DatabaseRegister;
import com.example.uts_pbp_d_kelompok_3.MainActivity;
import com.example.uts_pbp_d_kelompok_3.Model.User;
import com.example.uts_pbp_d_kelompok_3.Preferences.UserPreferences;
import com.example.uts_pbp_d_kelompok_3.R;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etUsername, etPassword;
    private Button btnRegister;
    private UserPreferences userPreferences;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userPreferences = new UserPreferences(RegisterActivity.this);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    register(etName.getText().toString(), etUsername.getText().toString().trim(), etPassword.getText().toString().trim());

                    Intent activityIntent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(RegisterActivity.this,
                            0, activityIntent, 0);

                    Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.logo_ngirim);

                    Notification notification = new NotificationCompat.Builder(RegisterActivity.this, CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_baseline_library_add_check_24)
                            .setContentTitle("REGISTER SUCCES")
                            .setLargeIcon(picture)
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(picture)
                                    .bigLargeIcon(null))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true)
                            .setOnlyAlertOnce(true)
                            .build();

                    notificationManager.notify(1, notification);
                }
            }
        });
    }

    private void register(String name,String username, String password){

        class RegisterUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);

                DatabaseRegister.getInstance(RegisterActivity.this)
                        .getDatabase()
                        .userDao()
                        .registerUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(RegisterActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                clearField();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

        }

        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }

    private void clearField(){
        etName.setText("");
        etUsername.setText("");
        etPassword.setText("");
    }


    private boolean validateForm(){
        /* Check username & password is empty or not */
        if(etUsername.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty() || etName.getText().toString().trim().isEmpty()){
            Toast.makeText(RegisterActivity.this,"Field tidak boleh Kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}