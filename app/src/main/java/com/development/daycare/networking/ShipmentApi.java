package com.development.daycare.networking;

import com.development.daycare.model.addBanner.AddBannerRequest;
import com.development.daycare.model.addBanner.AddBannerResponse;
import com.development.daycare.model.addBanner.BannerListResponse;
import com.development.daycare.model.addCareActivity.ActivityListResponse;
import com.development.daycare.model.addCareActivity.AddActivityRequest;
import com.development.daycare.model.addCareActivity.AddActivityResponse;
import com.development.daycare.model.addDay.AddCareRequest;
import com.development.daycare.model.addDay.AddCareResponse;
import com.development.daycare.model.forgotModel.ForgotRequestModel;
import com.development.daycare.model.homeModel.HomeResponse;
import com.development.daycare.model.loginModel.LoginRequest;
import com.development.daycare.model.loginModel.LoginResponse;
import com.development.daycare.model.partnerprofile.ProfileData;
import com.development.daycare.model.partnerprofile.ProfileResponse;
import com.development.daycare.model.showCareModel.ShowCareApiResponse;
import com.development.daycare.model.showCareModel.ShowCareResponse;
import com.development.daycare.model.signupModel.SignUpResponse;
import com.development.daycare.model.signupModel.UserSignupRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShipmentApi {

    @GET("getAllHomePageDataAPI")
    Call<HomeResponse> getData(
            @Query("type") String  type);

    @GET("getDayCareProfileData")
    Call<ProfileResponse> getProfile(
            @HeaderMap Map<String,String> headers);

    @POST("updateUserProfile")
    Call<ProfileResponse> updateProfile(
            @HeaderMap Map<String,String> headers,
            @Body ProfileData request);


    @POST("userSingup")
    Call<SignUpResponse> userSignUp(
            @HeaderMap Map<String,String> headers,
            @Body UserSignupRequest request);

    @POST("userLogin")
    Call<LoginResponse> login(
            @HeaderMap Map<String,String> headers,
            @Body LoginRequest request);

    @POST("forgetPassword")
    Call<LoginResponse> forgotPassword(
            @Body ForgotRequestModel request);


    @POST("postDaycare")
    Call<AddCareResponse> addDayCare(
            @HeaderMap Map<String,String> headers,
            @Body AddCareRequest request);

    @POST("postDaycareBanner")
    Call<AddBannerResponse> addBanner(
            @HeaderMap Map<String,String> headers,
            @Body AddBannerRequest request);

    @POST("postDaycareActivity")
    Call<AddActivityResponse> addActivity(
            @HeaderMap Map<String,String> headers,
            @Body AddActivityRequest request);

    @GET("getDayCareListData")
    Call<ShowCareResponse> getCareList(
            @HeaderMap Map<String,String> headers,
            @Query("offset") String  offset,
            @Query("per_page") String  per_page);

    @GET("getDayCareBannerListData")
    Call<BannerListResponse> getBannerList(
            @HeaderMap Map<String,String> headers,
            @Query("offset") String  offset,
            @Query("per_page") String  per_page,
            @Query("daycare_id") String  id);

    @GET("getDayCareActivityListData")
    Call<ActivityListResponse> getActivityList(
            @HeaderMap Map<String,String> headers,
            @Query("offset") String  offset,
            @Query("per_page") String  per_page,
            @Query("daycare_id") String  id);
}
