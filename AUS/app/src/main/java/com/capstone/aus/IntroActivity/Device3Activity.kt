package com.capstone.aus.IntroActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.capstone.aus.MainActivity.MainActivity
import com.capstone.aus.R

class Device3Activity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product3)


        Handler().postDelayed({ startActivity(Intent(baseContext, MainActivity::class.java)) }, 2000L)
    }
}