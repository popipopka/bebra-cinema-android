package it.bebra.cinema.app.presentation.register.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.app.presentation.login.activity.LoginActivity
import it.bebra.cinema.app.presentation.register.viewmodel.RegisterViewModel
import it.bebra.cinema.databinding.ActivityRegistrationBinding
import it.bebra.cinema.domain.Resource.Error
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.dto.user.UserCreateRequest

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val vm: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.signUpBtn.setOnClickListener {
            val firstName = binding.firstNameInputLayout.editText?.text.toString()
            val lastName = binding.lastNameInputLayout.editText?.text.toString()
            val email = binding.emailInputLayout.editText?.text.toString()
            val username = binding.usernameInputLayout.editText?.text.toString()
            val password = binding.passwordInputLayout.editText?.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Укажите данные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.register(UserCreateRequest(firstName, lastName, email, username, password))
        }

        binding.goBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {
        vm.registerResultFlow.observe(this) {
            when (it) {
                is Success -> startLoginActivity()
                is Error -> Toast.makeText(
                    this,
                    "Проверьте корректность данных",
                    Toast.LENGTH_SHORT
                ).show()

                else -> Unit
            }
        }
    }

    private fun startLoginActivity() {
        val username = binding.usernameInputLayout.editText?.text.toString()
        val password = binding.passwordInputLayout.editText?.text.toString()

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