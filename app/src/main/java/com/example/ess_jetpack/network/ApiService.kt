package com.example.ess_jetpack.network

import com.example.ess_jetpack.model.LoginRequest
import com.example.ess_jetpack.model.LoginResponse
import com.example.ess_jetpack.model.OtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    // STEP 1 : LOGIN
    @FormUrlEncoded
    @POST("mLoginEncrpt_vapt")
    suspend fun login(
        @Field("uid") uid: String,
        @Field("pass") pass: String,
        @Field("langId") langId: String
    ): Response<LoginResponse>

    // STEP 2 : SEND OTP
    @FormUrlEncoded
    @POST("Method_Customer_Login_Otp_new")
    suspend fun sendOtp(
        @Field("cusid") cusid: String,
        @Field("uniqueSessionId") uniqueSessionId: String,
        @Field("requestId") requestId: String,
        @Field("sendOTP") sendOTP: String,
        @Field("langId") langId: String,
        @Field("sendbtn") sendbtn: String
    ): Response<OtpResponse>

}