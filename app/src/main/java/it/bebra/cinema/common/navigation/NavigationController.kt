package it.bebra.cinema.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

class NavigationController(
    private val fragmentManager: FragmentManager,
    private val container: FragmentContainerView
) {
    private var fragments = mutableMapOf<FragmentLinks, Fragment>()

    fun addFragment(link: FragmentLinks, fragment: Fragment) {
        fragments[link] = fragment
    }

    fun navTo(link: FragmentLinks) {
        val newFragment = fragments[link]

        newFragment?.let {
            val currentFragment = container.getFragment<Fragment>()

            if (currentFragment == newFragment) {
                return@let
            }

            fragmentManager.beginTransaction()
                .replace(container.id, it)
                .addToBackStack(null)
                .commit()
        }
    }
}