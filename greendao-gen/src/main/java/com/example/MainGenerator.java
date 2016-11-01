package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {


    public static void main(String[] args)  throws Exception {
        //place where db folder will be created inside the project folder
        Schema schema = new Schema(1,"com.apex.greendao.db");

        createTableUser(schema);

        //  ./app/src/main/java/   ----   com/codekrypt/greendao/db is the full path
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }



    public static void createTableUser(Schema schema)
    {
        //Entity i.e. Class to be stored in the database // ie table LOG
        Entity user_entity= schema.addEntity("User");
        user_entity.addIdProperty().autoincrement();
        user_entity.addStringProperty("firstname").notNull();
        user_entity.addStringProperty("surname").notNull();
        user_entity.addStringProperty("nic").notNull().unique();
        user_entity.addStringProperty("age").notNull();
    }

}
