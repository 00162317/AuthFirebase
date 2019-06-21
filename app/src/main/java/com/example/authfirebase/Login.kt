package com.example.authfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = et_email_login.text.toString()
            val pass = et_pass_login.text.toString()

            Log.d("login", "correo: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)

        }

        btn_back_registrar.setOnClickListener {
            finish()
        }
    }
}
