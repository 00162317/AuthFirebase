package com.example.authfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_registrar.setOnClickListener {
            registrar()
        }
        btn_ya_tenias_cuenta.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }
    private fun registrar(){
        val email = et_email.text.toString()
        val pass = et_pass.text.toString()

        if(email.isEmpty()|| pass.isEmpty()){
            Log.d("mains","HEY PONE DATOS VO")
            return
        }
        Log.d("mains","email es: "+email)
        Log.d("mains","pass es: $pass")

        auth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    return@addOnCompleteListener
                }
                Log.d("mains","SE CREO, su id es: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("mains","NO SE CREO: ${it.message}")
            }
        user?.sendEmailVerification()
            ?.addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Log.d("mains","CORREO ENVIADO")
                }
            }
    }
}
