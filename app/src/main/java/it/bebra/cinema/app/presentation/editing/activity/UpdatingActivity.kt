package it.bebra.cinema.app.presentation.editing.activity

import android.os.Bundle
import android.view.WindowInsets.Type.ime
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.app.common.ui.edgeToEdge
import it.bebra.cinema.app.common.ui.padding
import it.bebra.cinema.app.presentation.editing.viewmodel.UpdatingViewModel
import it.bebra.cinema.databinding.ActivityUpdatingBinding
import it.bebra.cinema.domain.Resource.Error
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import it.bebra.cinema.domain.dto.user.UserUpdateRequest

@AndroidEntryPoint
class UpdatingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatingBinding
    private val vm: UpdatingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdatingBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupEdgeToEdge()

        vm.loadUserProfile()

        setupListeners()
        setupObservers()
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root.padding(ime())
        }
    }

    private fun setupListeners() {
        binding.updateBtn.setOnClickListener {
            val firstName = binding.firstNameInput.editText?.text?.trim().toString()
            val lastName = binding.lastNameInput.editText?.text?.trim().toString()
            val email = binding.emailInput.editText?.text?.trim().toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.updateUser(UserUpdateRequest(firstName, lastName, email))
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {
        vm.updateResultFlow.observe(this) {
            when (it) {
                is Success -> {
                    Toast.makeText(this, "Профиль успешно обновлен", Toast.LENGTH_SHORT).show()
                    finish()
                }

                is Error -> Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()

                else -> Unit
            }
        }

        vm.userProfileResultFlow.observe(this) {
            when (it) {
                is Success -> fillInformationAboutUserProfile(it.data)
                is Error -> {
                    Toast.makeText(
                        this,
                        "Произошла ошибка загрузки данных пользователя",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }

                else -> Unit
            }
        }
    }

    private fun fillInformationAboutUserProfile(profile: UserDetailResponse) {
        binding.apply {
            firstNameInput.editText?.setText(profile.firstName)
            lastNameInput.editText?.setText(profile.lastName)
            emailInput.editText?.setText(profile.email)
        }
    }
}