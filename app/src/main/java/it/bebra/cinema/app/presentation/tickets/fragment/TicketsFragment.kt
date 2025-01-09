package it.bebra.cinema.app.presentation.tickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.app.common.ui.decoration.LastItemBottomSpacingItemDecoration
import it.bebra.cinema.app.common.ui.decoration.SpacingItemDecoration
import it.bebra.cinema.app.presentation.tickets.recycle.TicketsListTicketAdapter
import it.bebra.cinema.app.presentation.tickets.viewmodel.TicketsViewModel
import it.bebra.cinema.databinding.FragmentTicketsBinding
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

        (binding.recyclerView.adapter as TicketsListTicketAdapter)
            .submitList(vm.tickets)

        vm.loadTickets()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)

            addItemDecoration(SpacingItemDecoration(0, 10, 0, 10))
            addItemDecoration(LastItemBottomSpacingItemDecoration(104))

            adapter = TicketsListTicketAdapter()

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

    private fun setupObservers() {
        vm.resultFlow.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    (binding.recyclerView.adapter as TicketsListTicketAdapter)
                        .submitList(vm.tickets)

                    isTicketsLoading = false
                }

                else -> Unit
            }
        }
    }
}