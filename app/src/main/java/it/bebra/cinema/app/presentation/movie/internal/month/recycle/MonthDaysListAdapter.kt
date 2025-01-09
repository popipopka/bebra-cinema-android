package it.bebra.cinema.app.presentation.movie.internal.month.recycle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.bebra.cinema.app.presentation.movie.internal.month.recycle.MonthDaysListAdapter.DayViewHolder
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel.DailySessions
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel.Session
import it.bebra.cinema.databinding.ItemDayMonthBinding
import java.time.format.TextStyle
import java.util.Locale

class MonthDaysListAdapter(
    private val onClick: (Int, List<Session>) -> Unit
) : ListAdapter<DailySessions, DayViewHolder>(DiffCallback()) {

    class DayViewHolder(
        private val binding: ItemDayMonthBinding,
        private val onClick: (Int, List<Session>) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(day: DailySessions) {
            binding.dayOfWeek.text = day.dayOfWeek
                .getDisplayName(TextStyle.SHORT, Locale("ru", "RU"))
            binding.dayOfMonth.text = day.dayOfMonth.toString()

            binding.root.setOnClickListener {
                onClick(this.layoutPosition, day.sessions)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val vhBinding = ItemDayMonthBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DayViewHolder(vhBinding, onClick)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<DailySessions>() {

        override fun areItemsTheSame(
            oldItem: DailySessions,
            newItem: DailySessions
        ): Boolean =
            areContentsTheSame(oldItem, newItem)

        override fun areContentsTheSame(
            oldItem: DailySessions,
            newItem: DailySessions
        ): Boolean =
            oldItem.dayOfMonth == newItem.dayOfMonth
                    && oldItem.dayOfWeek == newItem.dayOfWeek
    }
}