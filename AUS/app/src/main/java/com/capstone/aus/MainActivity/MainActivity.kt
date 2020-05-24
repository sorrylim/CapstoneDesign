package com.capstone.aus.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.aus.Fragment.AnalysisFragment
import com.capstone.aus.Fragment.CalendarFragment
import com.capstone.aus.Fragment.MypageFragment
import com.capstone.aus.IntroActivity.LoginActivity
import com.capstone.aus.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            val fragment = AnalysisFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_main, fragment, fragment.javaClass.simpleName).commit()
        }

    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.calendar -> {
                val fragment = CalendarFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_main, fragment, fragment.javaClass.simpleName).commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.analysis -> {
                val fragment = AnalysisFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_main, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.mypage -> {
                val fragment = MypageFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_main, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
