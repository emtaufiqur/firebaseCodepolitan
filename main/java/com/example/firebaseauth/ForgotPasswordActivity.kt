package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        initActionBar()

        btnSendEmail.setOnClickListener{
            Toast.makeText(this, "Send Email",Toast.LENGTH_SHORT).show()
        }

        tbForgotPassword.setNavigationOnClickListener{
            finish()
        }
    }

    private fun initActionBar() {
        setSupportActionBar(tbForgotPassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}