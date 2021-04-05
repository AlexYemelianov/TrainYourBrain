package com.yemelianov.trainyourbrain.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.yemelianov.trainyourbrain.R
import com.yemelianov.trainyourbrain.authentication.FirebaseObject.firebaseAuth
import com.yemelianov.trainyourbrain.authentication.FirebaseObject.firebaseUser
import kotlinx.android.synthetic.main.activity_email_password_authentication.*

class EmailPasswordAuthentication : AppCompatActivity() {

    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password_authentication)

        createAccountInputsArray = arrayOf(emailEt, passwordEt, confirmPasswordEt)
        register_btn.setOnClickListener {
            signIn()
        }
        logInBtn.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
            Toast.makeText(this, "please sign into your account", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            Toast.makeText(this, "welcome back", Toast.LENGTH_SHORT).show()
        }
    }

    private fun notEmpty(): Boolean = emailEt.text.toString().trim().isNotEmpty() &&
            passwordEt.text.toString().trim().isNotEmpty() &&
            confirmPasswordEt.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            passwordEt.text.toString().trim() == confirmPasswordEt.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            Toast.makeText(this, "passwords are not matching !", Toast.LENGTH_SHORT).show()
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            userEmail = emailEt.text.toString().trim()
            userPassword = passwordEt.text.toString().trim()

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "created account successfully !", Toast.LENGTH_SHORT)
                            .show()
                        sendEmailVerification()
                        finish()
                    } else {
                        Toast.makeText(this, "failed to Authenticate !", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "email sent to $userEmail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}