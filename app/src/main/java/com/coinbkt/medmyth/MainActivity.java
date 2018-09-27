package com.coinbkt.medmyth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coinbkt.medmyth.utils.SPMedmyth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.factsBtn)
    Button factsBtn;
    @BindView(R.id.settingsBtn)
    Button settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start service and play music
        startService(new Intent(MainActivity.this, SoundService.class));
        SPMedmyth.setIsMute(MainActivity.this, true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        factsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FactsActivity.class);
                startActivity(intent);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
