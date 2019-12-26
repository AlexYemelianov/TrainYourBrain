package com.example.trainyourbrain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trainyourbrain.Loging.LoginActivity
import com.example.trainyourbrain.Loging.SignInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        logIn.setOnClickListener {
            val intent0 = Intent(this, LoginActivity::class.java)
            startActivity(intent0)
            this.finish()
        }

        signIn.setOnClickListener {
            val intent1 = Intent(this, SignInActivity::class.java)
            startActivity(intent1)
            this.finish()
        }

        withoutReg.setOnClickListener {
            val intent2 = Intent(this, MainPage::class.java)
            startActivity(intent2)
            this.finish()
        }
    }


}
