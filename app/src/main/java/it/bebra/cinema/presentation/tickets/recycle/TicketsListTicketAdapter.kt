package it.bebra.cinema.presentation.tickets.recycle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.bebra.cinema.common.util.formatDate
import it.bebra.cinema.common.util.loadMovieImage
import it.bebra.cinema.databinding.ItemTicketTicketsBinding
import it.bebra.cinema.presentation.tickets.recycle.TicketsListTicketAdapter.TicketViewHolder
import it.bebra.domain.model.TicketListModel

class TicketsListTicketAdapter : ListAdapter<TicketListModel, TicketViewHolder>(DiffCallback()) {

    class TicketViewHolder(
        private val binding: ItemTicketTicketsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(ticket: TicketListModel) {
            ticket.posterUrl?.let {
                loadMovieImage(it, binding.moviePoster)
            }

            binding.movieName.text = ticket.filmTitle
            binding.sessionDate.text = formatDate(ticket.date, "dd MMMM yyyy, HH:mm")
            binding.ticketPlaceNumber.text = ticket.place.toString()
            binding.ticketRowNumber.text = ticket.row.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val vhBinding =
            ItemTicketTicketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketViewHolder(vhBinding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TicketListModel>() {

        override fun areItemsTheSame(oldItem: TicketListModel, newItem: TicketListModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TicketListModel,
            newItem: TicketListModel
        ): Boolean =
            oldItem == newItem
    }
}