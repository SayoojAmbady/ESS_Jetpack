package com.example.ess_jetpack.model

data class LoginResponse(

    val customerid: String?,
    val customername: String?,
    val custlang: String?,
    val uniquesessid: String?,
    val requestid: String?,
    val status: String?,
    val result: String?,
    val mpinstatus: String?,
    val encr_uid: String?,
    val encr_pass: String?,
    val captcha_status: String?,
    val captcha: String?

)