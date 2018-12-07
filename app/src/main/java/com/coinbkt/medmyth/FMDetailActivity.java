package com.coinbkt.medmyth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coinbkt.medmyth.utils.SPMedmyth;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FMDetailActivity extends AppCompatActivity {

    @BindView(R.id.tvPageTitle)
    TextView tvPageTitle;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvMain)
    TextView tvMain;
    @BindView(R.id.backBtn)
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmdetail);
        ButterKnife.bind(this);

        final Boolean isFx = SPMedmyth.getIsFX(this);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.click);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                FMDetailActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        tvPageTitle.setText(category);
        tvTitle.setText(title);
        tvMain.setText(desc);
        tvMain.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
    }
}
