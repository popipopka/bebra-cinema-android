package it.bebra.cinema.presentation.login.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import it.bebra.cinema.R
import it.bebra.cinema.presentation.register.activity.RegisterActivity

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        intent.extras?.let {
            val username = it.getString("username")
            val password = it.getString("password")

            val usernameEditText = findViewById<TextInputLayout>(R.id.username_input_layout).editText
            val passwordEditText = findViewById<TextInputLayout>(R.id.password_input_layout).editText

            usernameEditText?.setText(username)
            passwordEditText?.setText(password)
        }

        val signUpBtn: MaterialButton = findViewById(R.id.sign_up_btn)
        signUpBtn.setOnClickListener{
            startRegisterActivity()
        }

    }

    private fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
        Log.d("BebraCinema", "Старт RegisterActivity из LoginActivity")
    }
}