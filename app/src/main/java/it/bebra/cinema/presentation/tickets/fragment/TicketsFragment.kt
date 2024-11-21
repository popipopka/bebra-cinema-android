package it.bebra.cinema.presentation.tickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.R
import it.bebra.cinema.common.ui.HorizontalCenteringItemDecoration
import it.bebra.cinema.common.ui.SpacingItemDecoration
import it.bebra.cinema.databinding.FragmentTicketsBinding
import it.bebra.cinema.presentation.tickets.recycle.TicketsListTicketAdapter
import it.bebra.cinema.presentation.tickets.viewmodel.TicketsViewModel
import it.bebra.domain.model.TicketListModel
import kotlinx.coroutines.channels.TickerMode
import java.time.LocalDateTime
import java.time.Month

@AndroidEntryPoint
class TicketsFragment : Fragment() {

    private val viewModel: TicketsViewModel by lazy {
        ViewModelProvider(this)[TicketsViewModel::class]
    }

    private lateinit var binding: FragmentTicketsBinding

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

        viewModel.loadTickets()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(SpacingItemDecoration(0, 10, 0, 15))

        recyclerView.adapter = TicketsListTicketAdapter()
    }

    private fun setupObservers() {
        viewModel.resultResponses.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as TicketsListTicketAdapter).addData(it)
        }
    }
}