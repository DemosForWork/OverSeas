package com.ltt.overseas.http;

import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.UserBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface APIService {

    //Login
    @POST("auth/login")
    Call<GsonUserBean> login(@Body UserBean userParams);

    //Get profile
    @GET("user")
    Call<GsonUserBean> getProfile();

    //Update profile
    @POST("user/update_profile")
    Call<String> modifyProfile(@Body UserBean userParams);

    //Get Country id
    @GET("country/phone_list")
    Call<PhoneListBean> getCountryIds();


//
//    //
//    @PUT("users/changePwd")
//    Call<String> changePwd(@Body UserParams userParams);

//    //
//    @DELETE("address/{addressId}")
//    Call<String> delAdddress(@Path("addressId") String addressId);

}