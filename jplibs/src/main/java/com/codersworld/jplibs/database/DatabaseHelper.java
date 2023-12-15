package com.codersworld.jplibs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codersworld.jplibs.database.dao.BusRoutesDao;
import com.codersworld.jplibs.database.dao.PlaceCategoryDao;
import com.codersworld.jplibs.database.dao.PlacesDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "jaipurbus_2023.db";
    public static final int DATABASE_VERSION = 3;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create all tables
        sqLiteDatabase.execSQL(BusRoutesDao.getCreateTable());
        sqLiteDatabase.execSQL(BusRoutesDao.getCreateTableHindi());
        sqLiteDatabase.execSQL(PlaceCategoryDao.getCreateTable());
        sqLiteDatabase.execSQL(PlacesDao.getCreateTable());
    }

    public void drop(SQLiteDatabase sqLiteDatabase) {
        // drop all tables
        sqLiteDatabase.execSQL(BusRoutesDao.getDropTable());
        sqLiteDatabase.execSQL(BusRoutesDao.getDropTableHindi());
        sqLiteDatabase.execSQL(PlaceCategoryDao.getDropTable());
        sqLiteDatabase.execSQL(PlacesDao.getDropTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion >= oldVersion) {
            drop(sqLiteDatabase);
            onCreate(sqLiteDatabase);
        }
    }
}
