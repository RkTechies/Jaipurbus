package com.codersworld.jplibs.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codersworld.jplibs.beans.BusRoutesBean;
import com.codersworld.jplibs.beans.CommonBean;
import com.codersworld.jplibs.beans.PlacesTypeBean;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;

import java.util.ArrayList;

public class PlaceCategoryDao {

    private static final String TABLE_BUS_ROUTES = "jpb_place_category";
    // Contacts Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CAT_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_BG_COLOR = "background_color";
    private static final String COLUMN_LANG_ID = "lang_id";
    private static final String COLUMN_TITLE = "title";

    private SQLiteDatabase mDatabase;
    private Context mContext;

    public PlaceCategoryDao(SQLiteDatabase database, Context context) {
        mDatabase = database;
        mContext = context;
    }

    public static String getCreateTable() {
        String CREATE_WORK_DETAILS_TABLE = "CREATE TABLE " + TABLE_BUS_ROUTES
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CAT_ID + " TEXT ,"
                + COLUMN_IMAGE + " TEXT ,"
                + COLUMN_TITLE + " TEXT ,"
                + COLUMN_LANG_ID + " TEXT ,"
                + COLUMN_BG_COLOR + " TEXT "
                + ")";
        return CREATE_WORK_DETAILS_TABLE;
    }

    public static String getDropTable() {
        return "DROP TABLE IF EXISTS " + TABLE_BUS_ROUTES;
    }

    public void deleteAll() {
        String delete_all_users = " DELETE "
                + " FROM "
                + TABLE_BUS_ROUTES;
        mDatabase.execSQL(delete_all_users);
    }

    public void insert(ArrayList<PlacesTypeBean> workArrayList) {
        for (PlacesTypeBean address : workArrayList) {
            String[] bindArgs = {
                    address.getId(),
                    address.getImage(),
                    address.getTitle(),
                    address.getLang_id(),
                    address.getBackground_color()
            };
            String insertUser = " INSERT INTO "
                    + TABLE_BUS_ROUTES
                    + " ( "
                    + COLUMN_CAT_ID
                    + " , "
                    + COLUMN_IMAGE
                    + " , "
                    + COLUMN_TITLE
                    + " , "
                    + COLUMN_LANG_ID
                    + " , "
                    + COLUMN_BG_COLOR
                    + " ) "
                    + " VALUES "
                    + " (?,?,?,?,?)";

            mDatabase.execSQL(insertUser, bindArgs);
        }
    }

    public ArrayList<PlacesTypeBean> selectAll() {
        String lang = new UserSessions(mContext).getLanguage(mContext);
        String getAllWorkDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_BUS_ROUTES;
        if (CommonMethods.isValidString(lang)){
            getAllWorkDetails +=" where lang_id="+lang;
        }
        Cursor cursor = mDatabase.rawQuery(getAllWorkDetails, null);
        ArrayList<PlacesTypeBean> dataList = manageCursor(cursor);
        closeCursor(cursor);
        return dataList;
    }

    protected PlacesTypeBean cursorToData(Cursor cursor) {
        PlacesTypeBean work = new PlacesTypeBean();
        work.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        work.setId(cursor.getString(cursor.getColumnIndex(COLUMN_CAT_ID)));
        work.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
        work.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        work.setLang_id(cursor.getString(cursor.getColumnIndex(COLUMN_LANG_ID)));
        work.setBackground_color(cursor.getString(cursor.getColumnIndex(COLUMN_BG_COLOR)));
        return work;
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    protected ArrayList<PlacesTypeBean> manageCursor(Cursor cursor) {
        ArrayList<PlacesTypeBean> dataList = new ArrayList<PlacesTypeBean>();
        //DbBillAbleUnder model=null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PlacesTypeBean model = cursorToData(cursor);
                if (model != null) {
                    dataList.add(model);
                }
                cursor.moveToNext();
            }
        }
        return dataList;
    }
}
