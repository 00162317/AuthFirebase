package com.example.authfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = et_email_login.text.toString()
            val pass = et_pass_login.text.toString()

            Log.d("loginx", "correo: $email/***")

            signIn(email,pass)

        }

        btn_back_registrar.setOnClickListener {
            finish()
        }
        btn_forget_passs.setOnClickListener {
            startActivity(Intent(this,forgetPass::class.java))
        }
    }

    fun signIn(email:String,pass:String){

        fbAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, OnCompleteListener<AuthResult>{
            task ->
            if(task.isSuccessful){
                Log.d("loginx","HEY: ${fbAuth.currentUser?.email}")
                var intent = Intent(this,logged::class.java)
                intent.putExtra("id",fbAuth.currentUser?.email)
                startActivity(intent)
                if(fbAuth!=null){
                    Log.d("loginx","User SIGNED")
                }
                else{
                    Log.d("loginx","User NO SIGNED")
                }
            }
            else{
                Log.d("loginx","ERROR WE")
            }
        })
    }
}
