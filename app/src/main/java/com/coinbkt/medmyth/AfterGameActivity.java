package com.coinbkt.medmyth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinbkt.medmyth.db.DaoSession;
import com.coinbkt.medmyth.db.FMLibrary;
import com.coinbkt.medmyth.db.FMLibraryDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class AfterGameActivity extends AppCompatActivity {

    MedMythApp medMythApp;
    DaoSession daoSession;
    List<FMLibrary> fmLibraryList;

    @BindView(R.id.tvScores)
    TextView tvScores;
    @BindView(R.id.tvScores1)
    TextView tvScores1;
    @BindView(R.id.linScores)
    LinearLayout linScores;
    @BindView(R.id.tvFMTitle)
    TextView tvFMTitle;
    @BindView(R.id.tvMain)
    TextView tvMain;

    String packName;
    int point, randFact;

    private final int REFRESH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game);
        ButterKnife.bind(this);

        medMythApp = (MedMythApp) getApplication();
        daoSession = medMythApp.getDaoSession();

        Intent intent = new Intent(getIntent());
        packName = intent.getStringExtra("name");
        point = intent.getIntExtra("point",0);
        ArrayList<String> gameResult = intent.getStringArrayListExtra("gameResult");

        fmLibraryList = daoSession.getFMLibraryDao().queryBuilder().where(FMLibraryDao.Properties.PackName.eq(packName)).list();

        tvScores.setText(String.valueOf(point));
        tvScores1.setText(String.valueOf(point));
        for (int i=0; i<fmLibraryList.size(); i++){
            if(fmLibraryList.get(i).getStatus().equals(gameResult.get(i))){
                ImageView image = new ImageView(this);
                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(44,44));
                image.setMaxHeight(44);
                image.setMaxWidth(44);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 0, 0, 0);
                image.setLayoutParams(lp);

                image.setImageResource(R.drawable.layer_14);

                // Adds the view to the layout
                linScores.addView(image);
            }
            else{
                ImageView image = new ImageView(this);
                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(44,44));
                image.setMaxHeight(44);
                image.setMaxWidth(44);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 0, 0, 0);
                image.setLayoutParams(lp);

                image.setImageResource(R.drawable.layer_15);

                // Adds the view to the layout
                linScores.addView(image);
            }
        }

        Random r = new Random();
        randFact = r.nextInt(5 - 1) + 1;

        tvFMTitle.setText(fmLibraryList.get(randFact).getTitle());
        tvMain.setText(fmLibraryList.get(randFact).getDesc());
        tvMain.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(1);
    }
}
