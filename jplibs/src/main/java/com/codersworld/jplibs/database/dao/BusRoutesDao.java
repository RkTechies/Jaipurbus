package com.codersworld.jplibs.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.codersworld.jplibs.beans.BusRoutesBean;

import java.util.ArrayList;

public class BusRoutesDao {

    private static final String TABLE_BUS_ROUTES = "jpb_routes";
    private static final String TABLE_BUS_ROUTES_HI = "jpb_routes_hi";
    // Contacts Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BUS = "buses";
    private static final String COLUMN_KMS = "kms";

    private SQLiteDatabase mDatabase;
    private Context mContext;

    public BusRoutesDao(SQLiteDatabase database, Context context) {
        mDatabase = database;
        mContext = context;
    }

    public static String getCreateTable() {
        String CREATE_WORK_DETAILS_TABLE = "CREATE TABLE " + TABLE_BUS_ROUTES
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_BUS + " TEXT ,"
                + COLUMN_KMS + " TEXT "
                + ")";
        return CREATE_WORK_DETAILS_TABLE;
    }
    public static String getCreateTableHindi() {
        String CREATE_WORK_DETAILS_TABLE = "CREATE TABLE " + TABLE_BUS_ROUTES_HI
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_BUS + " TEXT ,"
                + COLUMN_KMS + " TEXT "
                + ")";
        return CREATE_WORK_DETAILS_TABLE;
    }

    public static String getDropTable() {
        return "DROP TABLE IF EXISTS " + TABLE_BUS_ROUTES;
    }
    public static String getDropTableHindi() {
        return "DROP TABLE IF EXISTS " + TABLE_BUS_ROUTES_HI;
    }

    public void deleteAll() {
        String delete_all_users = " DELETE "
                + " FROM "
                + TABLE_BUS_ROUTES;
        mDatabase.execSQL(delete_all_users);
        String delete_all_users_hi = " DELETE "
                + " FROM "
                + TABLE_BUS_ROUTES_HI;
        mDatabase.execSQL(delete_all_users_hi);
    }

    public void insert(ArrayList<BusRoutesBean> workArrayList) {
        for (BusRoutesBean address : workArrayList) {
            String[] bindArgs = {
                    address.getBus(),
                    address.getKms()
            };
            String insertUser = " INSERT INTO "
                    + TABLE_BUS_ROUTES
                    + " ( "
                    + COLUMN_BUS
                    + " , "
                    + COLUMN_KMS
                    + " ) "
                    + " VALUES "
                    + " (?,?)";

            mDatabase.execSQL(insertUser, bindArgs);
        }
    }
    public void insertHindi(ArrayList<BusRoutesBean> workArrayList) {
        for (BusRoutesBean address : workArrayList) {
            String[] bindArgs = {
                    address.getBus(),
                    address.getKms()
            };
            String insertUser = " INSERT INTO "
                    + TABLE_BUS_ROUTES_HI
                    + " ( "
                    + COLUMN_BUS
                    + " , "
                    + COLUMN_KMS
                    + " ) "
                    + " VALUES "
                    + " (?,?)";

            mDatabase.execSQL(insertUser, bindArgs);
        }
    }

    public ArrayList<BusRoutesBean> selectAll(String lang) {
        String getAllWorkDetails = " SELECT "
                + " * "
                + " FROM "
                + ((lang.equalsIgnoreCase("1"))?TABLE_BUS_ROUTES:TABLE_BUS_ROUTES_HI);

        Cursor cursor = mDatabase.rawQuery(getAllWorkDetails, null);
        ArrayList<BusRoutesBean> dataList = manageCursor(cursor);
        closeCursor(cursor);
        return dataList;
    }

    protected BusRoutesBean cursorToData(Cursor cursor) {
        BusRoutesBean work = new BusRoutesBean();
        work.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        work.setBus(cursor.getString(cursor.getColumnIndex(COLUMN_BUS)));
        work.setKms(cursor.getString(cursor.getColumnIndex(COLUMN_KMS)));
        return work;
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    protected ArrayList<BusRoutesBean> manageCursor(Cursor cursor) {
        ArrayList<BusRoutesBean> dataList = new ArrayList<BusRoutesBean>();
        //DbBillAbleUnder model=null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                BusRoutesBean model = cursorToData(cursor);
                if (model != null) {
                    dataList.add(model);
                }
                cursor.moveToNext();
            }
        }
        return dataList;
    }
}
