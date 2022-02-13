package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initActionBar()

        btnSignUp.setOnClickListener{

            val email = etEmailSignUp.text.toString().trim()
            val password = etPasswordSignUp.text.toString().trim()
            val confrimPassword = etConfirmPasswordSignUp.text.toString().trim()

            CustomDialog.showDialog(this)
            if (checkValidation(email, password, confrimPassword)){
                registerToserver(email, password)
            }

        }

        tbSignUp.setNavigationOnClickListener{
            finish()
        }
    }

    private fun registerToserver(email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                CustomDialog.hideLoading()
                if (task.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            }
            .addOnFailureListener{
                CustomDialog.hideLoading()
                Toast.makeText(this, "Authentication failid", Toast.LENGTH_SHORT).show()
            }

    }

    private fun checkValidation(email: String, password: String, confrimPassword: String): Boolean {
        if (email.isEmpty()) {
            etEmailSignUp.error = "Please field your email"
            etEmailSignUp.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmailSignUp.error = "Please use valida your email"
            etEmailSignUp.requestFocus()
        } else if (password.isEmpty()) {
            etPasswordSignUp.error = "Please field your password"
            etPasswordSignUp.requestFocus()
        } else if (confrimPassword.isEmpty()) {
            etConfirmPasswordSignUp.error = "Please field your confirm password"
            etConfirmPasswordSignUp.requestFocus()
        } else if (password != confrimPassword){
            etPasswordSignUp.error = "Your password didnt match"
            etConfirmPasswordSignUp.error = "Your confirm password didnt match"

            etPasswordSignUp.requestFocus()
            etConfirmPasswordSignUp.requestFocus()
        }else{
            etPasswordSignUp.error = null
            etConfirmPasswordSignUp.error = null
            return true
        }

        CustomDialog.hideLoading()
        return false
    }

    private fun initActionBar() {
        setSupportActionBar(tbSignUp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title =""
    }
}