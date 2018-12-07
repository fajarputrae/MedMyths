package com.coinbkt.medmyth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.coinbkt.medmyth.db.DaoSession;
import com.coinbkt.medmyth.db.FMLibrary;
import com.coinbkt.medmyth.db.FMLibraryDao;
import com.coinbkt.medmyth.db.Packs;
import com.coinbkt.medmyth.db.PacksDao;
import com.coinbkt.medmyth.utils.SPMedmyth;

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

    Packs packsNext;

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
    @BindView(R.id.ivRetry)
    ImageView ivRetry;
    @BindView(R.id.ivPacks)
    ImageView ivPacks;
    @BindView(R.id.ivNext)
    ImageView ivNext;

    String packName;
    int point, randFact, idPack;

    MaterialDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game);
        ButterKnife.bind(this);

        final Boolean isFx = SPMedmyth.getIsFX(this);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.click);

        medMythApp = (MedMythApp) getApplication();
        daoSession = medMythApp.getDaoSession();

        Intent intent = new Intent(getIntent());
        packName = intent.getStringExtra("name");
        point = intent.getIntExtra("point",0);
        idPack = intent.getIntExtra("idPack", 1);

        ArrayList<String> gameResult = intent.getStringArrayListExtra("gameResult");

        fmLibraryList = daoSession.getFMLibraryDao().queryBuilder().where(FMLibraryDao.Properties.PackName.eq(packName)).list();
        packsNext = daoSession.getPacksDao().queryBuilder().where(PacksDao.Properties.IdPack.eq(idPack+1)).unique();

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

        ivRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                Intent i = new Intent(AfterGameActivity.this, GameActivity.class);
                i.putExtra("name", packName);
                i.putExtra("idPack", idPack);
                startActivity(i);
                AfterGameActivity.this.finish();
            }
        });

        ivPacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                onBackPressed();
            }
        });

        if(point>=80){
            ivNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFx)
                        mp.start();
                    Intent i = new Intent(AfterGameActivity.this, GameActivity.class);
                    i.putExtra("name", packsNext.getPackName());
                    i.putExtra("idPack", packsNext.getIdPack());
                    startActivity(i);
                    AfterGameActivity.this.finish();
                }
            });
        }
        else{
            ivNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFx)
                        mp.start();
                    showAlertDialog("Ups!", "Poin kamu masih belum cukup, silahkan coba lagi :)");
                }
            });
            ivNext.setColorFilter(ContextCompat.getColor(AfterGameActivity.this, R.color.shadow));
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AfterGameActivity.this, PackActivity.class);
        setResult(1);
        startActivityForResult(intent, 0);
        finish();
    }

    protected void showAlertDialog(String title, String message) {
        alertDialog = new MaterialDialog.Builder(AfterGameActivity.this)
                .title(title)
                .content(message)
                .positiveText("OK")
                .callback(new MaterialDialog.ButtonCallback() {

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.dismiss();
                    }
                }).build();
        alertDialog.show();
    }
}
