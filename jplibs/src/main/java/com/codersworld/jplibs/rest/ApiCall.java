package com.codersworld.jplibs.rest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.codersworld.jplibs.R;
import com.codersworld.jplibs.beans.CommonBean;
import com.codersworld.jplibs.beans.PlacesTypeBean;
import com.codersworld.jplibs.listeners.OnResponse;
import com.codersworld.jplibs.utils.CommonMethods;
import com.codersworld.jplibs.utils.SFProgress;
import com.codersworld.jplibs.utils.Tags;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {
    public Activity mContext = null;

    public ApiCall(Context applicationContext) {
    }

    public ApiCall(Activity ctx) {
        this.mContext = ctx;
    }

    public void supportTicket(OnResponse<UniverSelObjct> onResponse,Boolean isTrue,String... params) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.supportTicket(params[0],params[1],params[2],params[3]).enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), Tags.JB_API_SUPPORT, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Tags.JB_API_SUPPORT, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Tags.JB_API_SUPPORT, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError(Tags.JB_API_SUPPORT, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void saveFCM(OnResponse<UniverSelObjct> onResponse,Boolean isTrue,String... params) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.saveFCM(params[0], CommonMethods.getIMEI(mContext)).enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), Tags.JB_API_SAVE_TOKEN, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Tags.JB_API_SAVE_TOKEN, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Tags.JB_API_SAVE_TOKEN, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError(Tags.JB_API_SAVE_TOKEN, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void getCategories(OnResponse<UniverSelObjct> onResponse,Boolean isTrue) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.getCategories().enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), Tags.JB_API_CATEGORY, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Tags.JB_API_CATEGORY, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Tags.JB_API_CATEGORY, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError(Tags.JB_API_CATEGORY, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void getPlaces(OnResponse<UniverSelObjct> onResponse,Boolean isTrue,String strCategory) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.getPlaces(strCategory).enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), Tags.JB_API_PLACES, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Tags.JB_API_PLACES, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Tags.JB_API_PLACES, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError(Tags.JB_API_PLACES, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void makeLogin(OnResponse<UniverSelObjct> onResponse,Boolean isTrue,String strPhone,String strFcm) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.makeLogin(strPhone, CommonMethods.getIMEI(mContext), strFcm).enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), Tags.JB_API_LOGIN, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Tags.JB_API_LOGIN, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Tags.JB_API_LOGIN, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError(Tags.JB_API_LOGIN, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void makeSignup(OnResponse<UniverSelObjct> onResponse,Boolean isTrue,String... params) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.makeSignup(params[0],params[1],params[2],params[3],params[4],params[5], CommonMethods.getIMEI(mContext), params[6]).enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), Tags.JB_API_SIGNUP, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Tags.JB_API_SIGNUP, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Tags.JB_API_SIGNUP, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError(Tags.JB_API_SIGNUP, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void getStateCity(OnResponse<UniverSelObjct> onResponse,Boolean isTrue,String country_id,String state_id) {
        if (isTrue) {
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        Call<CommonBean> mCall = mRequest.getState(country_id);
        if (CommonMethods.isValidString(state_id)){
            mCall = mRequest.getCity(country_id,state_id);
        }
        mCall.enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                          //  CommonBean mBean = new Gson().fromJson(response.body().toString(), CommonBean.class);
                            onResponse.onSuccess(new UniverSelObjct(response.body(), (CommonMethods.isValidString(state_id))?Tags.JB_API_CITY:Tags.JB_API_STATE, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError((CommonMethods.isValidString(state_id))?Tags.JB_API_CITY:Tags.JB_API_STATE, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError((CommonMethods.isValidString(state_id))?Tags.JB_API_CITY:Tags.JB_API_STATE, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {
                if (isTrue) {
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                t.printStackTrace();
                onResponse.onError((CommonMethods.isValidString(state_id))?Tags.JB_API_CITY:Tags.JB_API_STATE, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }
    public void hitApi() {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(2, 2).create(ApiRequest.class);
        Call<String> mCall = mRequest.hitApi("9782300124");
        mCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("success otp","success otp");
                hitApi();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Log.e("failed otp","failed otp");
                hitApi();
            }
        });
    }

}
