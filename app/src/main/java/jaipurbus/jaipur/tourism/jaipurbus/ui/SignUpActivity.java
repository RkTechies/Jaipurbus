package jaipurbus.jaipur.tourism.jaipurbus.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codersworld.jplibs.beans.CommonBean;
import com.codersworld.jplibs.beans.StateBean;
import com.codersworld.jplibs.listeners.OnResponse;
import com.codersworld.jplibs.rest.ApiCall;
import com.codersworld.jplibs.rest.UniverSelObjct;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.codersworld.jplibs.utils.Tags;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivityLoginBinding;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivitySignupBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.adapters.StateAdapter;
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.MainActivity;
import jaipurbus.jaipur.tourism.jaipurbus.utils.Global;

public class SignUpActivity extends AppCompatActivity implements OnResponse<UniverSelObjct> {
    ActivitySignupBinding binding;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    String country = "";
    String state = "";
    String city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SignUpActivity.this, R.layout.activity_signup);
        //setContentView(R.layout.activity_login);
        if (getIntent().hasExtra("phone")){
            binding.etPhone.setText(getIntent().getStringExtra("phone"));

        }
        binding.txtVersion.setText(getString(R.string.lbl_version, new Global().getVersionName(SignUpActivity.this)));
        ArrayAdapter mAdapter = ArrayAdapter.createFromResource(SignUpActivity.this, R.array.array_country, R.layout.item_country);
        binding.spCountry.setAdapter(mAdapter);
        ArrayAdapter mAdapter1 = ArrayAdapter.createFromResource(SignUpActivity.this, R.array.array_gender, R.layout.item_country);
        binding.spGender.setAdapter(mAdapter1);
        binding.spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = "";
                if (position == 1) {
                    getStates("101");
                    country = "101";
                    binding.rlState.setVisibility(View.VISIBLE);
                    binding.rlCity.setVisibility(View.GONE);
                } else {
                    country = "";
                    getStates("");
                    binding.rlState.setVisibility(View.GONE);
                    binding.rlCity.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    if (CommonMethods.isValidArrayList(mListState) && mListState.get(position).getId() > 0) {
                        getCity(mListState.get(position).getId() + "");
                        binding.rlCity.setVisibility(View.VISIBLE);
                    } else {
                        getCity("");
                        binding.rlCity.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    initCity();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = "";
                try {
                    if (CommonMethods.isValidArrayList(mListCity) && mListCity.get(position).getId() > 0) {
                        city = mListCity.get(position).getId() + "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("JB_DEVICE_ID", 0);
                String device_id = prefs.getString("device_id", "").toString();
                String name =binding.etName.getText().toString();
                String phone = binding.etPhone.getText().toString();
                String email = binding.etEmail.getText().toString();
                String gender = (binding.spGender.getSelectedItemPosition()==0)?"Male":((binding.spGender.getSelectedItemPosition()==1)?"Female":"Other");
                if (!CommonMethods.isValidString(name)) {
                    CommonMethods.setError(binding.etName, SignUpActivity.this, getString(R.string.error_fullname), getString(R.string.error_fullname));
                } else if (!CommonMethods.checkPhone(phone)) {
                    CommonMethods.setError(binding.etPhone, SignUpActivity.this, getString(R.string.error_phone), getString(R.string.error_phone));
                } else if (!CommonMethods.checkEmail(email)) {
                    CommonMethods.setError(binding.etEmail, SignUpActivity.this, getString(R.string.error_valid_email), getString(R.string.error_valid_email));
                } else {
                    if (CommonMethods.isValidString(country)){
                        if (!CommonMethods.isValidString(state)){
                            CommonMethods.errorDialog(SignUpActivity.this,getString(R.string.error_select_state),getString(R.string.app_name),getString(R.string.lbl_ok));
                        }else if (!CommonMethods.isValidString(city)){
                            CommonMethods.errorDialog(SignUpActivity.this,getString(R.string.error_select_city),getString(R.string.app_name),getString(R.string.lbl_ok));
                        }else{
                            //@Field(" ") String  ,@Field("") String  ,@Field("") String  ,@Field("") String state,@Field("city") String city,@Field("device_id") String device_id,@Field("fcm_id") String fcm_id
                            new ApiCall(SignUpActivity.this).makeSignup(SignUpActivity.this, true,  phone,email,name,country,state,city, device_id,gender);
                        }
                    }else{
                        new ApiCall(SignUpActivity.this).makeSignup(SignUpActivity.this, true,  phone,email,name,country,state,city, device_id,gender);
                    }
                }
            }
        });
    }

    public void getStates(String strCountry) {
        mListState = new ArrayList<>();
        if (CommonMethods.isValidString(strCountry)) {
            new ApiCall(SignUpActivity.this).getStateCity(SignUpActivity.this, true, strCountry, "");
        } else {
            initStates();
        }
    }

    public void getCity(String strState) {
        state = strState;
        mListCity = new ArrayList<>();
        if (CommonMethods.isValidString(strState)) {
            new ApiCall(SignUpActivity.this).getStateCity(SignUpActivity.this, true, "101", strState);
        } else {
            initCity();
        }
    }

    public void initStates() {
        if (!CommonMethods.isValidArrayList(mListState)) {
            mListState = new ArrayList<>();
        }
        mListState.add(0, new StateBean(0, getString(R.string.lbl_select_state)));
        StateAdapter mAdapterState = new StateAdapter(SignUpActivity.this, mListState);
        binding.spState.setAdapter(mAdapterState);
    }

    public void initCity() {
        if (!CommonMethods.isValidArrayList(mListState)) {
            mListCity = new ArrayList<>();
        }
        mListCity.add(0, new StateBean(0, getString(R.string.lbl_select_city)));
        StateAdapter mAdapterCity = new StateAdapter(SignUpActivity.this, mListCity);
        binding.spCity.setAdapter(mAdapterCity);
    }

    ArrayList<StateBean> mListState = new ArrayList<>();
    ArrayList<StateBean> mListCity = new ArrayList<>();

    @Override
    public void onSuccess(UniverSelObjct response) {
        switch (response.getMethodname()) {
            case Tags.JB_API_STATE:
                CommonBean mCommonBean1 = (CommonBean) response.getResponse();
                if (mCommonBean1 != null && mCommonBean1.getStatus() == 1 && CommonMethods.isValidArrayList(mCommonBean1.getStates())) {
                    mListState = mCommonBean1.getStates();
                }
                initStates();
                break;
            case Tags.JB_API_CITY:
                CommonBean mCommonBean2 = (CommonBean) response.getResponse();
                if (mCommonBean2 != null && mCommonBean2.getStatus() == 1 && CommonMethods.isValidArrayList(mCommonBean2.getStates())) {
                    mListCity = mCommonBean2.getStates();
                }
                initCity();
                break;
            case Tags.JB_API_SIGNUP:
                CommonBean mCommonBean3 = (CommonBean) response.getResponse();
                if (mCommonBean3 != null && mCommonBean3.getStatus() == 1 && mCommonBean3.getUser() !=null) {
                    UserSessions.saveUserInfo(SignUpActivity.this,mCommonBean3.getUser());
                    CommonMethods.moveWithClear(SignUpActivity.this, MainActivity.class);
                    //mListCity = mCommonBean3.getStates();
                }else if (mCommonBean3 !=null && CommonMethods.isValidString(mCommonBean3.getMsg())){
                    CommonMethods.errorDialog(SignUpActivity.this,mCommonBean3.getMsg(),getString(R.string.app_name),getString(R.string.lbl_ok));
                }else{
                    CommonMethods.errorDialog(SignUpActivity.this,getString(R.string.something_wrong),getString(R.string.app_name),getString(R.string.lbl_ok));
                }
                break;
        }
    }

    @Override
    public void onError(String type, String error) {
        switch (type) {
            case Tags.JB_API_STATE:
                initStates();
                break;
            case Tags.JB_API_CITY:
                initCity();
                break;
            case Tags.JB_API_SIGNUP:
                CommonMethods.errorDialog(SignUpActivity.this,getString(R.string.something_wrong),getString(R.string.app_name),getString(R.string.lbl_ok));
                break;
        }
    }

}