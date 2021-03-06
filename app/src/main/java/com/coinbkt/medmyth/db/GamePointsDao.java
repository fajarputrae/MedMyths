package com.coinbkt.medmyth.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GAME_POINTS".
*/
public class GamePointsDao extends AbstractDao<GamePoints, Long> {

    public static final String TABLENAME = "GAME_POINTS";

    /**
     * Properties of entity GamePoints.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property PackName = new Property(1, String.class, "packName", false, "PACK_NAME");
        public final static Property Result = new Property(2, String.class, "result", false, "RESULT");
    }


    public GamePointsDao(DaoConfig config) {
        super(config);
    }
    
    public GamePointsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GAME_POINTS\" (" + //
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PACK_NAME\" TEXT," + // 1: packName
                "\"RESULT\" TEXT);"); // 2: result
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GAME_POINTS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GamePoints entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String packName = entity.getPackName();
        if (packName != null) {
            stmt.bindString(2, packName);
        }
 
        String result = entity.getResult();
        if (result != null) {
            stmt.bindString(3, result);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GamePoints entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String packName = entity.getPackName();
        if (packName != null) {
            stmt.bindString(2, packName);
        }
 
        String result = entity.getResult();
        if (result != null) {
            stmt.bindString(3, result);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GamePoints readEntity(Cursor cursor, int offset) {
        GamePoints entity = new GamePoints( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // packName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // result
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GamePoints entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPackName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setResult(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GamePoints entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GamePoints entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GamePoints entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
