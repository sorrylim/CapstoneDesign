package com.capstone.aus.MainActivity

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Method

object VolleyService {
    val ip= "http://121.181.171.51:2222"

    fun getSleepDataReq(userId: String, sleepDate:String, context: Context, success: (JSONArray) -> Unit) {
        var url="${ip}/sleepdata/get"

        var json= JSONObject()
        json.put("user_id", userId)
        json.put("sleep_date", sleepDate)

        var array = JSONArray()
        array.put(json)

        var request=object : JsonArrayRequest(
            Method.POST,
            url,
            array,
            Response.Listener {
                success(it)
            },
            Response.ErrorListener {
                Log.d("volleysorvice", "${it}")
            }){
        }
        Log.d("test", "test")
        Volley.newRequestQueue(context).add(request)
    }

    fun loginReq(id: String, pw: String, context: Context, success: (JSONObject) -> Unit) {
        val url = "${ip}/user/login"

        val json = JSONObject()
        json.put("id", id)

        var result = JSONObject()

        var request = object : JsonObjectRequest(
            Method.POST
            , url
            , json
            , Response.Listener {
                result.put("user", it)
                if (pw != it.getString("user_pw"))
                    result.put("code", 2)
                else if (pw == it.getString("user_pw"))
                    result.put("code", 3)
                success(result)
            }
            , Response.ErrorListener {
                Log.d("test",it.toString())
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    result.put("code", 0)
                } else if (it is com.android.volley.ParseError) {
                    Log.d("test", "ParserError")
                    result.put("code", 1)
                }
                success(result)
            }
        ) {
        }
        //요청을 보내는 부분
        Volley.newRequestQueue(context).add(request)
    }

}