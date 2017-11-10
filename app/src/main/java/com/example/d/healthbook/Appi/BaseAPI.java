package com.example.d.healthbook.Appi;


import com.example.d.healthbook.Models.LoginResponseModel;
import com.example.d.healthbook.Models.RegisterResponse;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.Models.ResponseAlldataOfMemer;
import com.example.d.healthbook.Models.ResponseCategoryProtocol;
import com.example.d.healthbook.Models.ResponseChannel;
import com.example.d.healthbook.Models.ResponseClinicInfo;
import com.example.d.healthbook.Models.ResponseClinicInfo2;
import com.example.d.healthbook.Models.ResponseClinicList;
import com.example.d.healthbook.Models.ResponseCloseOrOpenVisit;
import com.example.d.healthbook.Models.ResponseDeleteMemberData;
import com.example.d.healthbook.Models.ResponseDeleteNote;
import com.example.d.healthbook.Models.ResponseDiseaseCategory;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.Models.ResponseDoctorList;
import com.example.d.healthbook.Models.ResponseDrugsCategory;
import com.example.d.healthbook.Models.ResponseEditUserProfile;
import com.example.d.healthbook.Models.ResponseFeedList;
import com.example.d.healthbook.Models.ResponseGetUserData;
import com.example.d.healthbook.Models.ResponseLibraryProtocol;
import com.example.d.healthbook.Models.ResponseMyFamilyMemberCreate;
import com.example.d.healthbook.Models.ResponseMyFamilyMembers;
import com.example.d.healthbook.Models.ResponseNoteType1;
import com.example.d.healthbook.Models.ResponseProgressUser;
import com.example.d.healthbook.Models.ResponseSubscribe;
import com.example.d.healthbook.Models.ResponseSubscribeToDoctor;
import com.example.d.healthbook.Models.ResponseVisit;
import com.example.d.healthbook.Models.VisitResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseAPI {

    @Headers("Content-Type: application/json")
    @POST("accounts/login")
    public Call<LoginResponseModel> loginUser(@Body JsonObject dataToSend);

    @Headers("Content-Type: application/json")
    @POST("invite/invite")
    public Call<RegisterResponse> registerUser(@Body JsonObject dataToSend);

    @POST("booking/expert/search")
    public Call<ResponseDoctorList> seeDoctorList(@Query("city_id") String city_id);

    @POST("content/subscriber/subscribe")
    public Call<ResponseSubscribe> subscribe(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);


    @GET("content/library/protocolCategory")
    public Call<ResponseLibraryProtocol> seeLibraryProtocol();

    @GET("booking/visit/next")
    public Call<List<ResponseVisit>> seeVisit(@Header("auth-token") String auth_token);

    @GET("content/channel")
    public Call<ResponseChannel> seeChannel();

    @GET("content/channel")
    public Call<ResponseChannel> addChannel(@Query("page") Integer page);

    @GET("content/library/drugCategory")
    public Call<ResponseLibraryProtocol> seeLibraryDrugs();

    @GET("content/library/diseaseCategory")
    public Call<ResponseLibraryProtocol> seeLibraryDisease();

    @GET("content/library/protocol")
    public Call<ResponseCategoryProtocol> seeProtocolCategory(@Query("category_id") String category_id);

    @GET("content/library/drug")
    public Call<ResponseDrugsCategory> seeDrugsCategory(@Query("category_id") String category_id);

    @GET("content/library/disease")
    public Call<ResponseDiseaseCategory> seeDiseaseCategory(@Query("category_id") String category_id);

    @GET("content/post")
    public Call<ResponseDoctorList> seeNewsList();

    @GET("content/post")
    public Call<ResponseDoctorList> addNewsList(@Query("page") Integer page);


    @GET("content/feed")
    public Call<ResponseFeedList> seeFeedList(@Header("auth-token") String auth_token, @Query("page") Integer page, @Query("lang") String lang,
                                              @Query("type") String type, @Query("scope") String scope);

    @GET("content/feed")
    public Call<ResponseFeedList> seeFeedList2(@Header("auth-token") String auth_token);


    @GET("booking/expert/search")
    public Call<ResponseDoctorList> seeDoctorListFilter(@Query("city_id") String city_id, @Query("speciality") String speciality,
                                                        @Query("full_name") String full_name);

    @POST("booking/expert/search")
    public Call<ResponseDoctorList> addDoctorList(@Query("page") Integer page, @Query("city_id") Integer city_id);

    @GET("booking/expert/{expert_id}")
    public Call<ResponseDoctorInfo> seedoctorInfo(@Path("expert_id") String user_id);


    @GET("booking/company/{company_id}")
    public Call<ResponseClinicInfo> seeclinicInfo(@Path("company_id") String user_id);

    @GET("booking/companyPoint/{company_id}")
    public Call<ResponseClinicInfo2> seeclinicInfo2(@Path("company_id") String user_id);

    @GET("booking/companyPoint/clinic/search")
    public Call<ResponseClinicList> seeclinicFiltered(@Query("city_id") String city_id, @Query("name") String name);

    @GET("accounts/user/{userId}")
    public Call<ResponseGetUserData> seedUserInfo(@Header("auth-token") String auth_token, @Path("userId") Integer userId);

    @GET("booking/companyPoint")
    public Call<ResponseClinicList> seeClinicList();

    @GET("booking/companyPoint")
    public Call<ResponseClinicList> addClinicList(@Query("page") Integer page);

    @GET("booking/visit/enroll")
    public Call<List<ResponseCloseOrOpenVisit>> visitCheck(@Header("auth-token") String auth_token, @Query("expert_id") String expert_id, @Query("finish_date") String finish_date,
                                                           @Query("start_date") String start_date);


    @Headers("Content-Type: application/json")
    @POST("booking/visit")
    public Call<VisitResponse> visitUser(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);

    @POST("achievements/task/get-progress")
    public Call<ResponseProgressUser> seeUserProgress(@Header("auth-token") String auth_token);

    @Headers("Content-Type: application/json")
    @POST("booking/subscribing/expert/subscribe")
    public Call<ResponseSubscribeToDoctor> subscribeToDoctor(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);

    @Headers("Content-Type: application/json")
    @POST("booking/subscribing/expert/subscribe")
    public Call<ResponseSubscribeToDoctor> unSubscribeToDoctor(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);

    @GET("booking/subscribing/experts")
    public Call<List<ResponseAllSubscriptionsToDoctor>> allUserSubscpriptionsToDoc(@Header("auth-token") String auth_token);

    @Headers("Content-Type: application/json")
    @PUT("accounts/user/{user_id}")
    public Call<ResponseEditUserProfile> editUserProfile(@Path("user_id") Integer user_id, @Header("auth-token") String auth_token, @Body JsonObject dataToSend);

    @Headers("Content-Type: application/json")
    @POST("calendar/action")
    public Call<ResponseNoteType1> saveNoteType1(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);

    @GET("healthbook/diary")
    public Call<ResponseMyFamilyMembers> seeFamilyMembers(@Header("auth-token") String auth_token);

    @GET("healthbook/diary/{diaryId}")
    public Call<ResponseAlldataOfMemer> seeAlldataofmember(@Header("auth-token") String auth_token,
                                                           @Path("diaryId") String diaryId);

    @DELETE("healthbook/diary/{diaryId}")
    public Call<ResponseDeleteMemberData> deleteAlldataofmember(@Header("auth-token") String auth_token,
                                                                @Path("diaryId") String diaryId);


    @GET("calendar/action")
    public Call<List<ResponseNoteType1>> seeNoteType1(@Header("auth-token") String auth_token);

    @DELETE("calendar/action/{actionId}")
    public Call<ResponseDeleteNote> deleteNote(@Header("auth-token") String auth_token, @Path("actionId") String actionId);

    @Headers("Content-Type: application/json")
    @PUT("calendar/action/{actionId}")
    public Call<ResponseNoteType1> updateNote(@Header("auth-token") String auth_token, @Path("actionId") String actionId,
                                              @Body JsonObject dataToSend);


//    @GET("calendar/action")
//    public Call<List<ResponseNoteType1>> seeNoteType1(@Header("auth-token") String auth_token,@Query("type") String type);

    @Headers("Content-Type: application/json")
    @POST("healthbook/diary")
    public Call<ResponseMyFamilyMemberCreate> createFamilyMember(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);

    @Headers("Content-Type: application/json")
    @PUT("healthbook/diary")
    public Call<ResponseMyFamilyMemberCreate> updateFamilyMember(@Header("auth-token") String auth_token, @Body JsonObject dataToSend);


}


