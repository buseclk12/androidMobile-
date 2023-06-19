package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat gestureDetector;
    private MediaPlayer mediaPlayer;
    private ImageView playButton;
    TextView login, register, textGesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });


        textGesture = findViewById(R.id.textGesture);
        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);
        login = findViewById(R.id.loginPage);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.project.Login.class);
                startActivity(intent);
            }
        });
        register = findViewById(R.id.registerPage);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, com.example.project.Register.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        textGesture.setText("www.tigerJewelry.com");
        return true;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        textGesture.setText("Ω Make your style chic Ω");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        textGesture.setText("~Elegant~");
        return true;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        textGesture.setText("~Contact us~");
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        textGesture.setText("~Everywhere~");
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        textGesture.setText("~Join Us~");
        return true;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        textGesture.setText("~Discover us~");
        return true;    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        textGesture.setText("~Sign in to explore~");
    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        textGesture.setText("~~~~~");
        return true;
    }
    //end gestures
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}