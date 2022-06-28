package com.linya.memorandum

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        this.title = "我的备忘录"

        findViewById<Button>(R.id.login_btn).setOnClickListener(this)

    }

    override fun onClick(v: View?){
        when(v?.id){
            R.id.login_btn -> {
                val username = findViewById<EditText>(R.id.loginAccount).text
                val password = findViewById<EditText>(R.id.loginPassword).text

                val okHttpClient = OkHttpClient()
                var url = "http://10.83.130.5:8080/v1/android/sign?name="
                
                val request = Request.Builder().url(url)
            }
        }
    }
}