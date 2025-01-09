package it.bebra.cinema.app.presentation.movie.internal.month.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import it.bebra.cinema.app.common.ui.decoration.SpacingItemDecoration
import it.bebra.cinema.app.presentation.movie.internal.month.recycle.MonthDaysListAdapter
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel.*
import it.bebra.cinema.databinding.FragmentMonthBinding
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import java.util.Locale

class MonthFragment(
    val index: Int,
    private val monthlySessions: MonthlySessions,
    private val onClickDay: (Int, List<Session>) -> Unit
) : Fragment() {
    companion object {
        const val DEFAULT_STROKE_WIDTH = 4
    }

    private lateinit var binding: FragmentMonthBinding
    private var positionOfSelectedDay: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentMonthBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillData()
    }

    private fun onClickDayDecorator(position: Int, data: List<Session>) {
        lifecycleScope.launch {
            removeSelectionFromSelectedDay()
            (binding.recyclerView[position] as MaterialCardView).strokeWidth = DEFAULT_STROKE_WIDTH

            positionOfSelectedDay = position
        }

        onClickDay(this.index, data)
    }

    fun removeSelectionFromSelectedDay() {
        positionOfSelectedDay?.let {
            (binding.recyclerView[it] as MaterialCardView).strokeWidth = 0

            positionOfSelectedDay = null
        }
    }

    private fun fillData() {
        binding.monthName.text = monthlySessions.month
            .getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru", "RU"))
            .uppercase()

        val recycleAdapter = MonthDaysListAdapter(::onClickDayDecorator)
        recycleAdapter.submitList(monthlySessions.days)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

            addItemDecoration(SpacingItemDecoration(10, 0, 10, 0))

            adapter = recycleAdapter
        }
    }
}