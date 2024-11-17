package it.bebra.cinema.presentation.tickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.bebra.cinema.R
import it.bebra.cinema.common.ui.HorizontalCenteringItemDecoration
import it.bebra.cinema.common.ui.SpacingItemDecoration
import it.bebra.cinema.databinding.FragmentTicketsBinding
import it.bebra.cinema.presentation.tickets.recycle.TicketsListTicketAdapter
import it.bebra.domain.model.TicketListModel
import java.time.LocalDateTime
import java.time.Month

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this.context)

        recyclerView.addItemDecoration(SpacingItemDecoration(0, 10, 0, 10))
        recyclerView.addItemDecoration(HorizontalCenteringItemDecoration(1, 64))

        recyclerView.adapter = TicketsListTicketAdapter(mockData())
    }

    private fun mockData(): List<TicketListModel> {
        return listOf(
            TicketListModel(
                1,
                "Один из нас",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.JULY, 9, 20, 8),
                19,
                1
            ),
            TicketListModel(
                2,
                "Мы",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.JUNE, 11, 20, 8),
                25,
                2
            ),
            TicketListModel(
                3,
                "Невероятные приключения крутого супергероя",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.MAY, 15, 20, 8),
                2,
                3
            ),
            TicketListModel(
                4,
                "Очень страшное и длинное название фильма, такого вы точно не видели",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.APRIL, 17, 20, 8),
                18,
                4
            ),
            TicketListModel(
                5,
                "Галка",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.MARCH, 12, 15, 32),
                143,
                5
            ),
            TicketListModel(
                6,
                "Маколей Калкин",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.FEBRUARY, 1, 1, 8),
                14,
                6
            ),
            TicketListModel(
                7,
                "Виу виу",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.JANUARY, 25, 10, 43),
                2,
                7
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}