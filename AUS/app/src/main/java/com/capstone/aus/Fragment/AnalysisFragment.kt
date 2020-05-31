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
import com.capstone.aus.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.formatter.PercentFormatter
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils


class AnalysisFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_analysis,container,false)
        val piechart:PieChart=rootView.findViewById(R.id.piechart)
    //pie데이터

        val values=ArrayList<PieEntry>()
        values.add(PieEntry(34.0f,"test1"))
        values.add(PieEntry(28.2f,"test2"))
        values.add(PieEntry(37.9f,"test3"))
        val dataSet = PieDataSet(values,"")
        dataSet.setColors(*ColorTemplate.JOYFUL_COLORS)


        val data=PieData(dataSet)
        data.setValueFormatter(PercentFormatter())

        piechart.data=data
        piechart.animateY(1000,Easing.EaseInCubic)

        return rootView
    }

}
