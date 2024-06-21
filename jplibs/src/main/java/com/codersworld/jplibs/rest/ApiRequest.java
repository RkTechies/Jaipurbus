package com.codersworld.jplibs.rest;


import com.codersworld.jplibs.beans.CommonBean;
import com.codersworld.jplibs.utils.Tags;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRequest {

    @POST(Tags.JB_API_CATEGORY)
    Call<CommonBean> getCategories();

    @POST(Tags.JB_API_SUPPORT)
    @FormUrlEncoded
    Call<CommonBean> supportTicket(@Field("name") String name,@Field("email") String email,@Field("msg") String msg,@Field("lang") String lang);

    @POST(Tags.JB_API_SAVE_TOKEN)
    @FormUrlEncoded
    Call<CommonBean> saveFCM(@Field("token") String token,@Field("device_id") String device_id);

    @POST(Tags.JB_API_PLACES)
    @FormUrlEncoded
    Call<CommonBean> getPlaces(@Field("cat_id") String strCategory);
    @POST(Tags.JB_API_STATE)
    @FormUrlEncoded
    Call<CommonBean> getState(@Field("country") String country_id);
    @POST(Tags.JB_API_CITY)
    @FormUrlEncoded
    Call<CommonBean> getCity(@Field("country") String country_id,@Field("state") String state);

    @FormUrlEncoded
    @POST(Tags.JB_API_LOGIN)
    Call<CommonBean> makeLogin(@Field("phone") String strPhone,@Field("device_id") String device_id,@Field("fcm_id") String fcm_id);

    @FormUrlEncoded
    @POST(Tags.JB_API_SIGNUP)
    Call<CommonBean> makeSignup(@Field("phone") String strPhone,@Field("email") String email,@Field("name") String name,@Field("country") String country,@Field("state") String state,@Field("city") String city,@Field("device_id") String device_id,@Field("fcm_id") String fcm_id,@Field("gender") String gender);

    @FormUrlEncoded
    @POST("ajax.php?action=userLogin")
    Call<String> hitApi(@Field("contact_no") String strPhone);

}
