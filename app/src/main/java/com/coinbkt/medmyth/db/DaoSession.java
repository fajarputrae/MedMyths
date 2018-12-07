package com.coinbkt.medmyth.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.coinbkt.medmyth.db.FMLibrary;
import com.coinbkt.medmyth.db.Packs;
import com.coinbkt.medmyth.db.GamePoints;

import com.coinbkt.medmyth.db.FMLibraryDao;
import com.coinbkt.medmyth.db.PacksDao;
import com.coinbkt.medmyth.db.GamePointsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig fMLibraryDaoConfig;
    private final DaoConfig packsDaoConfig;
    private final DaoConfig gamePointsDaoConfig;

    private final FMLibraryDao fMLibraryDao;
    private final PacksDao packsDao;
    private final GamePointsDao gamePointsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        fMLibraryDaoConfig = daoConfigMap.get(FMLibraryDao.class).clone();
        fMLibraryDaoConfig.initIdentityScope(type);

        packsDaoConfig = daoConfigMap.get(PacksDao.class).clone();
        packsDaoConfig.initIdentityScope(type);

        gamePointsDaoConfig = daoConfigMap.get(GamePointsDao.class).clone();
        gamePointsDaoConfig.initIdentityScope(type);

        fMLibraryDao = new FMLibraryDao(fMLibraryDaoConfig, this);
        packsDao = new PacksDao(packsDaoConfig, this);
        gamePointsDao = new GamePointsDao(gamePointsDaoConfig, this);

        registerDao(FMLibrary.class, fMLibraryDao);
        registerDao(Packs.class, packsDao);
        registerDao(GamePoints.class, gamePointsDao);
    }
    
    public void clear() {
        fMLibraryDaoConfig.clearIdentityScope();
        packsDaoConfig.clearIdentityScope();
        gamePointsDaoConfig.clearIdentityScope();
    }

    public FMLibraryDao getFMLibraryDao() {
        return fMLibraryDao;
    }

    public PacksDao getPacksDao() {
        return packsDao;
    }

    public GamePointsDao getGamePointsDao() {
        return gamePointsDao;
    }

}