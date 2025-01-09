package it.bebra.cinema.app.presentation.movie.adapter.recycle

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.bebra.cinema.app.common.util.formatDateTime
import it.bebra.cinema.app.presentation.movie.adapter.recycle.MovieSessionsListAdapter.SessionViewHolder
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel.Session
import it.bebra.cinema.databinding.ItemSessionMovieBinding

class MovieSessionsListAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<Session, SessionViewHolder>(DiffCallback()) {

    class SessionViewHolder(
        private val binding: ItemSessionMovieBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(session: Session) {
            binding.startTime.text = formatDateTime(
                session.startTime,
                "HH:mm"
            )

            binding.hallName.text = session.hallName
            binding.price.text = session.price.toString()

            binding.root.setOnClickListener {
                onClick(session.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val vhBinding = ItemSessionMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return SessionViewHolder(vhBinding, onClick)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Session>() {

        override fun areItemsTheSame(
            oldItem: Session,
            newItem: Session
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Session,
            newItem: Session
        ): Boolean =
            oldItem.id == newItem.id
    }
}