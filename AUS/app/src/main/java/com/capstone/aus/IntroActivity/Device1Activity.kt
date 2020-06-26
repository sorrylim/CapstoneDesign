package com.capstone.aus.IntroActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.aus.R
import kotlinx.android.synthetic.main.activity_product1.*

class Device1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product1)

        text_next.setOnClickListener {
            var intent = Intent(this, Device2Activity::class.java)
            startActivity(intent)
        }
    }
}
