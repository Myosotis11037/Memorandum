package com.linya.memorandum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.linya.memorandum.logic.model.LoginResponse
import com.linya.memorandum.logic.network.LoginService
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        this.title = "我的备忘录"

        findViewById<Button>(R.id.login_btn).setOnClickListener(this)

    }



    override fun onClick(v: View?){
        when(v?.id){
            //点击登录按钮
            R.id.login_btn -> {
                val username = findViewById<EditText>(R.id.loginAccount).text
                val password = findViewById<EditText>(R.id.loginPassword).text


            }
        }
    }

}