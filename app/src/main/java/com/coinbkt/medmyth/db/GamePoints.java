package com.coinbkt.medmyth.db;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "GAME_POINTS".
 */
@Entity
public class GamePoints {

    @Id(autoincrement = true)
    private Long id;
    private String packName;
    private String result;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public GamePoints() {
    }

    public GamePoints(Long id) {
        this.id = id;
    }

    @Generated
    public GamePoints(Long id, String packName, String result) {
        this.id = id;
        this.packName = packName;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}