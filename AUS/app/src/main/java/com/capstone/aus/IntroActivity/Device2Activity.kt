package com.capstone.aus.IntroActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.capstone.aus.MainActivity.VolleyService
import com.capstone.aus.R
import kotlinx.android.synthetic.main.activity_product1.*

class Device2Activity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product2)

        VolleyService.deviceReq(this,{success->

        })
        Handler().postDelayed({ startActivity(Intent(baseContext, Device3Activity::class.java)) }, 2000L)

       // var intent = Intent(this, Device2Activity::class.java)
       // startActivity(intent)
    }
}