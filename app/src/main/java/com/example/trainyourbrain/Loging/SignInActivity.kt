package com.example.trainyourbrain.Loging

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trainyourbrain.R
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        register!!.setOnClickListener {
            when {
                name.length() == 0 -> Toast.makeText(
                    this,
                    "Заповність, будь ласка, поле ${name.hint}",
                    Toast.LENGTH_SHORT
                ).show()
                age.length() == 0 -> Toast.makeText(
                    this,
                    "Заповність, будь ласка, поле ${age.hint}",
                    Toast.LENGTH_SHORT
                ).show()
                email.length() == 0 -> Toast.makeText(
                    this,
                    "Заповність, будь ласка, поле ${email.hint}",
                    Toast.LENGTH_SHORT
                ).show()
                "@" !in email.text -> Toast.makeText(
                    this,
                    "Неправильний формат у полі ${email.hint}",
                    Toast.LENGTH_SHORT
                ).show()
                password.length() < 6 -> Toast.makeText(
                    this,
                    "Пароль надто простий",
                    Toast.LENGTH_SHORT
                ).show()
                confPass.text.toString() != password.text.toString() -> Toast.makeText(
                    this,
                    "Паролі не однакові",
                    Toast.LENGTH_SHORT
                ).show()
                else -> Toast.makeText(
                    this,
                    "Реєстрація пройшла успішно",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }
}

