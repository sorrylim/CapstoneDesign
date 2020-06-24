package com.capstone.aus.IntroActivity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.aus.R
import kotlinx.android.synthetic.main.activity_signup1.*

class Signup1Activity : AppCompatActivity() {

    var nameCheck = 0
    var pwCheck = 0
    var gender:String = ""

    fun pwEqualCheck() {
        if(edit_pw.text.toString().equals(edit_pwcheck.text.toString()))
        {
            text_pwcheck.text = "비밀번호가 일치합니다."
            text_pwcheck.setTextColor(Color.parseColor("#008000"))
            pwCheck = 1
        }
        else{
            text_pwcheck.text = "비밀번호가 일치하지 않습니다."
            text_pwcheck.setTextColor(Color.parseColor("#FF0000"))
            pwCheck = 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)

        edit_pwcheck.setOnFocusChangeListener { view, b ->
            if(b==false)
            {
                pwEqualCheck()
            }
        }

        /*radiogroup_gender.setOnCheckedChangeListener {radioGroup, i ->
            when(i) {
                R.id.radio_male -> gender = "M"
                R.id.radio_female -> gender = "F"
            }
        }*/

        btn_signup1next.setOnClickListener{
            pwEqualCheck()
            if(nameCheck == 0)
            {
                Toast.makeText(this, "이름을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(pwCheck == 0)
            {
                Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(gender == "")
            {
                Toast.makeText(this, "성별을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            /*else if(edit_age.text.toString() == "" || edit_age.text.toString() == null)
            {
                Toast.makeText(this, "나이를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }*/
            else {
                var intent = Intent(this, Signup2Activity::class.java)
                intent.putExtra("name", edit_name.text.toString())
                intent.putExtra("pw", edit_pwcheck.text.toString())
                intent.putExtra("gender", gender)
                //intent.putExtra("age", edit_age.text.toString())
                startActivity(intent)
            }
        }
    }
}
