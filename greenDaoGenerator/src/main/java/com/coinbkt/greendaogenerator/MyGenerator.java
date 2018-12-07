package com.coinbkt.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(14, "com.coinbkt.medmyth.db");
        schema.enableKeepSectionsByDefault();

        addFMLibrary(schema);
        addPacks(schema);
        addGamePoints(schema);

        //new DaoGenerator().generateAll(schema, args[0]);
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addFMLibrary(Schema schema){
        Entity fmlibrary = schema.addEntity("FMLibrary");
        fmlibrary.setHasKeepSections(true);
        fmlibrary.addLongProperty("id").primaryKey().autoincrement();
        fmlibrary.addIntProperty("thumbImg");
        fmlibrary.addIntProperty("img");
        fmlibrary.addStringProperty("packName");
        fmlibrary.addStringProperty("category");
        fmlibrary.addStringProperty("title");
        fmlibrary.addStringProperty("desc");
        fmlibrary.addStringProperty("status");
    }

    private static void addPacks(Schema schema){
        Entity fmlibrary = schema.addEntity("Packs");
        fmlibrary.setHasKeepSections(true);
        fmlibrary.addLongProperty("id").primaryKey().autoincrement();
        fmlibrary.addIntProperty("idPack");
        fmlibrary.addStringProperty("packName");
        fmlibrary.addIntProperty("packPoints");
        fmlibrary.addIntProperty("packImage");
        fmlibrary.addStringProperty("packStatus");
    }

    private static void addGamePoints(Schema schema){
        Entity fmlibrary = schema.addEntity("GamePoints");
        fmlibrary.setHasKeepSections(true);
        fmlibrary.addLongProperty("id").primaryKey().autoincrement();
        fmlibrary.addStringProperty("packName");
        fmlibrary.addStringProperty("result");
    }

}
