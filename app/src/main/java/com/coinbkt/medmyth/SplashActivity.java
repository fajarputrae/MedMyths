package com.coinbkt.medmyth;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    protected int _splashTime = 3500;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }

    private void StartAnimations() {

        mediaPlayer = MediaPlayer.create(this, R.raw.bgm_splash);
        mediaPlayer.setVolume(25,25);
        mediaPlayer.start();

        Animation loadAnimation1 = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        loadAnimation1.reset();
        TextView tv1 = findViewById(R.id.splash1);
        tv1.clearAnimation();
        tv1.startAnimation(loadAnimation1);

        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.translate_anim2);
        loadAnimation2.reset();
        TextView tv2 = findViewById(R.id.splash2);
        tv2.clearAnimation();
        tv2.startAnimation(loadAnimation2);

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < _splashTime) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashThread.start();

    }
}
