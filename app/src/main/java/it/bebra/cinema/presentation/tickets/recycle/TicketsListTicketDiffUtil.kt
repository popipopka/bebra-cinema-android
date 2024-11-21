package it.bebra.cinema.presentation.tickets.recycle

import androidx.recyclerview.widget.DiffUtil
import it.bebra.domain.model.TicketListModel

class TicketsListTicketDiffUtil(
    private val oldList: List<TicketListModel>,
    private val newList: List<TicketListModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}