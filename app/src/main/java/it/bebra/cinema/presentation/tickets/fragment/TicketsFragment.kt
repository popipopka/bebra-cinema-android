package it.bebra.cinema.presentation.tickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.common.ui.SpacingItemDecoration
import it.bebra.cinema.databinding.FragmentTicketsBinding
import it.bebra.cinema.presentation.tickets.recycle.TicketsListTicketAdapter
import it.bebra.cinema.presentation.tickets.viewmodel.TicketsViewModel

@AndroidEntryPoint
class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding

    private val viewModel: TicketsViewModel by lazy {
        ViewModelProvider(this)[TicketsViewModel::class]
    }

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

        if (binding.recyclerView.adapter?.itemCount == 0) {
            viewModel.loadTickets()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(SpacingItemDecoration(0, 10, 0, 15))

        recyclerView.adapter = TicketsListTicketAdapter()
    }

    private fun setupObservers() {
        viewModel.resultResponses.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as TicketsListTicketAdapter).submitList(it)
        }
    }
}