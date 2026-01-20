package com.example.myapplicationfinal.remote

import com.example.myapplicationfinal.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse
}
