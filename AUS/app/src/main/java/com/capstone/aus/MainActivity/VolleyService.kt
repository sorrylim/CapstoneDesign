package com.capstone.aus.MainActivity

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

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
}