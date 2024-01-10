package jaipurbus.jaipur.tourism.jaipurbus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.codersworld.jplibs.beans.CommonBean;
import com.codersworld.jplibs.database.DatabaseManager;
import com.codersworld.jplibs.database.dao.PlaceCategoryDao;
import com.codersworld.jplibs.database.dao.PlacesDao;
import com.codersworld.jplibs.listeners.OnResponse;
import com.codersworld.jplibs.listeners.QueryExecutor;
import com.codersworld.jplibs.rest.ApiCall;
import com.codersworld.jplibs.rest.UniverSelObjct;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.codersworld.jplibs.utils.Tags;
import com.google.gson.Gson;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityLoginBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.MainActivity;
import jaipurbus.jaipur.tourism.jaipurbus.utils.Global;

public class LoginActivity extends BaseActivity implements OnResponse<UniverSelObjct> {
    ActivityLoginBinding binding;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(getCurrentFocus() !=null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        return super.dispatchTouchEvent(ev);
    }
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this,R.layout.activity_login);
        //setContentView(R.layout.activity_login);
        binding.txtVersion.setText(getString(R.string.lbl_version, new Global().getVersionName(LoginActivity.this)));
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs =getSharedPreferences("JB_DEVICE_ID", 0);
                String device_id  = prefs.getString("device_id", "").toString();

                if(!CommonMethods.isValidString(binding.etUsername.getText().toString())){
                    CommonMethods.setError(binding.etUsername,LoginActivity.this,getString(R.string.error_phone_required),getString(R.string.error_phone_required));
                }else if (!CommonMethods.checkPhone(binding.etUsername.getText().toString())){
                    CommonMethods.setError(binding.etUsername,LoginActivity.this,getString(R.string.error_phone),getString(R.string.error_phone));
                }else{
                    new ApiCall(LoginActivity.this).makeLogin(LoginActivity.this, true,binding.etUsername.getText().toString(),device_id);
                }
            }
        });
     }

    @Override
    public void onSuccess(UniverSelObjct response) {
        switch (response.getMethodname()) {
            case Tags.JB_API_LOGIN:
                CommonBean mCommonBean1 = (CommonBean) response.getResponse();
                if (mCommonBean1 != null && mCommonBean1.getStatus()==1) {
                    UserSessions.saveUserInfo(LoginActivity.this,mCommonBean1.getUser());
                    CommonMethods.moveWithClear(LoginActivity.this, MainActivity.class);
                } else if (mCommonBean1 != null && mCommonBean1.getStatus()==2) {
                    startActivity(new Intent(LoginActivity.this,SignUpActivity.class).putExtra("phone",binding.etUsername.getText().toString()));
                }else if (mCommonBean1 !=null && CommonMethods.isValidString(mCommonBean1.getMsg())){
                    CommonMethods.errorDialog(LoginActivity.this,mCommonBean1.getMsg(),getString(R.string.app_name),getString(R.string.lbl_ok));
                }else{
                    CommonMethods.errorDialog(LoginActivity.this,getString(R.string.something_wrong),getString(R.string.app_name),getString(R.string.lbl_ok));
                }
                break;
        }
    }

    @Override
    public void onError(String type, String error) {
    }

 }