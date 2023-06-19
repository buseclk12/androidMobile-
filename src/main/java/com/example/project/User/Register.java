package com.example.project;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register  extends AppCompatActivity {
    TextView textView2, myButton;
    EditText mUsernameEditText;
    EditText mPasswordEditText;
    EditText mEmailEditText;
    ImageView back;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register);
        TextView myButton = findViewById(R.id.myButton);
        //myButton.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral30));

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mUsernameEditText = findViewById(R.id.editTextTextPersonName);
        mPasswordEditText = findViewById(R.id.editTextTextPassword);
        mEmailEditText = findViewById(R.id.editTextEmail);

        mDatabaseHelper = new DatabaseHelper(this);
        TextView registerButton = findViewById(R.id.myButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEditText.getText().toString();
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                boolean addUser = mDatabaseHelper.addUser(username,email,password);
                // Show a toast message to indicate success or failure
                if (addUser) {
                    Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
