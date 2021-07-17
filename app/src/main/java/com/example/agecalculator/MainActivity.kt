package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*



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
        val tvSelectedDate= findViewById<TextView>(R.id.tvSelectedDate)
        val tvSelectedDateInMinutes= findViewById<TextView>(R.id.tvSelectedDateInMinutes)


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

        val format=DecimalFormat("###,###").format(result)
            tvSelectedDate.text = selectedDate

            tvSelectedDateInMinutes.text = format.toString()

    },year,month,day)

        dpd.datePicker.setMaxDate(Date().time)
        dpd.show()

    }
}