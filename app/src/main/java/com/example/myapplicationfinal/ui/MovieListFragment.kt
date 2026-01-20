package com.example.myapplicationfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationfinal.adapter.MovieAdapter
import com.example.myapplicationfinal.databinding.FragmentMovieListBinding
import com.example.myapplicationfinal.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    // 🔑 აქ ჩასვით შენი TMDB API Key
    private val apiKey = "01c54e9c9d3168c6f247f7843f8b15ed"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Movies წამოღება
        fetchMovies()

        return binding.root
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            try {
                // Retrofit suspend call background thread-ზე
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getPopularMovies(apiKey)
                }

                val movies = response.results

                if (movies.isEmpty()) {
                    Toast.makeText(requireContext(), "No movies found", Toast.LENGTH_SHORT).show()
                } else {
                    // RecyclerView Adapter
                    binding.recyclerView.adapter = MovieAdapter(movies) { movie ->
                        val bundle = Bundle().apply {
                            putString("title", movie.title)
                            putString("overview", movie.overview)
                            putString("poster", movie.poster_path)
                        }
                        findNavController().navigate(
                            com.example.myapplicationfinal.R.id.action_movieListFragment_to_movieDetailsFragment,
                            bundle
                        )
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Failed to load movies: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
