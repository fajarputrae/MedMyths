package com.coinbkt.medmyth;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coinbkt.medmyth.utils.SPMedmyth;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class CreditActivity extends AppCompatActivity {

    @BindView(R.id.backBtn)
    ImageView backBtn;
    @BindView(R.id.tvMain)
    TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        ButterKnife.bind(this);

        final Boolean isFx = SPMedmyth.getIsFX(this);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.click);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                CreditActivity.this.finish();
            }
        });
    }
}
