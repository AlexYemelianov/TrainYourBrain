package com.yemelianov.trainyourbrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)

        val timeED: EditText = findViewById(R.id.timeEditText)
        var time = 1
        var themeBtn: Button? = null

        intent = Intent(this, MainActivity::class.java)

        themeBtn = findViewById(R.id.setThemeBtn)

        checkTheme()
        themeBtn.setOnClickListener { chooseThemeDialog() }

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

    private fun chooseThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change theme")
        val styles = arrayOf("Light", "Dark", "System default")
        val checkedItem = MyPreferences(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(this).darkMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(this).darkMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    MyPreferences(this).darkMode = 2
                    delegate.applyDayNight()
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }
    private fun checkTheme() {
        when (MyPreferences(this).darkMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }
}