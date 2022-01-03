package com.example.myapi.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myapi.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splash);
        //بعرف الانميشن هنا اللي عايزه
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.from_below_to_top);

        //بدي الصوره الانميشن اللي عرفته
        logo.startAnimation(animation);
        //بعمل counter لمدة 3 ثواني يخلصوا يعمل intent
        Thread t = new Thread() {

            public void run() {

                try {

                    sleep(3000);
                    finish();
                    Intent IntentToLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(IntentToLoginActivity);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }
}