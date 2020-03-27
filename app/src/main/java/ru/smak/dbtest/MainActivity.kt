package ru.smak.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dbHelper: DBHelper

    init{
        dbHelper = DBHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            dbHelper.addRow(
                username.text.toString(),
                email.text.toString()
            )
        }

        btnShow.setOnClickListener {
            var i = 1
            resultView.removeAllViews()
            val values = dbHelper.getAllValues()
            for (v in values){
                val tv = TextView(this)
                val s = getString(R.string.result, i, v.first, v.second)
                tv.setText(s)
                resultView.addView(tv)
                i++
            }
        }
    }
}
