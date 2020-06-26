package com.capstone.aus.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.capstone.aus.IntroActivity.Device1Activity
import com.capstone.aus.IntroActivity.Device2Activity
import com.capstone.aus.IntroActivity.LoginActivity

import com.capstone.aus.R


class MypageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_mypage, container, false)
        val logoutBtn = rootView.findViewById<TextView>(R.id.text_logout)
        val deviceBtn = rootView.findViewById<TextView>(R.id.text_device)

        logoutBtn.setOnClickListener{
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        deviceBtn.setOnClickListener {
            var intent = Intent(context, Device1Activity::class.java)
            startActivity(intent)
        }
        return rootView
    }

}
