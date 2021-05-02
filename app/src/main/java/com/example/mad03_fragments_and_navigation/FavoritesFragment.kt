package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mad03_fragments_and_navigation.adapters.FavoritesListAdapter
import com.example.mad03_fragments_and_navigation.database.AppDatabase
import com.example.mad03_fragments_and_navigation.databinding.FragmentFavoritesBinding
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import com.example.mad03_fragments_and_navigation.utils.EditDialog
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModel
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory


class FavoritesFragment : Fragment(), EditDialog.DialogListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: MovieFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val adapter = FavoritesListAdapter(
            dataSet = listOf(),     // start with empty list
            onDeleteClicked = { movieId -> onDeleteMovieClicked(movieId) },   // pass functions to adapter
            onEditClicked = { movie -> onEditMovieClicked(movie) },           // pass functions to adapter
        )

        with(binding) {
            recyclerView.adapter = adapter
        }

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).movieDao
        val dataSource = MovieRepository.getInstance(dao)
        val viewModelFactory = MovieFavoritesViewModelFactory(dataSource)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieFavoritesViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel?.favorites?.observe(viewLifecycleOwner, Observer { newData ->
            adapter.updateDataSet(newData)
        })

        binding.clearBtn.setOnClickListener { onClearMoviesClicked() }

        return binding.root
    }

    // This is called when recyclerview item edit button is clicked
    private fun onEditMovieClicked(movieObj: Movie) {
        val dialogue = EditDialog()
        dialogue.show(parentFragmentManager, "bla")
        binding.viewModel?.onUpdate(movieObj)
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long) {
        binding.viewModel?.onDelete(movieId)
    }

    private fun onClearMoviesClicked() {
        binding.viewModel?.onClear()
    }

    override fun onDialogPositiveClick(text: String) {
        Toast.makeText(requireContext(), "Callback received.", Toast.LENGTH_SHORT).show()
    }


}