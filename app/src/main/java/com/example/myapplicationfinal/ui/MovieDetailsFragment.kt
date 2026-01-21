package com.example.myapplicationfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplicationfinal.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {
//ViewBinding
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        val title = arguments?.getString("title") ?: "No Title"
        val overview = arguments?.getString("overview") ?: "No Overview"
        val poster = arguments?.getString("poster") ?: ""

        binding.detailMovieTitle.text = title
        binding.detailMovieOverview.text = overview

        if (poster.isNotEmpty()) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500$poster")
                .into(binding.detailMovieImage)
        }

        // Back button click
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}
