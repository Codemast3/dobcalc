package com.holaaaaa.dobcalc

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.time.Year
import java.util.Calendar
import java.util.Locale
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var tvitem : TextView? = null
    private var tvage :  TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndatepicker : Button = findViewById(R.id.btndatepicker)
        tvitem = findViewById(R.id.tvitem)
        tvage = findViewById(R.id.ageinminss)
        btndatepicker.setOnClickListener{
            clickdatepicker()


        }

    }
    private fun clickdatepicker(){
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day =  mycalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
                Toast.makeText(this, "chiuuuuuuuuu", Toast.LENGTH_LONG).show()
                val selecteddate = "$dayofMonth/${month + 1}/$year"
                tvitem?.text = (selecteddate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selecteddate)
                thedate?.let {
                    val selecteddateinmin = thedate.time / 60000
                    val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentdate?.let {
                        val currdateinmin = currentdate.time / 60000
                        val diffinmins = currdateinmin - selecteddateinmin
                        tvage?.text = diffinmins.toString()
                    }

                }
            },

                year,
                month,
                day


        )

        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()
        //Toast.makeText(this,"btndatepicker pressed",Toast.LENGTH_LONG).show()

    }
}