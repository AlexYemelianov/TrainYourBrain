package com.example.trainyourbrain

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        register.setOnClickListener {
            when {
                name.length() == 0 -> Toast.makeText(this, "Заповність, будь ласка, поле ${name.hint}", Toast.LENGTH_SHORT).show()
                age.length() == 0 -> Toast.makeText(this, "Заповність, будь ласка, поле ${age.hint}", Toast.LENGTH_SHORT).show()
                email.length() == 0 -> Toast.makeText(this, "Заповність, будь ласка, поле ${email.hint}", Toast.LENGTH_SHORT).show()
                "@" !in email.getText() -> Toast.makeText(this, "Неправильний формат у полі ${email.hint}", Toast.LENGTH_SHORT).show()
                password.text != confPass.text -> Toast.makeText(this, "Паролі не збігаються", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

}