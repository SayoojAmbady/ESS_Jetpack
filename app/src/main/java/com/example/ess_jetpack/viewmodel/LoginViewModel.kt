package com.example.ess_jetpack.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ess_jetpack.model.LoginRequest
import com.example.ess_jetpack.network.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var isLoading = mutableStateOf(false)
    var loginSuccess = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun login(uid: String, pass: String) {

        viewModelScope.launch {

            try {

                isLoading.value = true

                val response = RetrofitClient.apiService.login(
                    uid,
                    pass,
                    "EN"
                )

                if (response.isSuccessful) {

                    val body = response.body()

                    if (body?.status == "111") {

                        // CALL OTP API
                        sendOtp(
                            body.customerid ?: "",
                            body.uniquesessid ?: "",
                            body.requestid ?: ""
                        )

                    } else {

                        errorMessage.value = body?.result
                    }

                }

            } catch (e: Exception) {

                errorMessage.value = e.message

            } finally {

                isLoading.value = false

            }
        }
    }

    fun sendOtp(
        cusid: String,
        uniqueSessionId: String,
        requestId: String
    ) {

        viewModelScope.launch {

            val response = RetrofitClient.apiService.sendOtp(
                cusid,
                uniqueSessionId,
                requestId,
                "1",
                "EN",
                "1"
            )

            if (response.isSuccessful) {

                if (response.body()?.status == "111") {

                    loginSuccess.value = true

                }

            }

        }
    }
}