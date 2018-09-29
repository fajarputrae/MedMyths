package com.coinbkt.medmyth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coinbkt.medmyth.utils.SPMedmyth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.playBtn)
    Button playBtn;
    @BindView(R.id.factsBtn)
    Button factsBtn;
    @BindView(R.id.settingsBtn)
    Button settingsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start service and play music
        startService(new Intent(MainActivity.this, SoundService.class));

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.click);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Boolean isFx = SPMedmyth.getIsFX(this);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                Intent intent = new Intent(MainActivity.this, PackActivity.class);
                startActivity(intent);
            }
        });

        factsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                Intent intent = new Intent(MainActivity.this, FMLibraryActivity.class);
                startActivity(intent);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent objIntent = new Intent(MainActivity.this, SoundService.class);
        stopService(objIntent);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Intent objIntent = new Intent(MainActivity.this, SoundService.class);
        stopService(objIntent);
        super.onDestroy();
    }

}
