package com.coinbkt.medmyth;

import android.app.Application;

import com.coinbkt.medmyth.db.DaoMaster;
import com.coinbkt.medmyth.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MedMythApp extends Application {

    private DaoSession mDaoSession;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "medmyth-db", null);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
