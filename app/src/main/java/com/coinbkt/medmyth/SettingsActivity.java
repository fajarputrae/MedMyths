package com.coinbkt.medmyth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.coinbkt.medmyth.utils.SPMedmyth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.backBtn)
    ImageView backBtn;
    @BindView(R.id.musicSwitch)
    Switch musicSwitch;
    @BindView(R.id.soundSwitch)
    Switch soundSwitch;

    boolean isSwitchChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        final Boolean isSwitchChecked = SPMedmyth.getIsMute(this);

        if(!isSwitchChecked)
            musicSwitch.setChecked(false);
        else
            musicSwitch.setChecked(true);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsActivity.this.finish();
            }
        });

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    SPMedmyth.setIsMute(SettingsActivity.this, false);
                    Intent objIntent = new Intent(SettingsActivity.this, SoundService.class);
                    stopService(objIntent);
                }
                else{
                    SPMedmyth.setIsMute(SettingsActivity.this, true);
                    Intent objIntent = new Intent(SettingsActivity.this, SoundService.class);
                    startService(objIntent);
                }
            }
        });
    }
}
