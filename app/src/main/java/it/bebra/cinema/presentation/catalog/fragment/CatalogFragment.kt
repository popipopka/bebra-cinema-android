package it.bebra.cinema.presentation.catalog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import it.bebra.cinema.presentation.catalog.recycle.FilmCatalogGridItemDecoration
import it.bebra.cinema.databinding.FragmentCatalogBinding
import it.bebra.domain.model.FilmListModel
import it.bebra.cinema.presentation.catalog.recycle.CatalogFilmListAdapter

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
        recyclerView.addItemDecoration(FilmCatalogGridItemDecoration(40, 2))

        recyclerView.adapter = CatalogFilmListAdapter(getMockData())
    }

    private fun getMockData(): List<FilmListModel> {
        return listOf(
            FilmListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм 1",
                100
            ),
            FilmListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм другой 2",
                200
            ),
            FilmListModel(1, null, "Фильм точно другой 3", 10),
            FilmListModel(1, null, "Фильм верьте 4", 50),
            FilmListModel(1, null, "Фильм фильм 5", 60),
            FilmListModel(1, null, "Фильм мультфильм 6", 20),
            FilmListModel(1, null, "Очень длинное название у фильма под номером 7", 120)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}