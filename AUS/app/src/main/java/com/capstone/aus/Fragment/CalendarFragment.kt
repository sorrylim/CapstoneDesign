package com.capstone.aus.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import com.capstone.aus.Item.SleepItem
import com.capstone.aus.MainActivity.VolleyService

import com.capstone.aus.R
import org.json.JSONObject


class CalendarFragment : Fragment() {
    var sleepDataList = ArrayList<SleepItem.SleepData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        val calendar : CalendarView = rootView.findViewById(R.id.calendar)

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var monthString = ""
            if(month < 10) {
                monthString = "0" + month.toString()
            }
            val msg:String = year.toString()+ " " + monthString+ " " + dayOfMonth.toString()
            Toast.makeText(activity!!, msg, Toast.LENGTH_SHORT).show()
            VolleyService.getSleepDataReq("gold", msg, activity!!, {success->
                sleepDataList.clear()
                var array = success
                for(i in 0..array.length()-1) {
                    var json = array[i] as JSONObject
                    if(json.getString("TIME").replace(":", "").toInt() > 220000) {
                        var sleepdata = SleepItem.SleepData(json.getString("TIME"), json.getInt("GYRO"), json.getInt("HR"))
                        sleepDataList.add(sleepdata)
                    }
                }
                Toast.makeText(activity!!, sleepDataList.size, Toast.LENGTH_LONG).show()
            })
        }

        return rootView
    }

}
