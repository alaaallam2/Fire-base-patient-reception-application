package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    var etLoginEmail: TextInputEditText? = null
    var etLoginPassword: TextInputEditText? = null
    lateinit var tvRegisterHere: TextView
    lateinit var btnLogin: Button

    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginEmail = findViewById(R.id.etLoginEmail)
        etLoginPassword =findViewById(R.id.etLoginPass)
        tvRegisterHere = findViewById(R.id.tvRegisterHere)
        btnLogin = findViewById(R.id.btnLogin)
        mAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener(View.OnClickListener { view: View? ->
            loginUser() })
        tvRegisterHere.setOnClickListener(View.OnClickListener { view: View? ->
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        })
    }

    private fun loginUser() {
        val email = etLoginEmail!!.text.toString()
        val password = etLoginPassword!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            etLoginEmail!!.error = "Email cannot be empty"
            etLoginEmail!!.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword!!.error = "Password cannot be empty"
            etLoginPassword!!.requestFocus()
        } else {
            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        "User logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Log in Error: " + task.exception!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}