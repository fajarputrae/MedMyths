package com.coinbkt.medmyth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coinbkt.medmyth.adapter.CardAdapter;
import com.coinbkt.medmyth.db.DaoSession;
import com.coinbkt.medmyth.db.FMLibrary;
import com.coinbkt.medmyth.db.FMLibraryDao;
import com.coinbkt.medmyth.db.GamePoints;
import com.coinbkt.medmyth.db.Packs;
import com.coinbkt.medmyth.db.PacksDao;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    CardAdapter adapter;

    MedMythApp medMythApp;
    DaoSession daoSession;
    List<FMLibrary> fmLibraryList;
    List<String> gameResult = new ArrayList<String>(4);
    Packs packs, packsNext;
    @BindView(R.id.activity_main_card_stack_view)
    CardStackView cardStackView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    String packName, swipeDirection;
    int idPack, point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        Intent intent = new Intent(getIntent());
        packName = intent.getStringExtra("name");
        idPack = intent.getIntExtra("idPack",1);

        medMythApp = (MedMythApp) getApplication();
        daoSession = medMythApp.getDaoSession();
        fmLibraryList = daoSession.getFMLibraryDao().queryBuilder().where(FMLibraryDao.Properties.PackName.eq(packName)).list();
        adapter = new CardAdapter(this);
        adapter.addAll(fmLibraryList);

        cardStackView.setAdapter(adapter);
        tvTitle.setText(packName);

        setup();
    }

    private void setup() {

        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                Log.d("CardStackView", "onCardDragging");
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                swipeDirection = direction.toString();

                if(fmLibraryList.get(cardStackView.getTopIndex()-1).getStatus().equals(swipeDirection))
                    point+=20;
                else
                    point+=0;

                    gameResult.add(swipeDirection);

                    GamePoints gamePoints = new GamePoints();
                    gamePoints.setPackName("Food");
                    gamePoints.setResult(direction.toString());
                    daoSession.getGamePointsDao().insert(gamePoints);

                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                Log.d("CardStackView", "topIndex: " + cardStackView.getTopIndex());
                Log.d("Adapter Count", "getCount: " + adapter.getCount());

                if (cardStackView.getTopIndex() == adapter.getCount()) {

                    packs = daoSession.getPacksDao().queryBuilder().where(PacksDao.Properties.PackName.eq(packName)).unique();
                    packsNext = daoSession.getPacksDao().queryBuilder().where(PacksDao.Properties.IdPack.eq(idPack+1)).unique();

                    packs.setIdPack(packs.getIdPack());
                    packs.setPackName(packs.getPackName());
                    packs.setPackImage(packs.getPackImage());
                    packs.setPackStatus(packs.getPackStatus());
                    packs.setPackPoints(point);

                    packsNext.setIdPack(packsNext.getIdPack());
                    packsNext.setPackName(packsNext.getPackName());
                    packsNext.setPackImage(packsNext.getPackImage());
                    if(point>=80)
                        packsNext.setPackStatus("Unlocked");
                    else
                        packsNext.setPackStatus("Locked");
                    packsNext.setPackPoints(packsNext.getPackPoints());

                    daoSession.getPacksDao().insertOrReplace(packs);
                    daoSession.getPacksDao().insertOrReplace(packsNext);

                    Log.d("CardStackView", "Paginate: " + cardStackView.getTopIndex());

                    Intent intent = new Intent(GameActivity.this, AfterGameActivity.class);
                    intent.putStringArrayListExtra("gameResult", (ArrayList<String>) gameResult);
                    intent.putExtra("point", point);
                    intent.putExtra("name", packName);


                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });
    }
}
