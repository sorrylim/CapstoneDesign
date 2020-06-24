package com.capstone.aus.IntroActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.aus.Item.UserInfo
import com.capstone.aus.MainActivity.MainActivity
import com.capstone.aus.MainActivity.VolleyService
import com.capstone.aus.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        text_join.setOnClickListener {
            var intent = Intent(this, Signup1Activity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            var id = edit_id.text.toString()
            var pw = edit_pw.text.toString()
            VolleyService.loginReq(id, pw, this, { success ->
                when (success.getInt("code")) {
                    0 -> {
                        Toast.makeText(this, "서버와의 통신에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(this, "계정을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(this, "ID / PW를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        // var token= FirebaseInstanceId.getInstance().token
                        var user = success.getJSONObject("user")
                        UserInfo.ID = user.getString("user_id")
                        UserInfo.PW = user.getString("user_pw")


                        //   VolleyService.insertTokenReq(UserInfo.ID,token,this)

                        var pref = this.getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
                        var editor = pref.edit()
                        editor.putString("ID", UserInfo.ID)
                            .putString("PW", UserInfo.PW)
                            .apply()

                        var intent: Intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

            })
        }
    }
}

