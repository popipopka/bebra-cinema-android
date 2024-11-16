package it.bebra.cinema.presentation.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.bebra.cinema.R
import it.bebra.cinema.databinding.ActivityMainBinding
import it.bebra.cinema.presentation.catalog.fragment.CatalogFragment
import it.bebra.cinema.presentation.main.controller.FragmentLinks.CATALOG
import it.bebra.cinema.presentation.main.controller.FragmentLinks.PROFILE
import it.bebra.cinema.presentation.main.controller.FragmentLinks.TICKET
import it.bebra.cinema.presentation.main.controller.NavigationController
import it.bebra.cinema.presentation.profile.fragment.ProfileFragment
import it.bebra.cinema.presentation.ticket.fragment.TicketFragment

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = NavigationController(supportFragmentManager, binding.mainFragmentContainerView)
            .apply {
                addFragment(TICKET, TicketFragment())
                addFragment(CATALOG, CatalogFragment())
                addFragment(PROFILE, ProfileFragment())
            }

        val bottomNavigation = binding.bottomNavigationView

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_navigation_tickets -> {
                    navController.navTo(TICKET)
                    bottomNavigation.menu.getItem(0).isChecked = true
                }

                R.id.item_navigation_home -> {
                    navController.navTo(CATALOG)
                    bottomNavigation.menu.getItem(1).isChecked = true
                }

                else -> {
                    navController.navTo(PROFILE)
                    bottomNavigation.menu.getItem(2).isChecked = true
                }
            }

            true
        }

        navController.navTo(CATALOG)
        bottomNavigation.menu.getItem(1).isChecked = true
    }
}