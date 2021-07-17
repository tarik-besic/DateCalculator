package com.example.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dugme= findViewById<Button>(R.id.btnDatePicker)
        dugme.setOnClickListener {
            view->clickDatePicker(view)

        }

    }

    fun clickDatePicker(view: View)
    {

        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)



        val dpd=DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

        val selectedDate="$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

        val sdf=SimpleDateFormat("dd/MM/yyyy")

        val theDate=sdf.parse(selectedDate)   //object type of DATE!

        val selectedDateToMinutes=theDate.time/60000

        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

        // Current date in to minutes.
        val currentDateToMinutes = currentDate.time / 60000

        val result=currentDateToMinutes-selectedDateToMinutes
        val resultDay=result/1440
        val resultHour=result/60
        val resultYear=(result/525600F).roundToInt()

        val formatResultMinutes=DecimalFormat("###,###").format(result)
        val formatResultHour=DecimalFormat("###,###").format(resultHour)
        val formatResultDay=DecimalFormat("###,###").format(resultDay)

        setValues(formatResultMinutes,formatResultHour,formatResultDay,resultYear,selectedDate)


    },year,month,day)

        dpd.datePicker.setMaxDate(Date().time)
        dpd.show()

    }

    fun setValues(minutes:String,hours:String,days:String,year:Int,selectedDate:String)
    {
        val tvSelectedDate= findViewById<TextView>(R.id.tvSelectedDate)
        val tvSelectedDateInMinutes= findViewById<TextView>(R.id.tvSelectedDateInMinutes)
        val tvSelectedDateInHours= findViewById<TextView>(R.id.tvSelectedDateInHours)
        val tvSelectedDateInDays= findViewById<TextView>(R.id.tvSelectedDateInDays)
        val tvSelectedDateInYear= findViewById<TextView>(R.id.tvSelectedDateInYears)

        tvSelectedDate.text = selectedDate
        tvSelectedDateInMinutes.text = minutes.toString()
        tvSelectedDateInHours.text=hours.toString()
        tvSelectedDateInDays.text=days.toString()
        tvSelectedDateInYear.text=year.toString()

        tvSelectedDateInDays.setTextColor(getResources().getColor(R.color.textColor,getResources().newTheme()))
        tvSelectedDateInHours.setTextColor(getResources().getColor(R.color.textColor,getResources().newTheme()))
        tvSelectedDateInMinutes.setTextColor(getResources().getColor(R.color.textColor,getResources().newTheme()))
        tvSelectedDateInYear.setTextColor(getResources().getColor(R.color.textColor,getResources().newTheme()))

    }

}