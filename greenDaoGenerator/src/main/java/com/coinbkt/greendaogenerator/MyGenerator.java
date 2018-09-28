package com.coinbkt.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(7, "com.coinbkt.medmyth.db");
        schema.enableKeepSectionsByDefault();

        addFMLibrary(schema);

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
        fmlibrary.setDbName("FMLibrary");
        fmlibrary.addLongProperty("id").primaryKey().autoincrement();
        fmlibrary.addIntProperty("thumbImg");
        fmlibrary.addIntProperty("img");
        fmlibrary.addStringProperty("category");
        fmlibrary.addStringProperty("title");
        fmlibrary.addStringProperty("desc");
    }

}
