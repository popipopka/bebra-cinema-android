package it.bebra.cinema.app.presentation.tickets.recycle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.bebra.cinema.app.common.util.formatDateTime
import it.bebra.cinema.app.common.util.loadMovieImage
import it.bebra.cinema.app.presentation.tickets.recycle.TicketsListTicketAdapter.TicketViewHolder
import it.bebra.cinema.databinding.ItemTicketTicketsBinding
import it.bebra.cinema.domain.dto.ticket.TicketListResponse

class TicketsListTicketAdapter(
    private val onLongClick: (View, Int) -> Unit
) : ListAdapter<TicketListResponse, TicketViewHolder>(DiffCallback()) {

    class TicketViewHolder(
        private val binding: ItemTicketTicketsBinding,
        private val onLongClick: (View, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(ticket: TicketListResponse) {
            ticket.moviePosterUrl?.let {
                loadMovieImage(it, binding.moviePoster)
            }

            binding.movieName.text = ticket.movieName
            binding.sessionDate.text =
                formatDateTime(ticket.sessionStartTime, "dd MMMM yyyy, HH:mm")
            binding.ticketPlaceNumber.text = ticket.place.toString()
            binding.ticketRowNumber.text = ticket.row.toString()

            binding.root.setOnLongClickListener {
                onLongClick(this.itemView, ticket.id)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val vhBinding =
            ItemTicketTicketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketViewHolder(vhBinding, onLongClick)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TicketListResponse>() {

        override fun areItemsTheSame(
            oldItem: TicketListResponse,
            newItem: TicketListResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TicketListResponse,
            newItem: TicketListResponse
        ): Boolean =
            oldItem == newItem
    }
}