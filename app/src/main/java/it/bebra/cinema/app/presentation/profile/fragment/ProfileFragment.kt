package it.bebra.cinema.app.presentation.profile.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.app.common.util.formatDateTime
import it.bebra.cinema.app.presentation.login.activity.LoginActivity
import it.bebra.cinema.app.presentation.profile.viewmodel.ProfileViewModel
import it.bebra.cinema.databinding.FragmentProfileBinding
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val vm: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()

        vm.getUserProfile()
    }

    private fun setupListeners() {
        binding.exitSystem.setOnClickListener {
            runBlocking { vm.logout() }
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this.context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
    }

    private fun setupObservers() {
        vm.userProfileResultFlow.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> fillInformationAboutUserProfile(it.data)

                else -> Unit
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillInformationAboutUserProfile(profile: UserDetailResponse) {
        binding.apply {
            userFullName.text = "${profile.firstName} ${profile.lastName}"
            userEmail.text = profile.email

            val pattern = "dd MMM yyyy, HH:mm"

            userCreatedAt.text = formatDateTime(profile.createdTime, pattern)
            userUpdatedAt.text = formatDateTime(profile.updatedTime, pattern)
        }
    }
}