package it.bebra.cinema.app.presentation.main.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.R
import it.bebra.cinema.app.presentation.login.activity.LoginActivity
import it.bebra.cinema.app.presentation.main.viewmodel.MainViewModel
import it.bebra.cinema.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        validateLogin {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupEdgeToEdge()

            setupNavigation()
        }
    }

    private fun setupEdgeToEdge() {
        // Через edgeToEdge { } ломается bootom navigation viewfe
        window.apply {
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }
    }

    private fun validateLogin(onLogged: () -> Unit) {
        if (!viewModel.isLogin()) {
            startLoginActivity()

        } else {
            onLogged.invoke()
        }
    }

    private fun setupNavigation() {
        val navController = this.findNavController(R.id.nav_host_fragment)
        val bottomNavView = binding.bottomNavigationView

        bottomNavView.setupWithNavController(navController)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
    }
}