//package com.linya.memorandum.logic
//
//import androidx.lifecycle.liveData
//import com.linya.memorandum.logic.network.LoginNetwork
//import kotlinx.coroutines.Dispatchers
//import kotlin.coroutines.CoroutineContext
//
//object Repository {
//    fun getLogin(name: String, password : String) = fire(Dispatchers.IO){
//        val loginResponse = LoginNetwork.getLoginResponse(name, password)
//        val code = loginResponse.code
//        if(code == 200 || code == -100 || code == -1001){
//            Result.success(loginResponse)
//        }else{
//            Result.failure(
//                RuntimeException(
//                    "response code is ${loginResponse.code}"
//                )
//            )
//        }
//    }
//
//    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
//        liveData<Result<T>>(context) {
//            val result = try {
//                block()
//            } catch (e: Exception) {
//                Result.failure<T>(e)
//            }
//            emit(result)
//        }
//}