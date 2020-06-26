package com.capstone.aus.IntroActivity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.capstone.aus.MainActivity.VolleyService
import com.capstone.aus.R
import kotlinx.android.synthetic.main.activity_signup1.*

class Signup1Activity : AppCompatActivity() {

    var nameCheck = 0
    var pwCheck = 0


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
    fun Check(){
        if(edit_name.text.toString()!=""||edit_name.text!=null){
            nameCheck=1
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)

        var nextBtn=findViewById<Button>(R.id.btn_signup1next)

        edit_pwcheck.setOnFocusChangeListener { view, b ->
            if(b==false)
            {
                pwEqualCheck()
            }
        }

        btn_namecheck.setOnClickListener {

            var id =edit_name.text.toString()

            VolleyService.idCheckReq(id,this,{success->
                when(success){
                    "success" -> {
                        Toast.makeText(this,"중복확인 되었습니다.",Toast.LENGTH_SHORT).show()
                        nextBtn.setEnabled(true)
                    }
                    "fail" -> {
                        Toast.makeText(this,"중복된 아이디입니다.",Toast.LENGTH_SHORT).show()
                        nextBtn.setEnabled(false)
                    }
                }
            })

        }
        btn_signup1next.setOnClickListener{

            var id =edit_name.text.toString()
            var pw =edit_pw.text.toString()
            var tem = edit_tem.text.toString()

            pwEqualCheck()
            Check()

            if(nameCheck == 0)
            {
                Toast.makeText(this, "이름을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(pwCheck == 0)
            {
                Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()

            }


            else {

                VolleyService.joinReq(id,pw,tem,this,{success->

                })

                Toast.makeText(this, "회원가입 입력 완료 .", Toast.LENGTH_SHORT).show()


                var intent = Intent(this, Device1Activity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        }
    }
}
