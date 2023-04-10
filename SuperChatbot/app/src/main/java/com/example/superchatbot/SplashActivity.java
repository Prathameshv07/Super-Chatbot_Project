package com.example.superchatbot;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    ProgressBar splashProgress;
    int SPLASH_TIME = 3000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash_activity);

            //This is additional feature, used to run a progress bar
            splashProgress = findViewById(R.id.splashProgress);
            playProgress();

            //Code to start timer and take action after the timer ends
            new Handler(getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do any action here. Now we are moving to next page
                    Intent mySuperIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mySuperIntent);

                    //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                    finish();

                }
            }, SPLASH_TIME);
        }

        private void playProgress() {
            ObjectAnimator.ofInt(splashProgress, "progress", 100)
                    .setDuration(5000)
                    .start();
        }
}
