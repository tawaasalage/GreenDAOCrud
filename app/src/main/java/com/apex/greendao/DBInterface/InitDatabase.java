package com.apex.greendao.DBInterface;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.apex.greendao.db.DaoMaster;
import com.apex.greendao.db.DaoSession;

public class InitDatabase {

    DaoSession daoSession;
    DaoMaster daoMaster;
    DaoMaster.DevOpenHelper helper;
    SQLiteDatabase db;

    public DaoSession getSession(Context context)
    {
        helper = new DaoMaster.DevOpenHelper(context, "user-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        return daoSession;
    }

}
