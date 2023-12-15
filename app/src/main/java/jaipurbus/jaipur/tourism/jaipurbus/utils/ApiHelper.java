package jaipurbus.jaipur.tourism.jaipurbus.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.DisplayMetrics;

import com.codersworld.jplibs.beans.CommonBean;
import com.codersworld.jplibs.beans.PlacesTypeBean;
import com.codersworld.jplibs.database.DatabaseHelper;
import com.codersworld.jplibs.database.DatabaseManager;
import com.codersworld.jplibs.database.dao.PlaceCategoryDao;
import com.codersworld.jplibs.database.dao.PlacesDao;
import com.codersworld.jplibs.listeners.OnResponse;
import com.codersworld.jplibs.listeners.QueryExecutor;
import com.codersworld.jplibs.rest.ApiCall;
import com.codersworld.jplibs.rest.UniverSelObjct;
import com.codersworld.jplibs.utils.CommonMethods;
import com.codersworld.jplibs.utils.Tags;

import java.util.ArrayList;
import java.util.Locale;

import jaipurbus.jaipur.tourism.jaipurbus.R;

public class ApiHelper implements OnResponse<UniverSelObjct> {
    public Activity mActivity;
    OnResponse<UniverSelObjct> mListener;

    public ApiHelper() {
    }

    String category_id = "";

    public ApiHelper(Activity activity) {
        mActivity = activity;
    }

    public void fetchCategory(Boolean isTrue, OnResponse<UniverSelObjct> mListener) {
        DatabaseManager.initializeInstance(new DatabaseHelper(mActivity));
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        this.mListener = mListener;
        new ApiCall(mActivity).getCategories(this, isTrue);
    }

    public void fetchPlaces(Boolean isTrue, OnResponse<UniverSelObjct> mListener, String strCategory) {
        category_id = strCategory;
        DatabaseManager.initializeInstance(new DatabaseHelper(mActivity));
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        this.mListener = mListener;
        new ApiCall(mActivity).getPlaces(this, isTrue, strCategory);
    }

    //http://194.233.69.10/lms/ajax.php?action=userLogin
    public void hitApi() {
        new ApiCall(mActivity).hitApi();
    }

    @Override
    public void onSuccess(UniverSelObjct response) {
        switch (response.getMethodname()) {
            case Tags.JB_API_CATEGORY:
                CommonBean mCommonBean = (CommonBean) response.getResponse();
                if (mCommonBean != null && CommonMethods.isValidArrayList(mCommonBean.getInfo())) {
                    DatabaseManager.getInstance().executeQuery(new QueryExecutor() {
                        @Override
                        public void run(SQLiteDatabase database) {
                            PlaceCategoryDao dao = new PlaceCategoryDao(database, mActivity);
                            dao.deleteAll();
                            dao.insert(mCommonBean.getInfo());
                            DatabaseManager.getInstance().closeDatabase();
                            mListener.onSuccess(new UniverSelObjct(mCommonBean, Tags.JB_API_CATEGORY, "true", ""));
                        }
                    });
                } else {
                    mListener.onError(Tags.JB_API_CATEGORY, mActivity.getString(R.string.something_wrong));
                }
                break;
            case Tags.JB_API_PLACES:
                CommonBean mCommonBean1 = (CommonBean) response.getResponse();
                if (mCommonBean1 != null && CommonMethods.isValidArrayList(mCommonBean1.getPlaces())) {
                    DatabaseManager.getInstance().executeQuery(new QueryExecutor() {
                        @Override
                        public void run(SQLiteDatabase database) {
                            PlacesDao dao = new PlacesDao(database, mActivity);
                            dao.deleteAll(CommonMethods.isValidString(category_id) ? category_id : "");
                            dao.insert(mCommonBean1.getPlaces());
                            DatabaseManager.getInstance().closeDatabase();
                            mListener.onSuccess(new UniverSelObjct(mCommonBean1, Tags.JB_API_PLACES, "true", ""));
                        }
                    });
                } else {
                    mListener.onError(Tags.JB_API_PLACES, mActivity.getString(R.string.something_wrong));
                }
                break;
        }
    }

    @Override
    public void onError(String type, String error) {
        mListener.onError(Tags.JB_API_CATEGORY, mActivity.getString(R.string.something_wrong));

    }

    public static void setApplicationlanguage(Context mActivity, String strLang) {
        String language = (strLang.equalsIgnoreCase("1")) ? "en" : "hi";
        Resources res = mActivity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= 17) {
            conf.setLocale(new Locale(language));
        } else {
            conf.locale = new Locale(language);
        }
        conf.setLayoutDirection(new Locale(language));
        res.updateConfiguration(conf, dm);
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        Locale.setDefault(locale);
        config.setLayoutDirection(locale);
        config.locale = locale;
        mActivity.getResources().updateConfiguration(config, mActivity.getResources().getDisplayMetrics());
        //LocaleManager.setLanguagePref(mActivity, language);
    }
}
