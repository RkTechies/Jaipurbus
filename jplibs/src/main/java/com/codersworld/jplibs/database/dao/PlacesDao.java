package com.codersworld.jplibs.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PlacesDao {

    private static final String TABLE_BUS_ROUTES = "jpb_places";
    // Contacts Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PLACE_ID = "id";
    private static final String COLUMN_CAT_ID = "category_id";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_WEBSITE = "website";
    private static final String COLUMN_STAR = "star";
    private static final String COLUMN_LAT = "lat";
    private static final String COLUMN_LNG = "lng";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_LANG_ID = "lang_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_GALLERY = "gallery";

    private SQLiteDatabase mDatabase;
    private Context mContext;

    public PlacesDao(SQLiteDatabase database, Context context) {
        mDatabase = database;
        mContext = context;
    }

    public static String getCreateTable() {
        String CREATE_WORK_DETAILS_TABLE = "CREATE TABLE " + TABLE_BUS_ROUTES
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PLACE_ID + " TEXT ,"
                + COLUMN_CAT_ID + " TEXT ,"
                + COLUMN_PHONE + " TEXT ,"
                + COLUMN_EMAIL + " TEXT ,"
                + COLUMN_WEBSITE + " TEXT ,"
                + COLUMN_STAR + " TEXT ,"
                + COLUMN_LAT + " TEXT ,"
                + COLUMN_LNG + " TEXT ,"
                + COLUMN_IMAGE + " TEXT ,"
                + COLUMN_TITLE + " TEXT ,"
                + COLUMN_LANG_ID + " TEXT ,"
                + COLUMN_DESCRIPTION + " TEXT ,"
                + COLUMN_ADDRESS + " TEXT ,"
                + COLUMN_GALLERY + " TEXT "
                + ")";
        return CREATE_WORK_DETAILS_TABLE;
    }

    public static String getDropTable() {
        return "DROP TABLE IF EXISTS " + TABLE_BUS_ROUTES;
    }

    public void deleteAll(String category_id) {
        String delete_all_users = " DELETE "
                + " FROM "
                + TABLE_BUS_ROUTES;
        if (CommonMethods.isValidString(category_id)){
            delete_all_users +=" where category_id = "+category_id;
        }
        mDatabase.execSQL(delete_all_users);
    }

    public void insert(ArrayList<PlacesBean> placeList) {
        for (PlacesBean mBn : placeList) {
            String[] bindArgs = {
                    mBn.getId(),
                    mBn.getCategory_id(),
                    (CommonMethods.isValidString(mBn.getPhone()))?mBn.getPhone():"",
                    (CommonMethods.isValidString(mBn.getEmail()))?mBn.getEmail():"",
                    (CommonMethods.isValidString(mBn.getWebsite()))?mBn.getWebsite():"",
                    (CommonMethods.isValidString(mBn.getStar()))?mBn.getStar():"",
                    (CommonMethods.isValidString(mBn.getLat()))?mBn.getLat():"",
                    (CommonMethods.isValidString(mBn.getLng()))?mBn.getLng():"",
                    (CommonMethods.isValidString(mBn.getImage()))?mBn.getImage():"",
                    (CommonMethods.isValidString(mBn.getLang_id()))?mBn.getLang_id():"1",
                    (CommonMethods.isValidString(mBn.getTitle()))?mBn.getTitle():"",
                    (CommonMethods.isValidString(mBn.getDescription()))?mBn.getDescription():"",
                    (CommonMethods.isValidString(mBn.getAddress()))?mBn.getAddress():"",
                    (CommonMethods.isValidArrayList(mBn.getGallery()))?new Gson().toJson(mBn.getGallery()) :""
             };
            String insertUser = " INSERT INTO "
                    + TABLE_BUS_ROUTES
                    + " ( "
                    + COLUMN_PLACE_ID
                    + " , "
                    + COLUMN_CAT_ID
                    + " , "
                    + COLUMN_PHONE
                    + " , "
                    + COLUMN_EMAIL
                    + " , "
                    + COLUMN_WEBSITE
                    + " , "
                    + COLUMN_STAR
                    + " , "
                    + COLUMN_LAT
                    + " , "
                    + COLUMN_LNG
                    + " , "
                    + COLUMN_IMAGE
                    + " , "
                    + COLUMN_LANG_ID
                    + " , "
                    + COLUMN_TITLE
                    + " , "
                    + COLUMN_DESCRIPTION
                    + " , "
                    + COLUMN_ADDRESS
                    + " , "
                    + COLUMN_GALLERY
                    + " ) "
                    + " VALUES "
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            mDatabase.execSQL(insertUser, bindArgs);
        }
    }

    public ArrayList<PlacesBean> selectAll(String category_id,String place_id) {
        String lang = new UserSessions(mContext).getLanguage(mContext);
        String getAllWorkDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_BUS_ROUTES +" where 1=1";
        if (CommonMethods.isValidString(category_id)){
            getAllWorkDetails +=" AND category_id="+category_id;
        }
        if (CommonMethods.isValidString(place_id)){
            getAllWorkDetails +=" AND id="+place_id;
        }
        if (CommonMethods.isValidString(lang)){
            getAllWorkDetails +=" AND lang_id="+lang;
        }
        Cursor cursor = mDatabase.rawQuery(getAllWorkDetails, null);
        ArrayList<PlacesBean> dataList = manageCursor(cursor);
        closeCursor(cursor);
        return dataList;
    }

    protected PlacesBean cursorToData(Cursor cursor) {
        PlacesBean work = new PlacesBean();
        work.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        work.setId(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE_ID)));
        work.setCategory_id(cursor.getString(cursor.getColumnIndex(COLUMN_CAT_ID)));
        work.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
        work.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
        work.setWebsite(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)));
        work.setStar(cursor.getString(cursor.getColumnIndex(COLUMN_STAR)));
        work.setLat(cursor.getString(cursor.getColumnIndex(COLUMN_LAT)));
        work.setLng(cursor.getString(cursor.getColumnIndex(COLUMN_LNG)));
        work.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
        work.setLang_id(cursor.getString(cursor.getColumnIndex(COLUMN_LANG_ID)));
        work.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        work.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        work.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
        String gallery = cursor.getString(cursor.getColumnIndex(COLUMN_GALLERY));
        if (CommonMethods.isValidString(gallery)){
         work.setGallery(new Gson().fromJson(gallery, new TypeToken<ArrayList<String>>(){}.getType()));
        }else {
            work.setGallery(new ArrayList<>());
        }
        return work;
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    protected ArrayList<PlacesBean> manageCursor(Cursor cursor) {
        ArrayList<PlacesBean> dataList = new ArrayList<PlacesBean>();
        //DbBillAbleUnder model=null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PlacesBean model = cursorToData(cursor);
                if (model != null) {
                    dataList.add(model);
                }
                cursor.moveToNext();
            }
        }
        return dataList;
    }
}
