package it.bebra.cinema.app.presentation.tickets.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.R
import it.bebra.cinema.app.common.ui.decoration.LastItemBottomSpacingItemDecoration
import it.bebra.cinema.app.common.ui.decoration.SpacingItemDecoration
import it.bebra.cinema.app.presentation.tickets.recycle.TicketsListTicketAdapter
import it.bebra.cinema.app.presentation.tickets.viewmodel.TicketsViewModel
import it.bebra.cinema.databinding.FragmentTicketsBinding
import it.bebra.cinema.domain.Resource.Error
import it.bebra.cinema.domain.Resource.Success

@AndroidEntryPoint
class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding
    private val vm: TicketsViewModel by viewModels()

    private var isTicketsLoading = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        /*TODO Сделать сохранение состояния в viewModel, чтобы каждый раз все заново не загружать.
        * Для этого подгрузить первую страницу при создании фрагмента и проверить полученные данные.
        * Если хоть что-то уже есть, то просто добавить новое и больше не грузить
        */
        vm.resetState()
        vm.loadTickets()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)

            addItemDecoration(SpacingItemDecoration(0, 10, 0, 10))
            addItemDecoration(LastItemBottomSpacingItemDecoration(104))

            adapter = TicketsListTicketAdapter(::showTicketPopupMenu)

            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (!isTicketsLoading && (totalItemCount <= (lastVisibleItem + 1))) {
                        isTicketsLoading = true
                        vm.loadTickets()
                    }
                }
            })
        }
    }

    private fun showTicketPopupMenu(view: View, ticketId: Int) {
        val popupMenu = PopupMenu(context, view)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.ticket_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_ticket_item -> {
                    vm.deleteTicket(ticketId)

                    true
                }

                else -> false
            }
        }

        popupMenu.gravity = Gravity.CENTER_VERTICAL
        popupMenu.show()
    }

    private fun setupObservers() {
        vm.ticketsResultFlow.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    (binding.recyclerView.adapter as TicketsListTicketAdapter)
                        .submitList(vm.tickets)

                    isTicketsLoading = false
                }

                else -> Unit
            }
        }

        vm.deleteResultFlow.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    Toast.makeText(
                        this.context,
                        "Билет успешно отменен",
                        Toast.LENGTH_SHORT
                    ).show()

                    vm.resetState()
                    vm.loadTickets()
                }

                is Error -> Toast.makeText(
                    this.context,
                    "Данный билет нельзя отменить",
                    Toast.LENGTH_SHORT
                ).show()

                else -> Unit
            }
        }
    }
}