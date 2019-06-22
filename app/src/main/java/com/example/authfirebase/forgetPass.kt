package com.example.authfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_pass.*

class forgetPass : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)
        btn_forget_accept.setOnClickListener {
            val correo = et_forget_pass.text.toString()

            auth.sendPasswordResetEmail(correo)
                .addOnCompleteListener {
                        task->
                    if(task.isSuccessful){
                        Log.d("forget","Email enviado")
                    }
                    else{
                        Log.d("forget","NEL, CORREO NO ENVIADO")
                    }
                }
        }
    }
}
