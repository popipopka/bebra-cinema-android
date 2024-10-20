package it.bebra.cinema.presentation.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import it.bebra.cinema.presentation.login.activity.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Затычка
        startActivity(Intent(this, LoginActivity::class.java))
    }
}