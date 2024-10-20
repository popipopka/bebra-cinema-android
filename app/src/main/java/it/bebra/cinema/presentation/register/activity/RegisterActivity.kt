package it.bebra.cinema.presentation.register.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.android.material.textfield.TextInputLayout
import it.bebra.cinema.R
import it.bebra.cinema.presentation.login.activity.LoginActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val createAccountBtn: Button = findViewById(R.id.sign_up_btn)
        createAccountBtn.setOnClickListener() {
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        val username =
            findViewById<TextInputLayout>(R.id.username_input_layout).editText?.text.toString()
        val password =
            findViewById<TextInputLayout>(R.id.password_input_layout).editText?.text.toString()

        val intent = Intent(this, LoginActivity::class.java)

        intent.putExtras(
            bundleOf(
                "username" to username,
                "password" to password
            )
        )

        startActivity(intent)
        finish()
    }
}