package com.example.ess_jetpack.network

import com.example.ess_jetpack.model.LoginRequest
import com.example.ess_jetpack.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

}