//package com.linya.memorandum.logic.network
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object ServiceCreator {
//    private const val BASE_URL = "http://10.83.130.5:8080/"
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
//
//    inline fun <reified T> create(): T = create(T::class.java)
//}