package com.coinbkt.medmyth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.coinbkt.medmyth.adapter.PacksAdapter;
import com.coinbkt.medmyth.db.DaoSession;
import com.coinbkt.medmyth.db.Packs;
import com.coinbkt.medmyth.utils.SPMedmyth;
import com.coinbkt.medmyth.utils.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.backBtn)
    ImageView backButton;

    PacksAdapter adapter;

    MedMythApp medMythApp;
    DaoSession daoSession;
    List<Packs> packsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack);

        ButterKnife.bind(this);

        final Boolean isFx = SPMedmyth.getIsFX(this);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.click);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                PackActivity.this.finish();
            }
        });

        medMythApp = (MedMythApp) getApplication();
        daoSession = medMythApp.getDaoSession();
        packsList = daoSession.getPacksDao().queryBuilder().list();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpacesItemDecoration(30, 2));
        adapter = new PacksAdapter(this, packsList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            packsList.clear();
            packsList.addAll(daoSession.getPacksDao().queryBuilder().list());
            adapter.notifyDataSetChanged();
        }
    }

}
