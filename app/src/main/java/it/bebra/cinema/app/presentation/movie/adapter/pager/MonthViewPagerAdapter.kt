package it.bebra.cinema.app.presentation.movie.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import it.bebra.cinema.app.presentation.movie.internal.month.fragment.MonthFragment

class MonthViewPagerAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val _fragments = mutableListOf<MonthFragment>()

    override fun getItemCount(): Int =
        _fragments.size

    override fun createFragment(position: Int): Fragment =
        _fragments[position]

    fun addFragment(fragment: MonthFragment) {
        _fragments.add(fragment)
        notifyItemInserted(_fragments.size - 1)
    }

    fun getFragments() = _fragments.toList()
}