package com.yemelianov.trainyourbrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)

        val timeED: EditText = findViewById(R.id.timeEditText)
        var time = 1

        intent = Intent(this, MainActivity::class.java)

        val easyBtn = findViewById<Button>(R.id.easy)
        easyBtn.setOnClickListener {
            if (timeED.text.isNotEmpty()) {
                time = timeED.text.toString().toInt()
                intent.putExtra("time", time)
            }
            intent.putExtra("x", 21)
            finish()
            startActivity(intent)
            Toast.makeText(this, "You have chosen ${easyBtn.text} level", Toast.LENGTH_SHORT).show()
        }

        val middleBtn = findViewById<Button>(R.id.middle)
        middleBtn.setOnClickListener {
            if (timeED.text.isNotEmpty()) {
                time = timeED.text.toString().toInt()
                intent.putExtra("time", time)
            }
            intent.putExtra("x", 41)
            finish()
            startActivity(intent)
            Toast.makeText(this, "You have chosen ${middleBtn.text} level", Toast.LENGTH_SHORT)
                .show()
        }

        val hardBtn = findViewById<Button>(R.id.hard)
        hardBtn.setOnClickListener {
            if (timeED.text.isNotEmpty()) {
                time = timeED.text.toString().toInt()
                intent.putExtra("time", time)
            }
            intent.putExtra("x", 61)
            finish()
            startActivity(intent)
            Toast.makeText(this, "You have chosen ${hardBtn.text} level", Toast.LENGTH_SHORT).show()
        }

        val backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

    }

}