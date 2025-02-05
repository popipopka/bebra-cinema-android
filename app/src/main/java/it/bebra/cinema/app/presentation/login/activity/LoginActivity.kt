package it.bebra.cinema.app.presentation.login.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.R
import it.bebra.cinema.app.common.ui.edgeToEdge
import it.bebra.cinema.app.presentation.login.viewmodel.LoginViewModel
import it.bebra.cinema.app.presentation.main.activity.MainActivity
import it.bebra.cinema.app.presentation.register.activity.RegisterActivity
import it.bebra.cinema.databinding.ActivityLoginBinding
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.dto.auth.LoginRequest

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupEdgeToEdge()

        setupButtons()
        setupObservers()
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleIntent(intent)
    }

    private fun setupButtons() {
        binding.signUpBtn.setOnClickListener {
            startRegisterActivity()
        }

        binding.signInBtn.setOnClickListener {
            val username = binding.usernameInputLayout.editText?.text?.trim().toString()
            val password = binding.passwordInputLayout.editText?.text?.trim().toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(LoginRequest(username, password))
        }
    }

    private fun setupObservers() {
        viewModel.resultFlow.observe(this) {
            when (it) {
                is Success -> startMainActivity()

                else -> Unit
            }
        }
    }

    private fun handleIntent(intent: Intent) {
        intent.extras?.let {
            val username = it.getString("username")
            val password = it.getString("password")

            if (username.isNullOrEmpty() && password.isNullOrEmpty()) {
                return@let
            }

            val usernameEditText =
                findViewById<TextInputLayout>(R.id.username_input_layout).editText
            val passwordEditText =
                findViewById<TextInputLayout>(R.id.password_input_layout).editText

            usernameEditText?.setText(username)
            passwordEditText?.setText(password)
        }
    }

    private fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
    }
}