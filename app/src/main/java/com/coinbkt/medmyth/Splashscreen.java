package com.coinbkt.medmyth;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }

    private void StartAnimations() {
        /*Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        loadAnimation.reset();
        RelativeLayout linearLayout = findViewById(R.id.lin_lay);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(loadAnimation);*/

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

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }
}
