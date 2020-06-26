package com.capstone.aus.Fragment

import android.R.attr.description
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_analysis.*
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import android.R.attr.entries
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.capstone.aus.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.formatter.PercentFormatter
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils


class AnalysisFragment(val lowCount:Int, val deepCount:Int, val day: String, val nday:String, val sleepStart:String, val sleepEnd:String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_analysis,container,false)
        val piechart:PieChart=rootView.findViewById(R.id.piechart)
        var lowText : TextView = rootView.findViewById(R.id.text_lowdata)
        var deepText : TextView = rootView.findViewById(R.id.text_deepdata)
        var scoreText : TextView = rootView.findViewById(R.id.text_score)
        var dateText : TextView = rootView.findViewById(R.id.text_sleepdate)

        var deep : Float = deepCount.toFloat() / (lowCount.toFloat() + deepCount.toFloat())
        var low : Float = lowCount.toFloat() / (lowCount.toFloat()+ deepCount.toFloat())

        Log.d("data", "${low}")

        val values=ArrayList<PieEntry>()
        values.add(PieEntry(deep,"깊은수면"))
        values.add(PieEntry(low,"얕은수면"))

        val dataSet = PieDataSet(values,"")
        dataSet.setColors(*ColorTemplate.JOYFUL_COLORS)


        val data=PieData(dataSet)
        data.setValueFormatter(PercentFormatter())

        data.setValueTextSize(15f)
        data.setValueTextColor(Color.parseColor("#FFFFFF"))

        piechart.data=data
        piechart.animateY(1000,Easing.EaseInCubic)
        piechart.description.text="수면분석데이터"
        piechart.description.setTextSize(15f)

        var legend: Legend = piechart.getLegend()

        legend.setTextSize(15f)


        lowText.text = "얕은수면값 : ${lowCount}개"
        deepText.text = "깊은수면값 : ${deepCount}개"

        if(low>=0.2 && low < 0.25) {
            scoreText.text = "100점"
        }
        else if(low>=0.25 && low <0.35) {
            scoreText.text = "90점"
        }
        else if(low>=0.35 && low <0.45) {
            scoreText.text = "80점"
        }
        else if(low>=0.45 && low<0.55) {
            scoreText.text = "70점"
        }
        else if(low>=0.55 && low<0.65) {
            scoreText.text = "60점"
        }
        else if(low>=0.65 && low<0.75) {
            scoreText.text = "50점"
        }
        else if(low>=0.75 && low<0.85) {
            scoreText.text = "40점"
        }
        else if(low>=0.85 && low<0.95) {
            scoreText.text = "30점"
        }
        else {
            scoreText.text = "0점"
        }

        dateText.text = "${day} ${sleepStart} ~ ${nday} ${sleepEnd}"



        return rootView
    }

}
