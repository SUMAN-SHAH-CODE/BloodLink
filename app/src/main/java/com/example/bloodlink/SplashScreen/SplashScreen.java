package com.example.bloodlink.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bloodlink.Login_SignUp_ForgetPassword_Portal.MainActivity;
import com.example.bloodlink.R;

public class SplashScreen extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
Handler handler=new Handler();
Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView=findViewById(R.id.animationView);

        runnable=new Runnable() {
            @Override
            public void run() {
            lottieAnimationView.playAnimation();
                Intent i =new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
        handler.postDelayed(runnable,3000);



    }
}