package com.example.project;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity{

    EditText passwordEditText, editTextEmail;
    TextView registerTextView,  loginbutton;
    ImageView back;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);


        passwordEditText = findViewById(R.id.editTextTextPassword);
        editTextEmail =  findViewById(R.id.editTextEmail);
        back = findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kayıt olma ekranına geçiş yapma!
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

                Intent serviceIntent = new Intent(Login.this, MyIntentService.class);
                startService(serviceIntent);
            }
        });

        loginbutton = findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = sharedPreferences.getInt("id", -1);
                String email = editTextEmail.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username and password are required.", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                    User user = dbHelper.getUser(email, password);

                    if (user != null) {
                        // kullanıcı adı ve şifre doğru
                        Intent intent = new Intent(Login.this, HomePage.class);
                        intent.putExtra("email", user.getEmail());
                        intent.putExtra("password", user.getPassword());
                        startActivity(intent);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("id", userId);
                        editor.apply();
                        finish();
                    } else {
                        // kullanıcı adı veya şifre yanlış
                        Toast.makeText(getApplicationContext(), "Username and password are wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}