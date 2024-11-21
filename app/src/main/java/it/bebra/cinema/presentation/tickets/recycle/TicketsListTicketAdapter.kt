package it.bebra.cinema.presentation.tickets.recycle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import it.bebra.cinema.R
import it.bebra.cinema.common.util.formatDate
import it.bebra.cinema.databinding.ItemTicketTicketsBinding
import it.bebra.domain.model.TicketListModel

class TicketsListTicketAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<TicketListModel> = mutableListOf()

    class TicketViewHolder(
        private val binding: ItemTicketTicketsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(ticket: TicketListModel) {
            ticket.posterUrl?.let {
                binding.filmPoster.load(ticket.posterUrl) {
                    crossfade(true)
                    crossfade(1500)
                    placeholder(R.drawable.download_film_poster)
                    error(R.drawable.error_film_poster)
                }

                binding.filmTitle.text = ticket.filmTitle
                binding.sessionDate.text = formatDate(ticket.date, "dd MMMM yyyy, HH:mm")
                binding.ticketPlaceNumber.text = ticket.place.toString()
                binding.ticketRowNumber.text = ticket.row.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vhBinding =
            ItemTicketTicketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketViewHolder(vhBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TicketViewHolder) {
            holder.bind(data[position])
        }
    }

    fun addData(data: List<TicketListModel>) {
        val diffCallback = TicketsListTicketDiffUtil(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }
}