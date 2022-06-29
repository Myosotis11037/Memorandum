package com.linya.memorandum.logic.network

import com.linya.memorandum.logic.model.LoginResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST("v1/android/sign")
    fun getLoginResponse(@Query("name") name: String, @Query("password") password: String): Call<LoginResponse>

}