package com.ltt.overseas.http;

import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.RequestListBean;
import com.ltt.overseas.model.ResponseListBean;
import com.ltt.overseas.model.SectionListBean;
import com.ltt.overseas.model.TypeListBean;
import com.ltt.overseas.model.UserBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @POST("auth/forget")
    Call<String> forgetPwd(@Body UserBean userParams);

    //GET List all type
    @GET("service/main/list_type")
    Call<TypeListBean> getTypeList();

    //Get Country id
    @GET("service/main/list_section/{type_id}")
    Call<SectionListBean> getSectionList(@Path("type_id") String typeId);

    @GET("service/service_provider/response/list_response")
    Call<ResponseListBean> getResponseList(@Query("page") String page, @Header("Authorization") String authorization);

    @GET("service/user/request")
    Call<RequestListBean> getRequestList(@Query("page") String page, @Header("Authorization") String authorization);

    //Login
    @POST("auth/login")
    Call<UserBean> loginTest(@Body UserBean userParams);

    //
    //    //
    //    @PUT("users/changePwd")
    //    Call<String> changePwd(@Body UserParams userParams);

    //    //
    //    @DELETE("address/{addressId}")
    //    Call<String> delAdddress(@Path("addressId") String addressId);

}