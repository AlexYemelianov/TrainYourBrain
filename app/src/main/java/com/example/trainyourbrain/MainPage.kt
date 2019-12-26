package com.example.trainyourbrain

import DatabaseHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlin.random.Random


class MainPage : AppCompatActivity() {

    private var databaseHelper : DatabaseHelper? = null
    private var arrayList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        databaseHelper = DatabaseHelper(this)


        start!!.setOnClickListener {
            arrayList = databaseHelper!!.allQuestionsList

            var random: Random? = null

            for(i in arrayList!!.indices) {
                TODO()
            }
        }
    }
}