package com.capstone.aus.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.capstone.aus.Item.SleepItem
import com.capstone.aus.MainActivity.MainActivity
import com.capstone.aus.MainActivity.VolleyService

import com.capstone.aus.R
import org.json.JSONObject


class CalendarFragment : Fragment() {
    var sleepDataList = ArrayList<SleepItem.SleepData>()
    var dayDataList = ArrayList<SleepItem.SleepData>()
    var deep : Int = 0
    var low : Int = 0
    var sleepStart : String = ""
    var sleepEnd : String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        val calendar : CalendarView = rootView.findViewById(R.id.calendar)

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            low = 0
            deep = 0
            var monthString = ""
            if(month < 10) {
                monthString = "0" + (month+1).toString()
            }
            val day:String = year.toString()+ " " + monthString+ " " + dayOfMonth.toString()
            val nextday:String = year.toString() + " " + monthString + " " + (dayOfMonth+1).toString()
            VolleyService.getSleepInfoReq("gold", day, activity!!, {success->
                var json = success
                sleepStart = json.getString("sleep_start")
                sleepEnd = json.getString("sleep_end")
                var sleepStarts : Int = sleepStart.replace(":", "").toInt()
                var sleepEnds : Int = sleepEnd.replace(":", "").toInt()
                VolleyService.getSleepDataReq("gold", day, nextday, activity!!, {success->
                    sleepDataList.clear()
                    dayDataList.clear()
                    var array = success
                    for(i in 0..array.length()-1) {
                        var json = array[i] as JSONObject
                        var sleepdata = SleepItem.SleepData(json.getString("DATE"), json.getString("TIME").replace(":", "").toInt(), json.getInt("GYRO"), json.getInt("HR"))
                        sleepDataList.add(sleepdata)
                    }


                    for(i in 0..sleepDataList.size-1) {
                        if(sleepDataList.get(i).time >= sleepStarts && sleepDataList.get(i).date == day) {
                            dayDataList.add(SleepItem.SleepData(sleepDataList.get(i).date, sleepDataList.get(i).time, sleepDataList.get(i).gyro, sleepDataList.get(i).hr))
                        }
                        else if(sleepDataList.get(i).time <= sleepEnds && sleepDataList.get(i).date == nextday) {
                            dayDataList.add(SleepItem.SleepData(sleepDataList.get(i).date, sleepDataList.get(i).time, sleepDataList.get(i).gyro, sleepDataList.get(i).hr))
                        }
                    }

                    for(i in 0..dayDataList.size-1) {
                        if(dayDataList.get(i).gyro >= 0) {
                            low++;
                        }
                    }

                    deep = dayDataList.size - low


                    val fragment = AnalysisFragment(1845, 3195, day, nextday, sleepStart, sleepEnd)
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, fragment, fragment.javaClass.simpleName).commit()

                })
            })


        }

        return rootView
    }



}
